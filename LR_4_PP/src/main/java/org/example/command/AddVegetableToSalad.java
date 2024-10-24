package org.example.command;

import org.example.salad.Salad;
import org.example.salad.Vegetable;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class AddVegetableToSalad implements Command {
    private Salad salad;
    private ArrayList<Vegetable> vegetables;

    public AddVegetableToSalad(Salad salad, ArrayList<Vegetable> vegetables) {
        this.salad = salad;
        this.vegetables = vegetables;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть назву овоча для додавання до салату: ");
        String nameToAdd = scanner.nextLine().trim();

        Optional<Vegetable> vegetableToAdd = vegetables.stream()
                .filter(v -> v.getName().equalsIgnoreCase(nameToAdd))
                .findFirst();

        if (vegetableToAdd.isPresent()) {
            salad.addVegetable(vegetableToAdd.get());
            System.out.println(nameToAdd + " додано до салату.");
        } else {
            System.out.println("Овоч не знайдено.");
        }
    }
}
