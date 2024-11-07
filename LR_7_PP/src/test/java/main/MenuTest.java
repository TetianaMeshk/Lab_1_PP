package main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import salad.Salad;
import salad.Vegetable;
import command.*;
import file.VegetableFileManager;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MenuTest {

    private Menu menu;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
        menu = new Menu();
    }

    @Test
    public void testMenuInitialization() {
        assertNotNull(menu);
    }

    @Test
    public void testShowMenu() {
        menu.showMenu();
        String output = outputStream.toString();
        assertTrue(output.contains("Меню:"));
        assertTrue(output.contains("1. Додати овоч"));
        assertTrue(output.contains("2. Видалити овоч"));
        assertTrue(output.contains("2. Видалити овоч"));
        assertTrue(output.contains("3. Вивести всі овочі"));
        assertTrue(output.contains("4. Додати овоч до салату"));
        assertTrue(output.contains("5. Видалити овоч з салату"));
        assertTrue(output.contains("6. Підрахувати калорійність салату"));
        assertTrue(output.contains("7. Вивести інгредієнти салату"));
        assertTrue(output.contains("8. Відсортувати інгредієнти салату за вагою"));
        assertTrue(output.contains("9. Відсортувати інгредієнти салату за калорійністю"));
        assertTrue(output.contains("10. Знайти інгредієнти салату в діапазоні калорійності"));
        assertTrue(output.contains("11. Зберегти у файл"));
        assertTrue(output.contains("12. Вихід"));
    }

    @Test
    public void testGetUserChoiceValidInput() {
        System.setIn(new java.io.ByteArrayInputStream("1\n".getBytes()));
        int choice = menu.getUserChoice(new java.util.Scanner(System.in));
        assertEquals(1, choice);
    }

    @Test
    public void testGetUserChoiceInvalidInput() {
        System.setIn(new java.io.ByteArrayInputStream("неправильне\n1\n".getBytes()));
        int choice = menu.getUserChoice(new java.util.Scanner(System.in));
        assertEquals(1, choice);
    }

    @Test
    public void testAddVegetable() {
        ArrayList<Vegetable> vegetables = new ArrayList<>();

        Command addVegetable = new AddVegetable(vegetables);

        System.setIn(new java.io.ByteArrayInputStream("Морква\n40\n150\n".getBytes()));

        addVegetable.execute();
        assertTrue(vegetables.stream().anyMatch(v -> v.getName().equals("Морква")));
    }

    @Test
    public void testRemoveVegetable() {
        ArrayList<Vegetable> vegetables = new ArrayList<>();
        Vegetable vegetable = new Vegetable("Огірок", 15, 50);
        vegetables.add(vegetable);

        Salad salad = new Salad();

        Command removeVegetable = new RemoveVegetable(vegetables, salad);

        System.setIn(new java.io.ByteArrayInputStream("Огірок\n".getBytes()));

        removeVegetable.execute();
        assertFalse(vegetables.contains(vegetable));
    }

    @Test
    public void testShowAllVegetables() {
        ArrayList<Vegetable> vegetables = new ArrayList<>();
        vegetables.add(new Vegetable("Морква", 40, 150));
        vegetables.add(new Vegetable("Картопля", 77, 200));


        Command showAllVegetables = new ShowAllVegetables(vegetables);
        showAllVegetables.execute();

        String output = outputStream.toString();
        assertTrue(output.contains("Морква"));
        assertTrue(output.contains("Картопля"));
    }

    @Test
    public void testCalculateCalories() {
        Salad salad = new Salad();
        salad.addVegetable(new Vegetable("Огірок", 15, 50));

        Command calculateCalories = new CalculateCalories(salad);
        calculateCalories.execute();

        String output = outputStream.toString();
        assertTrue(output.contains("калорійність салату"));
    }

    @Test
    public void testSortByWeight() {
        Salad salad = new Salad();
        Vegetable tomato = new Vegetable("Помідор", 30, 100);
        Vegetable potato = new Vegetable("Картопля", 77, 200);
        salad.addVegetable(tomato);
        salad.addVegetable(potato);

        Command sortByWeight = new SortByWeight(salad);
        sortByWeight.execute();

        String output = outputStream.toString();
        assertTrue(output.contains("Помідор"));
    }

    @Test
    public void testSortByCalories() {
        Salad salad = new Salad();
        Vegetable tomato = new Vegetable("Помідор", 30, 100);
        Vegetable potato = new Vegetable("Картопля", 77, 200);
        salad.addVegetable(tomato);
        salad.addVegetable(potato);

        Command sortByCalories = new SortByCalories(salad);
        sortByCalories.execute();

        String output = outputStream.toString();
        assertTrue(output.contains("Помідор"));
    }

    @Test
    public void testSaveToFile() {
        ArrayList<Vegetable> vegetables = new ArrayList<>();
        vegetables.add(new Vegetable("Морква", 40, 100));

        VegetableFileManager fileManagerMock = Mockito.mock(VegetableFileManager.class);

        fileManagerMock.saveVegetablesToFile(vegetables);

        Mockito.verify(fileManagerMock).saveVegetablesToFile(vegetables);
    }


    @Test
    public void testExit() {
        System.setIn(new java.io.ByteArrayInputStream("12\n".getBytes()));
        menu.menu();
    }

}
