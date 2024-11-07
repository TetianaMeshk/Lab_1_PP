package command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import salad.Salad;
import salad.Vegetable;

import static org.junit.jupiter.api.Assertions.*;

class SortByWeightTest {

    private Salad salad;
    private SortByWeight sortCommand;

    @BeforeEach
    void setUp() {
        salad = new Salad();
        sortCommand = new SortByWeight(salad);
    }

    @Test
    void testSortByWeight() {
        Vegetable tomato = new Vegetable("Помідор", 18, 100);
        Vegetable cucumber = new Vegetable("Огірок", 16, 150);
        Vegetable carrot = new Vegetable("Морква", 41, 50);

        salad.addVegetable(cucumber);
        salad.addVegetable(tomato);
        salad.addVegetable(carrot);

        sortCommand.execute();

        assertEquals(carrot, salad.getIngredients().get(0));
        assertEquals(tomato, salad.getIngredients().get(1));
        assertEquals(cucumber, salad.getIngredients().get(2));
    }
}
