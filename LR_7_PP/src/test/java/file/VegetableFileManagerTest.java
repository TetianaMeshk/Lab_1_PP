package file;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import salad.Salad;
import salad.Vegetable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VegetableFileManagerTest {

    private static final String VEGETABLES_FILE = "vegetables.txt";
    private static final String SALAD_FILE = "salad.txt";

    @AfterEach
    void cleanUp() throws IOException {
        Files.deleteIfExists(Paths.get(VEGETABLES_FILE));
        Files.deleteIfExists(Paths.get(SALAD_FILE));
    }

    @Test
    void testSaveVegetablesToFile() throws IOException {
        ArrayList<Vegetable> vegetables = new ArrayList<>();
        vegetables.add(new Vegetable("Морква", 41, 0.5));
        vegetables.add(new Vegetable("Помідор", 18, 0.3));

        VegetableFileManager.saveVegetablesToFile(vegetables);

        List<String> lines = Files.readAllLines(Paths.get(VEGETABLES_FILE));
        assertEquals("Морква,41,0.5", lines.get(0));
        assertEquals("Помідор,18,0.3", lines.get(1));
    }

    @Test
    void testSaveSaladToFile() throws IOException {
        Salad salad = new Salad();
        salad.addVegetable(new Vegetable("Салат", 15, 0.2));
        salad.addVegetable(new Vegetable("Огірок", 16, 0.1));

        VegetableFileManager.saveSaladToFile(salad);

        List<String> lines = Files.readAllLines(Paths.get(SALAD_FILE));
        assertEquals("Салат,15,0.2", lines.get(0));
        assertEquals("Огірок,16,0.1", lines.get(1));
    }

    @Test
    void testLoadVegetablesFromFile() throws IOException {
        List<String> lines = List.of("Перець,40,0.4", "Цибуля,44,0.2");
        Files.write(Paths.get(VEGETABLES_FILE), lines);

        ArrayList<Vegetable> vegetables = VegetableFileManager.loadVegetablesFromFile();

        assertEquals(2, vegetables.size());
        assertEquals("Перець", vegetables.get(0).getName());
        assertEquals(40, vegetables.get(0).getCalories());
        assertEquals(0.4, vegetables.get(0).getWeight());

        assertEquals("Цибуля", vegetables.get(1).getName());
        assertEquals(44, vegetables.get(1).getCalories());
        assertEquals(0.2, vegetables.get(1).getWeight());
    }

    @Test
    void testLoadSaladFromFile() throws IOException {
        List<String> lines = List.of("Часник,23,0.1", "Капуста,35,0.15");
        Files.write(Paths.get(SALAD_FILE), lines);

        Salad salad = VegetableFileManager.loadSaladFromFile();

        assertEquals(2, salad.getIngredients().size());
        assertEquals("Часник", salad.getIngredients().get(0).getName());
        assertEquals(23, salad.getIngredients().get(0).getCalories());
        assertEquals(0.1, salad.getIngredients().get(0).getWeight());

        assertEquals("Капуста", salad.getIngredients().get(1).getName());
        assertEquals(35, salad.getIngredients().get(1).getCalories());
        assertEquals(0.15, salad.getIngredients().get(1).getWeight());
    }

    @Test
    void testWriteToFileIOException() {
        assertDoesNotThrow(() -> {
            VegetableFileManager.writeToFile("/invalid_path/vegetables.txt", new ArrayList<>());
        });
    }

    @Test
    void testLoadFromFileIOException() {
        assertDoesNotThrow(() -> {
            ArrayList<Vegetable> vegetables = VegetableFileManager.loadFromFile("/invalid_path/vegetables.txt");
            assertEquals(0, vegetables.size());
        });
    }

    @Test
    void testLoadVegetablesFromMalformedFile() throws IOException {
        List<String> lines = List.of("MalformedLineWithoutCommas");
        Files.write(Paths.get(VEGETABLES_FILE), lines);

        ArrayList<Vegetable> vegetables = VegetableFileManager.loadVegetablesFromFile();

        assertEquals(0, vegetables.size());
    }

    @Test
    void testLoadSaladFromMalformedFile() throws IOException {
        List<String> lines = List.of("InvalidDataFormat");
        Files.write(Paths.get(SALAD_FILE), lines);

        Salad salad = VegetableFileManager.loadSaladFromFile();

        assertEquals(0, salad.getIngredients().size());
    }
}
