package kickboxgame;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class MoveStorageProcessor {

    private MoveStorage storedMoves;

    private MoveStorage currentMoves = new MoveStorage();

    private MoveStorage totalMoves = new MoveStorage();

    public MoveStorageProcessor(MoveStorage storedMoves) {
        this.storedMoves = storedMoves;
    }

    public MoveStorage getStoredMoves() {
        return storedMoves;
    }

    public MoveStorage getCurrentMoves() {
        return currentMoves;
    }

    public MoveStorage getTotalMoves() {
        return totalMoves;
    }

    public void addCurrentMove(Move move) {
        currentMoves.promoteMove(move);
        totalMoves.promoteMove(move);
    }

    public void promoteCurrentMoves() {
        storedMoves.addValues(currentMoves);
        resetTemporaryMoves();
    }

    public void demoteCurrentMoves() {
        storedMoves.subtractValues(currentMoves);
        resetTemporaryMoves();
    }

    private void resetTemporaryMoves() {
        emptyCurrentMoves();
        updateTotalMoves();
    }

    private void emptyCurrentMoves() {
        currentMoves.resetValues();
    }

    private void updateTotalMoves() {
        totalMoves.replaceValues(storedMoves);
    }

    public Move getOptimalMove(Player player) {
        int playerEnergy = player.getState().getAttributeValue(StateAttribute.ENERGY);
        List<Generable> maxValueKeys = totalMoves.getMaxValueKeys();
        return (Move) (maxValueKeys.size() > 1 ? maxValueKeys.get(getRandomIndex(maxValueKeys.size())) : maxValueKeys.get(0));
    }

    private int getRandomIndex(int size) {
        return ThreadLocalRandom.current().nextInt(size);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoveStorageProcessor that = (MoveStorageProcessor) o;
        return Objects.equals(storedMoves, that.getStoredMoves());
    }

    @Override
    public int hashCode() {
        return Objects.hash(storedMoves);
    }

    @Override
    public String toString() {
        return "MoveStorageProcessor{" +
                "storedMoves=" + storedMoves +
                ", currentMoves=" + currentMoves +
                ", totalMoves=" + totalMoves +
                '}';
    }
}
