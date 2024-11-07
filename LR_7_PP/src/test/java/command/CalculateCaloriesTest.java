package command;

import org.junit.jupiter.api.Test;
import salad.Salad;
import salad.Vegetable;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculateCaloriesTest {

    @Test
    void testExecute() {

        Salad salad = new Salad();
        salad.addVegetable(new Vegetable("Помідор", 18, 200)); // 36 ккал
        salad.addVegetable(new Vegetable("Огірок", 16, 100)); // 16 ккал
        salad.addVegetable(new Vegetable("Морква", 41, 150)); // 61.5 ккал

        double expectedCalories = 36 + 16 + 61.5;

        CalculateCalories calculateCalories = new CalculateCalories(salad);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        calculateCalories.execute();

        String expectedOutput = "Загальна калорійність салату: " + expectedCalories + "\r\n";
        assertEquals(expectedOutput, outputStream.toString());

        System.setOut(System.out);
    }
}
