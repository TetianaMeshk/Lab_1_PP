package command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import salad.Vegetable;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShowAllVegetablesTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private PrintStream originalOut;

    @BeforeEach
    public void setUp() {
        originalOut = System.out;
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testExecuteWithEmptyVegetables() {

        ArrayList<Vegetable> vegetables = new ArrayList<>();
        ShowAllVegetables command = new ShowAllVegetables(vegetables);

        command.execute();

        assertEquals("Овочів немає.", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testExecuteWithNonEmptyVegetables() {
        ArrayList<Vegetable> vegetables = new ArrayList<>();
        vegetables.add(new Vegetable("Помідор", 18, 100));
        vegetables.add(new Vegetable("Огірок", 16, 100));
        ShowAllVegetables command = new ShowAllVegetables(vegetables);

        command.execute();

        String expectedOutput = "Овочі:\r\n"
                + "Помідор (100.0гр, 18 ккал/100г)\n"
                + "Огірок (100.0гр, 16 ккал/100г)\n";

        assertEquals(expectedOutput.trim(), outputStreamCaptor.toString().trim());
    }
}
