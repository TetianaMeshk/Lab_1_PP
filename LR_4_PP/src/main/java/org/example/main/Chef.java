package org.example.main;

import org.example.salad.Salad;
import org.example.salad.Vegetable;
import org.example.command.*;
import org.example.file.VegetableFileManager;

import java.util.ArrayList;
import java.util.Scanner;

public class Chef {
    private final ArrayList<Vegetable> vegetables;
    private final Salad salad;

    public Chef() {
        this.vegetables = VegetableFileManager.loadVegetablesFromFile();
        this.salad = VegetableFileManager.loadSaladFromFile();
        menu();
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
                case 1 -> addVegetable.execute();
                case 2 -> removeVegetable.execute();
                case 3 -> showAllVegetables.execute();
                case 4 -> addVegetableToSalad.execute();
                case 5 -> removeVegetableFromSalad.execute();
                case 6 -> calculateCalories.execute();
                case 7 -> showSalad.execute();
                case 8 -> sortByWeight.execute();
                case 9 -> sortByCalories.execute();
                case 10 -> findByCaloriesRange.execute();
                case 11 -> {
                    VegetableFileManager.saveVegetablesToFile(vegetables);
                    VegetableFileManager.saveSaladToFile(salad);
                    System.out.println("Збережено до файлу.");
                }
                case 12 -> {
                    System.out.println("Вихід.");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Неправильна опція. Спробуйте ще раз.");
            }
        }
    }

    private void showMenu() {
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

    private int getUserChoice(Scanner scanner) {
        System.out.print("Оберіть опцію: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Неправильне введення. Спробуйте ще раз: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
}
