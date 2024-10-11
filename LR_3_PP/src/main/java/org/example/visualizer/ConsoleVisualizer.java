package org.example.visualizer;

import org.example.droidModel.Droid;

public class ConsoleVisualizer {
    // ANSI escape codes for colors
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";     // Swordsman
    public static final String BLUE = "\u001B[34m";    // Mage
    public static final String PURPLE = "\u001B[35m";  // Assassin
    public static final String CYAN = "\u001B[36m";    // Archer
    public static final String GREEN = "\u001B[32m";   // Загальні повідомлення

    public StringBuilder battleLog; // Лог бою

    // Конструктор
    public ConsoleVisualizer() {
        this.battleLog = new StringBuilder();
    }

    public void printMiss(Droid attacker, boolean isSuperAttack) {
        String color = getColor(attacker.getType());
        String message = isSuperAttack ? attacker.getName() + " промахнувся з суператакою!" : attacker.getName() + " промахнувся!";
        System.out.println(color + message + RESET);
        battleLog.append(message).append("\n"); // Записуємо в лог
    }

    public void printAttack(Droid attacker, Droid defender, int damage) {
        String color = getColor(attacker.getType());
        String message = attacker.getName() + " атакує " + defender.getName() + " на " + damage + " шкоди.";
        System.out.println(color + message + RESET);
        battleLog.append(message).append("\n"); // Записуємо в лог
    }

    public void printSuperAttack(Droid attacker, Droid defender, int damage) {
        String color = getColor(attacker.getType());
        String message = attacker.getName() + " використовує супер атаку на " + defender.getName() + " на " + damage + " шкоди!";
        System.out.println(color + message + RESET);
        battleLog.append(message).append("\n"); // Записуємо в лог
    }

    public void printMessage(String message) {
        System.out.println(message);
        battleLog.append(message).append("\n"); // Записуємо в лог
    }

    public void printGreen(String message) {
        System.out.println(GREEN + message + RESET);
    }

    private String getColor(String type) {
        switch (type) {
            case "Assassin":
                return PURPLE;
            case "Mage":
                return BLUE;
            case "Swordsman":
                return RED;
            case "Archer":
                return CYAN;
            default:
                return GREEN;
        }
    }
}
