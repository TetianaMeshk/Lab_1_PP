package salad;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SaladTest {
    private Salad salad;
    private Vegetable carrot;
    private Vegetable cucumber;

    @BeforeEach
    public void setUp() {
        salad = new Salad();
        carrot = new Vegetable("Морква", 322, 25);
        cucumber = new Vegetable("Огірок", 155, 15);
    }

    @Test
    public void testAddVegetable() {
        salad.addVegetable(carrot);
        assertTrue(salad.getIngredients().contains(carrot));
    }

    @Test
    public void testAddVegetableDuplicate() {
        salad.addVegetable(cucumber);
        salad.addVegetable(cucumber);
        assertEquals(1, salad.getIngredients().size());
    }

    @Test
    public void testRemoveVegetable() {
        salad.addVegetable(carrot);
        salad.removeVegetable(carrot);
        assertFalse(salad.getIngredients().contains(carrot));
    }

    @Test
    public void testToStringEmpty() {
        assertEquals("Салат не має інгредієнтів.", salad.toString());
    }

    @Test
    public void testToStringWithIngredients() {
        Salad salad = new Salad();
        Vegetable carrot = new Vegetable("Морква", 25, 322);
        Vegetable cucumber = new Vegetable("Огірок", 15, 155);

        salad.addVegetable(carrot);
        salad.addVegetable(cucumber);

        String expectedOutput = "Інгредієнти салату:\nМорква (322.0гр, 25 ккал/100г)\n\nОгірок (155.0гр, 15 ккал/100г)";
        String actualOutput = salad.toString().trim();
        assertEquals(expectedOutput, actualOutput);
    }
}
