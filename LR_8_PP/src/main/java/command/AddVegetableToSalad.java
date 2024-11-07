package command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import salad.Salad;
import salad.Vegetable;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class AddVegetableToSalad implements Command {
    private static final Logger fileLogger = LogManager.getLogger("FileOnlyLogger");

    private final Salad salad;
    private final ArrayList<Vegetable> vegetables;

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
            fileLogger.info("Овоч додано до салату.");
            System.out.println(nameToAdd + " додано до салату.");
        } else {
            fileLogger.info("Овоч не додано до салату.");
            System.out.println("Овоч не знайдено.");
        }
    }
}
