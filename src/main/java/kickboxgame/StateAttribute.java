package kickboxgame;

public enum StateAttribute implements Generable {

    HEALTH(200), ENERGY(100), ATTACK(50), DEFENSE(50);

    private int defaultValue;

    StateAttribute(int defaultValue) {
        this.defaultValue = defaultValue;
    }

    public int getDefaultValue() {
        return defaultValue;
    }
}
