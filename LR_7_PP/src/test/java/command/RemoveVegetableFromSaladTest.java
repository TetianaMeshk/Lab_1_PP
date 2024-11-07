package command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import salad.Salad;
import salad.Vegetable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RemoveVegetableFromSaladTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private PrintStream originalOut;
    private Salad salad;

    @BeforeEach
    public void setUp() {
        originalOut = System.out;
        System.setOut(new PrintStream(outputStreamCaptor));
        salad = new Salad();
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testExecuteRemoveExistingVegetable() {

        Vegetable carrot = new Vegetable("Морква", 41, 100);
        salad.addVegetable(carrot);
        RemoveVegetableFromSalad command = new RemoveVegetableFromSalad(salad);

        String input = "Морква\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        command.execute();

        assertEquals("Введіть назву овоча для видалення з салату: Морква видалено з салату.", outputStreamCaptor.toString().trim());
        assertEquals(0, salad.getIngredients().size());
    }

    @Test
    public void testExecuteRemoveNonExistingVegetable() {

        Vegetable carrot = new Vegetable("Морква", 41, 100);
        salad.addVegetable(carrot);
        RemoveVegetableFromSalad command = new RemoveVegetableFromSalad(salad);

        String input = "Помідор\n"; // Вводимо неіснуючий овоч
        System.setIn(new ByteArrayInputStream(input.getBytes())); // Перенаправляємо ввід
        command.execute();

        assertEquals("Введіть назву овоча для видалення з салату: Інгредієнт Помідор відсутній в салаті.", outputStreamCaptor.toString().trim());
        assertEquals(1, salad.getIngredients().size()); // Перевіряємо, що овоч залишився
    }
}
