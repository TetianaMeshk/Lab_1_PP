package file;
import salad.Salad;
import salad.Vegetable;

import java.io.*;
import java.util.ArrayList;

public class VegetableFileManager {
    private static final String VEGETABLES_FILE = "vegetables.txt";
    private static final String SALAD_FILE = "salad.txt";

    // Метод для збереження овочів у файл
    public static void saveVegetablesToFile(ArrayList<Vegetable> vegetables) {
        writeToFile(VEGETABLES_FILE, vegetables);
    }

    // Метод для збереження салату у файл
    public static void saveSaladToFile(Salad salad) {
        writeToFile(SALAD_FILE, salad.getIngredients());
    }

    private static void writeToFile(String filePath, Iterable<Vegetable> vegetables) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Vegetable vegetable : vegetables) {
                writer.write(vegetable.getName() + "," + vegetable.getCalories() + "," + vegetable.getWeight());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Помилка збереження до файлу: " + e.getMessage());
        }
    }

    // Метод для завантаження овочів із файлу
    public static ArrayList<Vegetable> loadVegetablesFromFile() {
        return loadFromFile(VEGETABLES_FILE);
    }

    // Метод для завантаження салату із файлу
    public static Salad loadSaladFromFile() {
        Salad salad = new Salad();
        for (Vegetable vegetable : loadFromFile(SALAD_FILE)) {
            salad.addVegetable(vegetable);
        }
        return salad;
    }

    private static ArrayList<Vegetable> loadFromFile(String filePath) {
        ArrayList<Vegetable> vegetables = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0];
                    int calories = Integer.parseInt(parts[1]);
                    double weight = Double.parseDouble(parts[2]);
                    vegetables.add(new Vegetable(name, calories, weight));
                }
            }
        } catch (IOException e) {
            System.out.println("Помилка завантаження з файлу: " + e.getMessage());
        }
        return vegetables;
    }
}
