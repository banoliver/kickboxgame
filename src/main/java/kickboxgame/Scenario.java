package kickboxgame;

import java.util.*;
import java.util.stream.Collectors;

public class Scenario {

    private Map<State, MoveStorage> states;

    public Scenario(State... states) {
        this.states = generateStatesMap(states);
    }

    public Scenario(Player... players) {
        this(Arrays.stream(players).map(Player::getState).toArray(State[]::new));
    }

    public Map<State, MoveStorage> getStates() {
        return states;
    }

    public MoveStorage getStoredMoves(State playerState) {
        return states.get(playerState);
    }

    public MoveStorage getStoredMoves(Player player) {
        return getStoredMoves(player.getState());
    }

    private Map<State, MoveStorage> generateStatesMap(State... states) {
        return Arrays.stream(states)
                .collect(Collectors.toMap(State::new, state -> new MoveStorage(), (a, b) -> a));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scenario scenario = (Scenario) o;
        return scenario.getStates().keySet().equals(states.keySet());
    }

    @Override
    public int hashCode() {
        return sumStateAttributes();
    }

    private int sumStateAttributes() {
        return states.keySet().stream().mapToInt(State::sumAttributes).sum();
    }

    @Override
    public String toString() {
        return "Scenario{" +
                "states=" + states +
                '}';
    }
}
