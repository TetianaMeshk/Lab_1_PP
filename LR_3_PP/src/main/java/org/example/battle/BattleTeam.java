package org.example.battle;

import org.example.visualizer.ConsoleVisualizer;
import org.example.droidModel.Droid;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BattleTeam {
    private List<Droid> team1;
    private List<Droid> team2;
    private ConsoleVisualizer visualizer;

    public BattleTeam(List<Droid> team1, List<Droid> team2, ConsoleVisualizer visualizer) {
        this.team1 = team1;
        this.team2 = team2;
        this.visualizer = visualizer;
    }

    public String startBattle() {
        if (team1.isEmpty()) {
            visualizer.printGreen("Кожна команда повинна мати принаймні одного дрона.");
            return "";
        }

        visualizer.printMessage("\nПочаток бою команда на команду.");
        Random rand = new Random();
        int round = 1;

        Droid currentDroid1 = getRandomAliveDroid(team1, rand);
        Droid currentDroid2 = getRandomAliveDroid(team2, rand);

        while (currentDroid1 != null && currentDroid2 != null) {
            visualizer.printMessage("Раунд " + round + ":");

            // Дрони атакують один одного в раунді
            fightRound(currentDroid1, currentDroid2);
            if (!currentDroid2.isAlive()) {
                currentDroid2 = getRandomAliveDroid(team2, rand);
                if (currentDroid2 == null) break;
            }

            fightRound(currentDroid2, currentDroid1);
            if (!currentDroid1.isAlive()) {
                currentDroid1 = getRandomAliveDroid(team1, rand);
                if (currentDroid1 == null) break;
            }

            // Виведення здоров'я команд після раунду
            displayTeamHealth();
            round++;
        }

        List<Droid> winningTeam = isTeamDefeated(team2) ? team1 : team2;
        visualizer.printMessage("Перемогла команда: " + getTeamNames(winningTeam));
        return visualizer.battleLog.toString();
    }

    private void fightRound(Droid attacker, Droid defender) {
        Random rand = new Random();
        boolean useSuperAttack = rand.nextBoolean();

        if (!attacker.isSuperAttackUsed() && useSuperAttack) {
            attacker.superAttack(defender, visualizer);
        } else {
            attacker.attack(defender, visualizer);
        }
    }

    private void displayTeamHealth() {
        visualizer.printMessage("--- Здоров'я команд ---");
        for (Droid d : team1) {
            visualizer.printMessage(d.getName() + " (" + d.getType() + ") здоров'я: " + d.getHealth());
        }
        for (Droid d : team2) {
            visualizer.printMessage(d.getName() + " (" + d.getType() + ") здоров'я: " + d.getHealth());
        }
    }

    private Droid getRandomAliveDroid(List<Droid> team, Random rand) {
        List<Droid> alive = new ArrayList<>();
        for (Droid d : team) {
            if (d.isAlive()) alive.add(d);
        }
        if (alive.isEmpty()) return null;
        return alive.get(rand.nextInt(alive.size()));
    }

    private boolean isTeamDefeated(List<Droid> team) {
        for (Droid d : team) {
            if (d.isAlive()) return false;
        }
        return true;
    }

    private String getTeamNames(List<Droid> team) {
        List<String> names = new ArrayList<>();
        for (Droid d : team) {
            names.add(d.getName());
        }
        return String.join(", ", names);
    }
}
