package kickboxgame;

import java.util.HashMap;
import java.util.Map;

public class Test {

    private Map<State, MoveStorage> map = new HashMap<>();

    public Map<State, MoveStorage> getMap() {
        return map;
    }

    public static void main(String[] args) {
        Test test = new Test();
        State s1 = new State();
        State s2 = new State();
        State s3 = new State();
        s1.changeAttributeValue(StateAttribute.HEALTH, ChangeType.LOSS, 10);
        s2.changeAttributeValue(StateAttribute.HEALTH, ChangeType.LOSS, 20);
        s3.changeAttributeValue(StateAttribute.HEALTH, ChangeType.LOSS, 10);
        test.getMap().put(s1, new MoveStorage());
        test.getMap().put(s2, new MoveStorage());
        System.out.println(test.getMap().get(s3));
    }
}
