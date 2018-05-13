package kickboxgame;

public class MoveProcessor {

    public void processMove(Move move, Player player, Player enemy) {
        switch (move) {
            case PUNCH: processPunch(player, enemy);
            case KICK: processKick(player, enemy);
//            case DEFEND: processDefend(player, enemy);
        }
    }

    private void processPunch(Player player, Player enemy) {
        player.getState().changeAttributeValue(StateAttribute.ENERGY, ChangeType.LOSS, 10);
        enemy.getState().changeAttributeValue(StateAttribute.HEALTH, ChangeType.LOSS, 10);
    }

    private void processKick(Player player, Player enemy) {
        player.getState().changeAttributeValue(StateAttribute.ENERGY, ChangeType.LOSS, 20);
        enemy.getState().changeAttributeValue(StateAttribute.HEALTH, ChangeType.LOSS, 20);
    }

    private void processDefend(Player player, Player enemy) {
        player.getState().changeAttributeValue(StateAttribute.ENERGY, ChangeType.GAIN, 10);
        player.getState().changeAttributeValue(StateAttribute.DEFENSE, ChangeType.GAIN, 10);
    }
}
