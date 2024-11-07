package command;

import salad.Vegetable;

import java.util.List;
import java.util.Scanner;

public class AddVegetable implements Command {
    private final List<Vegetable> vegetables;

    public AddVegetable(List<Vegetable> vegetables) {
        this.vegetables = vegetables;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        execute(scanner);
    }

    // Додаємо перевантажений метод, який приймає Scanner
    public void execute(Scanner scanner) {
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

    protected int getIntInput(Scanner scanner, String message) {
        System.out.println(message);
        while (true) {
            String input = scanner.nextLine().trim();  // Зчитуємо рядок
            try {
                return Integer.parseInt(input);  // Пробуємо перетворити на int
            } catch (NumberFormatException e) {
                System.out.println("Неправильне введення. Введіть ціле значення:");
            }
        }
    }


    protected double getDoubleInput(Scanner scanner, String message) {
        System.out.println(message);
        while (true) {
            String input = scanner.nextLine().trim();  // Зчитуємо рядок
            try {
                return Double.parseDouble(input);  // Пробуємо перетворити на double
            } catch (NumberFormatException e) {
                System.out.println("Неправильне введення. Введіть числове значення:");
            }
        }
    }
}
