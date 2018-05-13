package kickboxgame;

import java.util.HashMap;
import java.util.Map;

public class State implements Initializable {

    private Map<Generable, Integer> attributes;

    public State() {
        attributes = initializeMap(StateAttribute.values());
    }

    public State(State state) {
        this.attributes = new HashMap<>(state.getAttributes());
    }

    public Map<Generable, Integer> getAttributes() {
        return attributes;
    }

    public Integer getAttributeValue(StateAttribute stateAttribute) {
        return attributes.get(stateAttribute);
    }

    /*private Map<StateAttribute, Integer> initializeAttributes() {
        Map<StateAttribute, Integer> stateAttributes = new HashMap<>();
        Arrays.stream(StateAttribute.values())
                .forEach(stateAttribute -> stateAttributes.put(stateAttribute, stateAttribute.getDefaultValue()));
        return stateAttributes;
    }*/

    public void changeAttributeValue(StateAttribute stateAttribute, ChangeType changeType, int value) {
        value = changeType == ChangeType.LOSS ? -value : value;
        attributes.replace(stateAttribute, attributes.get(stateAttribute) + value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return attributes.keySet().stream()
                .allMatch(generable -> attributes.get(generable).equals(state.getAttributes().get(generable)));
    }

    @Override
    public int hashCode() {
        return sumAttributes();
    }

    public int sumAttributes() {
        return attributes.values().stream().mapToInt(Number::intValue).sum();
    }

    @Override
    public String toString() {
        return "State{" +
                "attributes=" + attributes +
                '}';
    }
}
