package org.example.main;
import org.example.battle.Battle1v1;
import org.example.battle.BattleFileHandler;
import org.example.battle.BattleTeam;
import org.example.droidModel.*;
import org.example.visualizer.ConsoleVisualizer;

import java.util.*;


//import java.util.List;
//import java.util.Random;
public class Main {
    private static List<Droid> droids = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static ConsoleVisualizer visualizer = new ConsoleVisualizer(); // Лог бою візуалізується і зберігається
    private static String currentBattleLog = ""; // Для збереження результатів бою

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = getIntegerInput("Виберіть опцію: ");
            switch (choice) {
                case 1:
                    createDroid();
                    break;
                case 2:
                    listDroids();
                    break;
                case 3:
                    battle1v1();
                    break;
                case 4:
                    battleTeam();
                    break;
                case 5:
                    saveBattle();
                    break;
                case 6:
                    loadBattle();
                    break;
                case 7:
                    visualizer.printGreen("Вихід з програми.");
                    System.exit(0);
                    break;
                default:
                    visualizer.printGreen("Неправильний вибір. Спробуйте ще раз.");
            }
        }
    }

    private static void printMenu() {
        visualizer.printGreen("\n--- Меню ---");
        System.out.println("1. Створити дроїда");
        System.out.println("2. Показати список створених дроїдів");
        System.out.println("3. Запустити бій 1 на 1");
        System.out.println("4. Запустити бій команда на команду");
        System.out.println("5. Записати проведений бій у файл");
        System.out.println("6. Відтворити проведений бій зі збереженого файлу");
        System.out.println("7. Завершити програму");
    }

    private static void createDroid() {
        visualizer.printGreen("\n--- Створення дроїда ---");
        System.out.println("Виберіть тип дроїда:");
        System.out.println("1. Маг");
        System.out.println("2. Асасин");
        System.out.println("3. Мечник");
        System.out.println("4. Лучник");
        int type = getIntegerInput("Ваш вибір: ");
        String name = getStringInput("Введіть ім'я дроїда: ");
        boolean hasWeapon = false;
        while (true) {
            String weaponChoice = getStringInput("Хочете дати дроїду зброю? (так/ні): ").toLowerCase();
            if (weaponChoice.equals("так")) {
                hasWeapon = true;
                break;
            } else if (weaponChoice.equals("ні")) {
                break;
            } else {
                visualizer.printGreen("Будь ласка, введіть 'так' або 'ні'.");
            }
        }
        switch (type) {
            case 1:
                droids.add(new Mage(name, hasWeapon));
                break;
            case 2:
                droids.add(new Assassin(name, hasWeapon));
                break;
            case 3:
                droids.add(new Swordsman(name, hasWeapon));
                break;
            case 4:
                droids.add(new Archer(name, hasWeapon));
                break;
            default:
                visualizer.printGreen("Невірний тип дроїда.");
                return;
        }
        visualizer.printGreen("Дроїд створений успішно!");
    }

    private static void listDroids() {
        visualizer.printGreen("\n--- Список дроїдів ---");
        if (droids.isEmpty()) {
            System.out.println("Дроїдів ще немає");
            return;
        }
        for (int i = 0; i < droids.size(); i++) {
            Droid d = droids.get(i);
            System.out.printf("%d. %s (%s) - Здоров'я: %d, Шкода: %d", i + 1, d.getName(), d.getType(), d.getHealth(), d.getDamage());
            if (d.isHasWeapon()) {
                System.out.print(", зброя");
            }
            System.out.println();
        }
    }

    private static void battle1v1() {
        if (droids.size() < 2) {
            visualizer.printGreen("Потрібно принаймні 2 дроїди для бою.");
            return;
        }

        int idx1 = getIntegerInput("Виберіть номер першого дроїда: ") - 1;
        int idx2 = getIntegerInput("Виберіть номер другого дроїда: ") - 1;

        if (idx1 < 0 || idx1 >= droids.size() || idx2 < 0 || idx2 >= droids.size() || idx1 == idx2) {
            visualizer.printGreen("Неправильний вибір дроїдів.");
            return;
        }

        Droid d1 = droids.get(idx1);
        Droid d2 = droids.get(idx2);

        Battle1v1 battle = new Battle1v1(d1, d2, visualizer);
        currentBattleLog = battle.startBattle(); // Зберігаємо лог бою

        d1.resetStats();
        d2.resetStats();
    }

    private static void battleTeam() {
        if (droids.size() < 2) {
            visualizer.printGreen("Дроїдів має бути принаймні двоє для командного бою.");
            return;
        }

        Set<Droid> selectedDroids = new HashSet<>();

        int teamSize = getIntegerInput("Введіть кількість дроїдів у команді: ");
        System.out.println("Створіть першу команду:");
        List<Droid> team1 = createTeam(teamSize, selectedDroids);

        System.out.println("Створіть другу команду:");
        List<Droid> team2 = createTeam(teamSize, selectedDroids);

        BattleTeam battle = new BattleTeam(team1, team2, visualizer);
        currentBattleLog = battle.startBattle(); // Зберігаємо лог командного бою

        // Відновлюємо статистики після бою
        for (Droid droid : team1) {
            droid.resetStats();
        }
        for (Droid droid : team2) {
            droid.resetStats();
        }
    }

    static List<Droid> createTeam(int teamSize, Set<Droid> selectedDroids) {
        List<Droid> team = new ArrayList<>();
        for (int i = 0; i < teamSize; i++) {
            System.out.println("Виберіть дроїда для команди:");
            int idx = getIntegerInput("Ваш вибір: ") - 1;
            if (idx < 0 || idx >= droids.size()) {
                visualizer.printGreen("Невірний вибір. Спробуйте ще раз.");
                i--;
                continue;
            }
            Droid selectedDroid = droids.get(idx);
            if (selectedDroids.contains(selectedDroid)) {
                visualizer.printGreen("Цей дроїд вже вибраний. Виберіть іншого.");
                i--;
                continue;
            }
            team.add(selectedDroid);
            selectedDroids.add(selectedDroid);
            visualizer.printGreen("Дроїд " + selectedDroid.getName() + " додано до команди.\n");
        }
        return team;
    }

    private static void saveBattle() {
        if (currentBattleLog.isEmpty()) {
            visualizer.printGreen("Немає проведеного бою для збереження.");
            return;
        }
        String filename = getStringInput("Введіть назву файлу для збереження: ");
        BattleFileHandler handler = new BattleFileHandler(filename);
        handler.saveBattle(currentBattleLog);
        visualizer.printGreen("Бій збережено у файл " + filename);
    }

    private static void loadBattle() {
        String filename = getStringInput("Введіть назву файлу для завантаження: ");
        BattleFileHandler handler = new BattleFileHandler(filename);
        String battleLog = handler.loadBattle();
        visualizer.printGreen("\nВідтворення бою:" + battleLog);
    }

    private static int getIntegerInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                int val = Integer.parseInt(input);
                return val;
            } catch (NumberFormatException e) {
                visualizer.printGreen("Будь ласка, введіть ціле число.");
            }
        }
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}

