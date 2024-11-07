package command;

import salad.Salad;
import salad.Vegetable;

import java.util.Optional;
import java.util.Scanner;

public class RemoveVegetableFromSalad implements Command {
    private Salad salad;

    public RemoveVegetableFromSalad(Salad salad) {
        this.salad = salad;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть назву овоча для видалення з салату: ");
        String removeVeg = scanner.next();

        Optional<Vegetable> vegetableToRemove = salad.getIngredients().stream()
                .filter(v -> v.getName().equalsIgnoreCase(removeVeg))
                .findFirst();

        if (vegetableToRemove.isPresent()) {
            salad.getIngredients().remove(vegetableToRemove.get());
            System.out.println(removeVeg + " видалено з салату.");
        } else {
            System.out.println("Інгредієнт " + removeVeg + " відсутній в салаті.");
        }
    }
}
