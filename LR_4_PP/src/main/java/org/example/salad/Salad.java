package org.example.salad;
import java.util.ArrayList;
import java.util.List;

public class Salad {
    private final List<Vegetable> ingredients;

    public Salad() {
        ingredients = new ArrayList<>();
    }

    public List<Vegetable> getIngredients() {
        return ingredients;
    }

    public void addVegetable(Vegetable vegetable) {
        if (!ingredients.contains(vegetable)) {
            ingredients.add(vegetable);
        } else {
            System.out.println(vegetable.getName() + " вже є в салаті.");
        }
    }

    public void removeVegetable(Vegetable vegetable) {
        ingredients.remove(vegetable);
    }

    @Override
    public String toString() {
        if (ingredients.isEmpty()) {
            return "Салат не має інгредієнтів.";
        } else {
            StringBuilder builder = new StringBuilder("Інгредієнти салату:\n");
            for (Vegetable vegetable : ingredients) {
                builder.append(vegetable.toString()).append("\n");
            }
            return builder.toString();
        }
    }
}
