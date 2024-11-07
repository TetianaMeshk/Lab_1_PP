package command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import salad.Salad;
import salad.Vegetable;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class SortByCaloriesTest {

    private Salad salad;
    private SortByCalories sortCommand;

    @BeforeEach
    void setUp() {
        salad = new Salad();
        sortCommand = new SortByCalories(salad);
    }

    @Test
    void testSortByCalories() {

        Vegetable tomato = new Vegetable("Помідор", 18, 100);
        Vegetable cucumber = new Vegetable("Огірок", 16, 150);
        Vegetable carrot = new Vegetable("Морква", 41, 50);

        salad.addVegetable(cucumber);
        salad.addVegetable(tomato);
        salad.addVegetable(carrot);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream originalOut = System.out;
        System.setOut(printStream);

        sortCommand.execute();

        System.setOut(originalOut);

        String expectedOutput = "Інгредієнти салату відсортовані за калорійністю:\r\n" +
                "Огірок (150.0гр, 16 ккал/100г)\n" +
                "Помідор (100.0гр, 18 ккал/100г)\n" +
                "Морква (50.0гр, 41 ккал/100г)\n";

        String actualOutput = outputStream.toString();
        assertEquals(expectedOutput, actualOutput, "Вивід не збігається! Очікуваний: " + expectedOutput + ", фактичний: " + actualOutput);

        assertEquals(cucumber, salad.getIngredients().get(0));
        assertEquals(tomato, salad.getIngredients().get(1));
        assertEquals(carrot, salad.getIngredients().get(2));
    }

}
