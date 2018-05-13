package kickboxgame;

import java.util.*;

public class KickBox {

    private Player player;

    private Set<Scenario> scenarioSet = new HashSet<>();

    public KickBox(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public Set<Scenario> getScenarioSet() {
        return scenarioSet;
    }

    public static void main(String[] args) {
        /*Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter your name.");*/
        KickBox kickBox = new KickBox(new Player(/*scanner.nextLine())*/"Joe"));
        System.out.println("Welcome to the KickBox Arena, " + kickBox.getPlayer().getName() + "!");

        Simulation simulation = new Simulation(kickBox.getScenarioSet());
        simulation.runSimulation(10);
        System.out.println(kickBox.getScenarioSet());
    }
}
