package command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import salad.Vegetable;

import java.util.List;
import java.util.Scanner;

public class AddVegetable implements Command {
    private static final Logger fileLogger = LogManager.getLogger("FileOnlyLogger");
    private static final Logger errorLogger = LogManager.getLogger("ErrorLogger");
    private static final Marker ERROR_MARKER = MarkerManager.getMarker("ERROR");

    private final List<Vegetable> vegetables;

    public AddVegetable(List<Vegetable> vegetables) {
        this.vegetables = vegetables;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        execute(scanner);
    }

    public void execute(Scanner scanner) {
        System.out.print("Введіть назву овоча: ");
        String name = scanner.nextLine().trim();

        if (vegetables.stream().anyMatch(v -> v.getName().equalsIgnoreCase(name))) {
            fileLogger.info("Овоч не додано, бо він вже є в списку.");
            System.out.println("Овоч із назвою " + name + " вже є в списку.");
            return;
        }
        else {
            int calories = getIntInput(scanner, "Введіть калорійність на 100гр: ");
            double weight = getDoubleInput(scanner, "Введіть вагу (в грамах): ");

            vegetables.add(new Vegetable(name, calories, weight));
            fileLogger.info("Овоч додано.");
            System.out.println(name + " додано.");
        }
    }

    protected int getIntInput(Scanner scanner, String message) {
        System.out.println(message);
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Неправильне введення. Введіть ціле значення:");
                errorLogger.error(ERROR_MARKER, "Помилка введення: некоректне ціле число", e);
            }
        }
    }

    protected double getDoubleInput(Scanner scanner, String message) {
        System.out.println(message);
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Неправильне введення. Введіть числове значення:");
                errorLogger.error(ERROR_MARKER, "Помилка введення: некоректне число з плаваючою комою", e);
            }
        }
    }
}
