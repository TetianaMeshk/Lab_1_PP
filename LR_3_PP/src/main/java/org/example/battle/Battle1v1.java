package org.example.battle;

import org.example.visualizer.ConsoleVisualizer;
import org.example.droidModel.Droid;

import java.util.Random;

public class Battle1v1 {
    private Droid droid1;
    private Droid droid2;
    private ConsoleVisualizer visualizer;

    public Battle1v1(Droid droid1, Droid droid2, ConsoleVisualizer visualizer) {
        this.droid1 = droid1;
        this.droid2 = droid2;
        this.visualizer = visualizer;
    }

    public String startBattle() {
        Random rand = new Random();
        int superAttackRound1 = rand.nextInt(5) + 1;
        int superAttackRound2 = rand.nextInt(5) + 1;
        int round = 1;

        visualizer.printMessage("\nПочаток бою між " + droid1.getName() + " і " + droid2.getName());

        while (droid1.isAlive() && droid2.isAlive()) {
            visualizer.printMessage("Раунд " + round + ":");

            // Атака дроїда 1
            if (round == superAttackRound1 && !droid1.isSuperAttackUsed()) {
                droid1.superAttack(droid2, visualizer);
            } else {
                droid1.attack(droid2, visualizer);
            }

            if (!droid2.isAlive()) {
                visualizer.printMessage(droid1.getName() + " переміг!");
                break;
            }

            // Атака дроїда 2
            if (round == superAttackRound2 && !droid2.isSuperAttackUsed()) {
                droid2.superAttack(droid1, visualizer);
            } else {
                droid2.attack(droid1, visualizer);
            }

            if (!droid1.isAlive()) {
                visualizer.printMessage(droid2.getName() + " переміг!");
                break;
            }

            // Виведення здоров'я дроїдів після раунду
            visualizer.printMessage("--- Здоров'я дроїдів ---");
            visualizer.printMessage(droid1.getName() + " здоров'я: " + droid1.getHealth());
            visualizer.printMessage(droid2.getName() + " здоров'я: " + droid2.getHealth());

            round++;
        }

        return visualizer.battleLog.toString();
    }
}
