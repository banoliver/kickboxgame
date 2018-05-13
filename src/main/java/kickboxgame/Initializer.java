package kickboxgame;

public class Initializer {
    private static Initializer ourInstance = new Initializer();

    public static Initializer getInstance() {
        return ourInstance;
    }

    private Initializer() {
    }
}
