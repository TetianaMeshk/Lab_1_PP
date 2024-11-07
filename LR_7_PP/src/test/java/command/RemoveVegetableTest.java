package command;
import command.RemoveVegetable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import salad.Salad;
import salad.Vegetable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RemoveVegetableTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private PrintStream originalOut;
    private Salad salad;
    private ArrayList<Vegetable> vegetables;

    @BeforeEach
    public void setUp() {
        originalOut = System.out;
        System.setOut(new PrintStream(outputStreamCaptor));

        salad = new Salad();
        vegetables = new ArrayList<>();
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testExecuteRemoveExistingVegetable() {

        Vegetable carrot = new Vegetable("Морква", 41, 100);
        vegetables.add(carrot);
        salad.addVegetable(carrot);
        RemoveVegetable command = new RemoveVegetable(vegetables, salad);

        // Act
        String input = "Морква\n"; // Вводимо назву овоча
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        command.execute();

        // Assert
        String expectedOutput = "Введіть назву овоча для видалення: Морква видалено з овочів.";
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
        assertEquals(0, salad.getIngredients().size());
    }

    @Test
    public void testExecuteRemoveNonExistingVegetable() {
        // Arrange
        Vegetable carrot = new Vegetable("Морква", 41, 100);
        vegetables.add(carrot);
        salad.addVegetable(carrot);
        RemoveVegetable command = new RemoveVegetable(vegetables, salad);

        // Act
        String input = "Помідор\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        command.execute();

        // Assert
        String expectedOutput = "Введіть назву овоча для видалення: Овоч Помідор не знайдено.";
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
        assertEquals(1, salad.getIngredients().size());
    }
}
