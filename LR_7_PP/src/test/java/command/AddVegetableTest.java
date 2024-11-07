package command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import salad.Vegetable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class AddVegetableTest {

    private List<Vegetable> vegetables;
    private AddVegetable addVegetable;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalSystemOut = System.out;
    private InputStream originalSystemIn = System.in;

    @BeforeEach
    void setUp() {
        vegetables = new ArrayList<>();
        addVegetable = new AddVegetable(vegetables);
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalSystemOut);
        System.setIn(originalSystemIn);
    }

    @Test
    void testAddDuplicateVegetable() {
        vegetables.add(new Vegetable("Помідор", 100, 200));
        String input = "Помідор\n150\n250\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        addVegetable.execute();

        assertEquals(1, vegetables.size());
    }

    @Test
    void testAddNewVegetable() {
        String input = "Помідор\n100\n200\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        addVegetable.execute();

        assertEquals(1, vegetables.size());
        assertEquals("Помідор", vegetables.get(0).getName());
        assertEquals(100, vegetables.get(0).getCalories());
        assertEquals(200, vegetables.get(0).getWeight());
    }

    @Test
    void testGetIntInputValid() {
        String input = "10\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Scanner scanner = new Scanner(System.in);
        int result = addVegetable.getIntInput(scanner, "Введіть калорійність на 100гр: ");

        assertEquals(10, result);
    }

    @Test
    void testGetIntInputInvalid() {
        String input = "abc\n20\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Scanner scanner = new Scanner(System.in);
        int result = addVegetable.getIntInput(scanner, "Введіть калорійність на 100гр: ");

        assertEquals(20, result);
    }

    @Test
    void testGetDoubleInputValid() {
        String input = "150.5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Scanner scanner = new Scanner(System.in);
        double result = addVegetable.getDoubleInput(scanner, "Введіть вагу (в грамах): ");

        assertEquals(150.5, result);
    }

    @Test
    void testGetDoubleInputInvalid() {
        String input = "abc\n100.2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Scanner scanner = new Scanner(System.in);
        double result = addVegetable.getDoubleInput(scanner, "Введіть вагу (в грамах): ");

        assertEquals(100.2, result);
    }

    @Test
    void testVegetableAddedSuccessfully() {
        String input = "Морква\n30\n250\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        addVegetable.execute(new Scanner(System.in));

        assertEquals(1, vegetables.size());
        Vegetable vegetable = vegetables.get(0);
        assertEquals("Морква", vegetable.getName());
        assertEquals(30, vegetable.getCalories());
        assertEquals(250, vegetable.getWeight());
    }

    @Test
    void testGetDoubleInputHandlesInvalidData() {
        String input = "text\n123.45\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Scanner scanner = new Scanner(System.in);
        double result = addVegetable.getDoubleInput(scanner, "Введіть числове значення:");

        assertEquals(123.45, result);
    }

    @Test
    void testGetIntInputHandlesInvalidData() {
        String input = "неЦілеЗначення\n10\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Scanner scanner = new Scanner(System.in);
        int result = addVegetable.getIntInput(scanner, "Введіть ціле значення:");

        assertEquals(10, result);
    }
}
