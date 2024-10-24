package org.example.command;
import org.example.salad.Salad;
import java.util.Scanner;

public class FindByCaloriesRange implements Command {
    private final Salad salad;

    public FindByCaloriesRange(Salad salad) {
        this.salad = salad;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        int minCalories = getInput(scanner, "Введіть початкове значення діапазону:");
        int maxCalories = getInput(scanner, "Введіть кінцеве значення діапазону:");

        salad.getIngredients().stream()
                .filter(v -> v.getCalories() >= minCalories && v.getCalories() <= maxCalories)
                .forEach(vegetable -> System.out.println(vegetable));
    }

    // Helper method for input validation
    private int getInput(Scanner scanner, String prompt) {
        System.out.println(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Неправильне введення. Будь ласка введіть ціле число:");
            scanner.next();
        }
        return scanner.nextInt();
    }
}
