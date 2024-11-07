package salad;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VegetableTest {

    @Test
    void testConstructorAndGetters() {
        Vegetable vegetable = new Vegetable("Tomato", 18, 150.0);

        assertEquals("Tomato", vegetable.getName());
        assertEquals(18, vegetable.getCalories());
        assertEquals(150.0, vegetable.getWeight());
    }

    @Test
    void testToString() {
        Vegetable vegetable = new Vegetable("Cucumber", 16, 200.0);
        String expectedString = "Cucumber (200.0гр, 16 ккал/100г)\n";

        assertEquals(expectedString, vegetable.toString());
    }

    @Test
    void testEquals() {
        Vegetable vegetable1 = new Vegetable("Carrot", 41, 100.0);
        Vegetable vegetable2 = new Vegetable("Carrot", 40, 100.0);
        Vegetable vegetable3 = new Vegetable("Pepper", 20, 50.0);
        Vegetable vegetable4 = new Vegetable("Carrot", 41, 100.0);

        assertEquals(vegetable1, vegetable2);
        assertNotEquals(vegetable1, vegetable3);
        assertEquals(vegetable1, vegetable4);
    }


    @Test
    void testHashCode() {
        Vegetable vegetable1 = new Vegetable("Spinach", 23, 80.0);
        Vegetable vegetable2 = new Vegetable("Spinach", 23, 90.0);

        assertEquals(vegetable1.hashCode(), vegetable2.hashCode());
    }

    @Test
    void testHashCodeNotEqual() {
        Vegetable vegetable1 = new Vegetable("Beet", 40, 100.0);
        Vegetable vegetable2 = new Vegetable("Carrot", 40, 100.0); // Інша назва

        assertNotEquals(vegetable1.hashCode(), vegetable2.hashCode());
    }


    @Test
    void testNotEqualsWithNull() {
        Vegetable vegetable = new Vegetable("Onion", 40, 60.0);
        assertNotEquals(vegetable, null);
    }

    @Test
    void testNotEqualsWithDifferentClass() {
        Vegetable vegetable = new Vegetable("Garlic", 149, 20.0);
        assertNotEquals(vegetable, "Not a Vegetable");
    }

    @Test
    void testEqualsSameObject() {
        Vegetable vegetable = new Vegetable("Lettuce", 15, 50.0);
        assertEquals(vegetable, vegetable);
    }
    @Test
    void testEqualsDifferentClass() {
        Vegetable vegetable = new Vegetable("Radish", 16, 30.0);
        assertNotEquals(vegetable, new Object());
    }

}
