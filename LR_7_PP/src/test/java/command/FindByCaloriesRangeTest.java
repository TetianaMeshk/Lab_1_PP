package command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import salad.Salad;
import salad.Vegetable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FindByCaloriesRangeTest {
    private Salad salad;
    private FindByCaloriesRange findByCaloriesRange;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        salad = new Salad();
        salad.addVegetable(new Vegetable("Морква", 41, 100));
        salad.addVegetable(new Vegetable("Огірок", 16, 150));
        salad.addVegetable(new Vegetable("Помідор", 18, 120));
        findByCaloriesRange = new FindByCaloriesRange(salad);

        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testExecuteWithValidRange() {
        String input = "15\n40\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        findByCaloriesRange.execute();

        String expectedOutput = "Введіть початкове значення діапазону:\r\n" +
                "Введіть кінцеве значення діапазону:\r\n" +
                "Огірок (150.0гр, 16 ккал/100г)\n" +
                "Помідор (120.0гр, 18 ккал/100г)\n";

        assertEquals(expectedOutput.trim(), outputStream.toString().trim());
    }

    @Test
    void testExecuteWithInvalidInputThenValidRange() {
        String input = "abc\n15\n40\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        findByCaloriesRange.execute();

        String expectedOutput = "Введіть початкове значення діапазону:\r\n" +
                "Неправильне введення. Будь ласка введіть ціле число:\r\n" +
                "Введіть кінцеве значення діапазону:\r\n" +
                "Огірок (150.0гр, 16 ккал/100г)\n" +
                "Помідор (120.0гр, 18 ккал/100г)\n";

        assertEquals(expectedOutput.trim(), outputStream.toString().trim());
    }

    @Test
    void testExecuteWithRangeOutsideOfIngredientsCalories() {
        String input = "50\n100\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        findByCaloriesRange.execute();

        String expectedOutput = "Введіть початкове значення діапазону:\r\n" +
                "Введіть кінцеве значення діапазону:";

        assertEquals(expectedOutput.trim(), outputStream.toString().trim());
    }
}
