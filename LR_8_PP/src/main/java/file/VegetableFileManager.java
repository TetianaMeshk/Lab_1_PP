package file;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import salad.Salad;
import salad.Vegetable;

import java.io.*;
import java.util.ArrayList;

public class VegetableFileManager {
    private static final Logger fileLogger = LogManager.getLogger("FileOnlyLogger");
    private static final Logger errorLogger = LogManager.getLogger("ErrorLogger");
    private static final Marker ERROR_MARKER = MarkerManager.getMarker("ERROR");

    protected static final String VEGETABLES_FILE = "vegetables.txt";
    protected static final String SALAD_FILE = "salad.txt";

    // Метод для збереження овочів у файл
    public static void saveVegetablesToFile(ArrayList<Vegetable> vegetables) {
        fileLogger.info("Збереження овочів до файлу.");
        writeToFile(VEGETABLES_FILE, vegetables);
    }

    // Метод для збереження салату у файл
    public static void saveSaladToFile(Salad salad) {
        fileLogger.info("Збереження салату до файлу.");
        writeToFile(SALAD_FILE, salad.getIngredients());
    }

    protected static void writeToFile(String filePath, Iterable<Vegetable> vegetables) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Vegetable vegetable : vegetables) {
                writer.write(vegetable.getName() + "," + vegetable.getCalories() + "," + vegetable.getWeight());
                writer.newLine();
            }
            fileLogger.info("Дані успішно записані у файл: {}", filePath);
        } catch (IOException e) {
            errorLogger.error(ERROR_MARKER, "Помилка запису до файлу: {}", filePath, e);
        }
    }

    // Метод для завантаження овочів із файлу
    public static ArrayList<Vegetable> loadVegetablesFromFile() {
        fileLogger.info("Завантаження овочів з файлу.");
        return loadFromFile(VEGETABLES_FILE);
    }

    // Метод для завантаження салату із файлу
    public static Salad loadSaladFromFile() {
        fileLogger.info("Завантаження салату з файлу.");
        Salad salad = new Salad();
        for (Vegetable vegetable : loadFromFile(SALAD_FILE)) {
            salad.addVegetable(vegetable);
        }
        return salad;
    }

    protected static ArrayList<Vegetable> loadFromFile(String filePath) {
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
            fileLogger.info("Дані успішно завантажені з файлу: {}", filePath);
        } catch (IOException e) {
            errorLogger.error(ERROR_MARKER, "Помилка завантаження з файлу: {}", filePath, e);
        } catch (NumberFormatException e) {
            errorLogger.error(ERROR_MARKER, "Помилка обробки даних з файлу: {}", filePath, e);
        }
        return vegetables;
    }
}
