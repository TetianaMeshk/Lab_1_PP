package org.example.command;

import org.example.salad.Salad;

public class CalculateCalories implements Command {
    private Salad salad;

    public CalculateCalories(Salad salad) {
        this.salad = salad;
    }

    @Override
    public void execute() {
        double totalCalories = salad.getIngredients().stream()
                .mapToDouble(v -> v.getCalories() * v.getWeight() / 100)
                .sum();
        System.out.println("Загальна калорійність салату: " + totalCalories);
    }
}
