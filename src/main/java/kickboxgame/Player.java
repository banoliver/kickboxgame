package kickboxgame;

public class Player {

    private String name;

    private State state = new State();

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public State getState() {
        return state;
    }

    public void move(Move move, Player target) {
        State targetState = target.getState();
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", state=" + state +
                '}';
    }
}
