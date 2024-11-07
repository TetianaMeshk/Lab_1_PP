package command;

import salad.Salad;
import salad.Vegetable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ShowSaladTest {
    private Salad salad;
    private ShowSalad showSalad;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        salad = new Salad();
        showSalad = new ShowSalad(salad);
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void testExecuteWithEmptySalad() {
        showSalad.execute();
        assertEquals("Салат не має інгредієнтів.", outputStreamCaptor.toString().trim());
    }

    @Test
    void testExecuteWithNonEmptySalad() {
        Vegetable carrot = new Vegetable("Морква", 41, 100);
        Vegetable cucumber = new Vegetable("Огірок", 16, 100);
        salad.addVegetable(carrot);
        salad.addVegetable(cucumber);

        showSalad.execute();

        String expectedOutput = "Інгредієнти салату:\r\n" +
                "Морква (100.0гр, 41 ккал/100г)\n" +
                "Огірок (100.0гр, 16 ккал/100г)\n";
        assertEquals(expectedOutput.trim(), outputStreamCaptor.toString().trim());
    }
}
