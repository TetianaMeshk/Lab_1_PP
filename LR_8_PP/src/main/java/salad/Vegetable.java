package salad;
import java.util.Objects;

public class Vegetable {
    private final String name;
    private final int calories;
    private final double weight;

    public Vegetable(String name, int calories, double weight) {
        this.name = name;
        this.calories = calories;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getCalories() {
        return calories;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return name + " (" + weight + "гр, " + calories + " ккал/100г)\n";
    }

    // Перевизначення equals для правильного порівняння об'єктів
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vegetable vegetable = (Vegetable) obj;
        return name.equals(vegetable.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }


}
