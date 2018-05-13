package kickboxgame;

import java.util.*;
import java.util.stream.Collectors;

public class MoveStorage implements Initializable {

    private Map<Generable, Integer> moves;

    public MoveStorage() {
        moves = initializeMap(Move.values());
    }

    public Map<Generable, Integer> getMoves() {
        return moves;
    }

    /*private Map<Move, Integer> initializeMoves() {
        Map<Move, Integer> moves = new HashMap<>();
        Arrays.stream(Move.values()).forEach(move -> moves.put(move, 0));
        return moves;
    }*/

    public void promoteMove(Move move) {
        moves.merge(move, 1, Integer::sum);
    }

    public void resetValues() {
        moves.replaceAll((k, v) -> 0);
    }

    public void replaceValues(MoveStorage source) {
        moves.putAll(source.getMoves());
    }

    public void addValues(MoveStorage source) {
        source.getMoves().forEach((k, v) -> moves.merge(k, v, Integer::sum));
    }

    public void subtractValues(MoveStorage source) {
        source.getMoves().forEach((k, v) -> moves.merge(k, v, (v1, v2) -> v1 - v2));
    }

    public List<Generable> getMaxValueKeys() {
        Integer max = getMaxValue();
        return moves.entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(max))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private Integer getMaxValue() {
        return moves.entrySet()
                .stream()
                .max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1)
                .get()
                .getValue();
    }

    @Override
    public String toString() {
        return "MoveStorage{" +
                "moves=" + moves +
                '}';
    }
}
