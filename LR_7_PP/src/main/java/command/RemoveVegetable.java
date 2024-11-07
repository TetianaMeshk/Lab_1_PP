package command;

import salad.Salad;
import salad.Vegetable;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class RemoveVegetable implements Command {
    private ArrayList<Vegetable> vegetables;
    private Salad salad;

    public RemoveVegetable(ArrayList<Vegetable> vegetables, Salad salad) {
        this.vegetables = vegetables;
        this.salad = salad;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть назву овоча для видалення: ");
        String nameToRemove = scanner.nextLine().trim();

        Optional<Vegetable> vegetableToRemove = vegetables.stream()
                .filter(v -> v.getName().equalsIgnoreCase(nameToRemove))
                .findFirst();

        if (vegetableToRemove.isPresent()) {
            Vegetable veg = vegetableToRemove.get();

            // Видаляємо овоч зі списку овочів
            vegetables.remove(veg);

            // Видаляємо овоч із салату
            salad.removeVegetable(veg);

            System.out.println(nameToRemove + " видалено з овочів.");
        } else {
            System.out.println("Овоч " + nameToRemove + " не знайдено.");
        }
    }
}
