package org.example.command;

import org.example.salad.Vegetable;

import java.util.ArrayList;
import java.util.Scanner;

public class AddVegetable implements Command {
    private final ArrayList<Vegetable> vegetables;

    public AddVegetable(ArrayList<Vegetable> vegetables) {
        this.vegetables = vegetables;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть назву овоча: ");
        String name = scanner.nextLine().trim();

        if (vegetables.stream().anyMatch(v -> v.getName().equalsIgnoreCase(name))) {
            System.out.println("Овоч із назвою " + name + " вже є в списку.");
            return;
        }

        int calories = getIntInput(scanner, "Введіть калорійність на 100гр: ");
        double weight = getDoubleInput(scanner, "Введіть вагу (в грамах): ");

        vegetables.add(new Vegetable(name, calories, weight));
        System.out.println(name + " додано.");
    }

    private int getIntInput(Scanner scanner, String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            System.out.println("Неправильне введення. Введіть ціле значення:");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private double getDoubleInput(Scanner scanner, String message) {
        System.out.print(message);
        while (!scanner.hasNextDouble()) {
            System.out.println("Неправильне введення. Введіть числове значення:");
            scanner.next();
        }
        return scanner.nextDouble();
    }
}
