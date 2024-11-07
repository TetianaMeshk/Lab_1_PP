package command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import salad.Salad;
import salad.Vegetable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddVegetableToSaladTest {

    private Salad salad;
    private ArrayList<Vegetable> vegetables;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        salad = new Salad();
        vegetables = new ArrayList<>();
        vegetables.add(new Vegetable("Помідор", 18, 200));
        vegetables.add(new Vegetable("Огірок", 16, 100));

        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testExecuteVegetableFound() {
        System.setIn(new ByteArrayInputStream("Помідор\n".getBytes()));

        AddVegetableToSalad addCommand = new AddVegetableToSalad(salad, vegetables);
        addCommand.execute();

        assertEquals(1, salad.getIngredients().size());
        assertEquals("Помідор", salad.getIngredients().get(0).getName());

        assertTrue(outputStream.toString().contains("Помідор додано до салату."));
    }

    @Test
    void testExecuteVegetableNotFound() {
        System.setIn(new ByteArrayInputStream("Морква\n".getBytes()));

        AddVegetableToSalad addCommand = new AddVegetableToSalad(salad, vegetables);
        addCommand.execute();

        assertEquals(0, salad.getIngredients().size());

        assertTrue(outputStream.toString().contains("Овоч не знайдено."));
    }

    @Test
    void testExecuteVegetableAlreadyInSalad() {
        salad.addVegetable(new Vegetable("Огірок", 16, 100));

        System.setIn(new ByteArrayInputStream("Огірок\n".getBytes()));

        AddVegetableToSalad addCommand = new AddVegetableToSalad(salad, vegetables);
        addCommand.execute();

        assertEquals(1, salad.getIngredients().size());
        assertEquals("Огірок", salad.getIngredients().get(0).getName());

        assertTrue(outputStream.toString().contains("Огірок вже є в салаті."));
    }
}
