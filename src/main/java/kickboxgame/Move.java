package kickboxgame;

public enum Move implements Generable {

    PUNCH(10) {
        @Override
        public void makeMove(Player player, Player enemy) {
            System.out.println(player.getName() + " punches " + enemy.getName() + ".");
//            player.getState().changeAttributeValue(StateAttribute.ENERGY, ChangeType.LOSS, 10);
            enemy.getState().changeAttributeValue(StateAttribute.HEALTH, ChangeType.LOSS, 10);
//            System.out.println(player.getName() + "'s energy decreased to " +
//                    player.getState().getAttributeValue(StateAttribute.ENERGY));
            System.out.println(enemy.getName() + "'s health decreased to " +
                    enemy.getState().getAttributeValue(StateAttribute.HEALTH));
        }
    }, KICK(20) {
        @Override
        public void makeMove(Player player, Player enemy) {
            System.out.println(player.getName() + " kicks " + enemy.getName() + ".");
//            player.getState().changeAttributeValue(StateAttribute.ENERGY, ChangeType.LOSS, 20);
            enemy.getState().changeAttributeValue(StateAttribute.HEALTH, ChangeType.LOSS, 20);
//            System.out.println(player.getName() + "'s energy decreased to " +
//                    player.getState().getAttributeValue(StateAttribute.ENERGY));
            System.out.println(enemy.getName() + "'s health decreased to " +
                    enemy.getState().getAttributeValue(StateAttribute.HEALTH));
        }
    }/*, DEFEND(-10) {
        @Override
        public void makeMove(Player player, Player enemy) {
            System.out.println(player.getName() + " defends.");
            player.getState().changeAttributeValue(StateAttribute.ENERGY, ChangeType.GAIN, 10);
            player.getState().changeAttributeValue(StateAttribute.DEFENSE, ChangeType.GAIN, 10);
            System.out.println(player.getName() + "'s energy increased to " +
                    player.getState().getAttributeValue(StateAttribute.ENERGY));
        }
    }*/;

    private int defaultValue;

    private int energyCost;

    Move(int energyCost) {
        this.energyCost = energyCost;
    }

    @Override
    public int getDefaultValue() {
        return defaultValue;
    }

    public int getEnergyCost() {
        return energyCost;
    }

    public abstract void makeMove(Player player, Player enemy);
}
