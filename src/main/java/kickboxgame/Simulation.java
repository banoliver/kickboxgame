package kickboxgame;

import java.util.*;

public class Simulation {

    private Set<Scenario> scenarioSet;

    public Simulation(Set<Scenario> scenarioSet) {
        this.scenarioSet = scenarioSet;
    }

    public Set<Scenario> getScenarioSet() {
        return scenarioSet;
    }

    public void runSimulation(int runs) {
        for (int i = 0; i < runs; i++) {
            Player botOne = new Player("botOne");
            Player botTwo = new Player("botTwo");

            Set<MoveStorageProcessor> botOneMoveStorageProcessors = new HashSet<>();
            Set<MoveStorageProcessor> botTwoMoveStorageProcessors = new HashSet<>();

            while (botOne.getState().getAttributeValue(StateAttribute.HEALTH) > 0 &&
                    botTwo.getState().getAttributeValue(StateAttribute.HEALTH) > 0) {

                Scenario scenario = getActiveScenario(botOne, botTwo);

                MoveStorageProcessor botOneMoveStorageProcessor =
                        getActiveMoveStorageProcessor(botOneMoveStorageProcessors, scenario, botOne);
                MoveStorageProcessor botTwoMoveStorageProcessor =
                        getActiveMoveStorageProcessor(botTwoMoveStorageProcessors, scenario, botTwo);

                Move botOneMove = botOneMoveStorageProcessor.getOptimalMove(botOne);
                Move botTwoMove = botTwoMoveStorageProcessor.getOptimalMove(botTwo);

                botOneMoveStorageProcessor.addCurrentMove(botOneMove);
                botTwoMoveStorageProcessor.addCurrentMove(botOneMove);

                botOneMove.makeMove(botOne, botTwo);
                if (botTwo.getState().getAttributeValue(StateAttribute.HEALTH) > 0) {
                    botTwoMove.makeMove(botTwo, botOne);
                }
            }
            int botOneHealth = botOne.getState().getAttributeValue(StateAttribute.HEALTH);

            if (botOneHealth > 0) {
                botOneMoveStorageProcessors.forEach(MoveStorageProcessor::promoteCurrentMoves);
                botTwoMoveStorageProcessors.forEach(MoveStorageProcessor::demoteCurrentMoves);
            } else {
                botTwoMoveStorageProcessors.forEach(MoveStorageProcessor::promoteCurrentMoves);
                botOneMoveStorageProcessors.forEach(MoveStorageProcessor::demoteCurrentMoves);
            }
        }
    }

    private Scenario getActiveScenario(Player playerOne, Player playerTwo) {
        Scenario newScenario = new Scenario(playerOne, playerTwo);
        return (Scenario) addOrFindInSet(scenarioSet, newScenario);
    }

    private MoveStorageProcessor getActiveMoveStorageProcessor(Set<MoveStorageProcessor> moveStorageProcessors,
                                                               Scenario scenario, Player player) {
        MoveStorageProcessor moveStorageProcessor = new MoveStorageProcessor(scenario.getStoredMoves(player));
        return (MoveStorageProcessor) addOrFindInSet(moveStorageProcessors, moveStorageProcessor);
    }

    private <T> Object addOrFindInSet(Set<T> set, T object) {
        set.add(object);
        return findInCollection(set, object);
    }

    private <T> Object findInCollection(Collection<T> collection,
                                                          T object) {
        return collection.stream()
                .filter(moveStorageProcessor -> moveStorageProcessor.equals(object))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("The provided Object is not present in the provided " +
                        "Collection."));
    }
}
