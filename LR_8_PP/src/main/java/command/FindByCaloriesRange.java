package command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import salad.Salad;

import java.util.Scanner;

public class FindByCaloriesRange implements Command {
    private static final Logger errorLogger = LogManager.getLogger("ErrorLogger");
    private static final Marker ERROR_MARKER = MarkerManager.getMarker("ERROR");

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
                .forEach(vegetable -> System.out.print(vegetable));
    }

    private int getInput(Scanner scanner, String prompt) {
        System.out.println(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Неправильне введення. Будь ласка введіть ціле число:");
            scanner.next();
            errorLogger.error(ERROR_MARKER, "Неправильне введення для діапазону калорій.", new Exception("Invalid input"));
        }
        return scanner.nextInt();
    }
}
