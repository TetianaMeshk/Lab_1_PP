package main;

import command.*;
import file.VegetableFileManager;
import salad.Salad;
import salad.Vegetable;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private final ArrayList<Vegetable> vegetables;
    private final Salad salad;
    private final CommandManager commandManager;

    public Menu() {
        this.vegetables = VegetableFileManager.loadVegetablesFromFile();
        this.salad = VegetableFileManager.loadSaladFromFile();
        this.commandManager = new CommandManager();
    }

    public void menu() {
        Command addVegetable = new AddVegetable(vegetables);
        Command removeVegetable = new RemoveVegetable(vegetables, salad);
        Command showAllVegetables = new ShowAllVegetables(vegetables);
        Command addVegetableToSalad = new AddVegetableToSalad(salad, vegetables);
        Command removeVegetableFromSalad = new RemoveVegetableFromSalad(salad);
        Command calculateCalories = new CalculateCalories(salad);
        Command sortByWeight = new SortByWeight(salad);
        Command sortByCalories = new SortByCalories(salad);
        Command findByCaloriesRange = new FindByCaloriesRange(salad);
        Command showSalad = new ShowSalad(salad);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            showMenu();
            int choice = getUserChoice(scanner);

            switch (choice) {
                case 1 -> commandManager.runCommand(addVegetable);
                case 2 -> commandManager.runCommand(removeVegetable);
                case 3 -> commandManager.runCommand(showAllVegetables);
                case 4 -> commandManager.runCommand(addVegetableToSalad);
                case 5 -> commandManager.runCommand(removeVegetableFromSalad);
                case 6 -> commandManager.runCommand(calculateCalories);
                case 7 -> commandManager.runCommand(showSalad);
                case 8 -> commandManager.runCommand(sortByWeight);
                case 9 -> commandManager.runCommand(sortByCalories);
                case 10 -> commandManager.runCommand(findByCaloriesRange);
                case 11 -> {
                    VegetableFileManager.saveVegetablesToFile(vegetables);
                    VegetableFileManager.saveSaladToFile(salad);
                    System.out.println("Збережено до файлу.");
                }
                case 12 -> {
                    System.out.println("Вихід.");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Неправильна опція. Спробуйте ще раз.");
            }
        }
    }

    protected void showMenu() {
        System.out.println("\nМеню:");
        System.out.println("1. Додати овоч");
        System.out.println("2. Видалити овоч");
        System.out.println("3. Вивести всі овочі");
        System.out.println("4. Додати овоч до салату");
        System.out.println("5. Видалити овоч з салату");
        System.out.println("6. Підрахувати калорійність салату");
        System.out.println("7. Вивести інгредієнти салату");
        System.out.println("8. Відсортувати інгредієнти салату за вагою");
        System.out.println("9. Відсортувати інгредієнти салату за калорійністю");
        System.out.println("10. Знайти інгредієнти салату в діапазоні калорійності");
        System.out.println("11. Зберегти у файл");
        System.out.println("12. Вихід");
    }

    protected int getUserChoice(Scanner scanner) {
        System.out.print("Оберіть опцію: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Неправильне введення. Спробуйте ще раз: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
}
