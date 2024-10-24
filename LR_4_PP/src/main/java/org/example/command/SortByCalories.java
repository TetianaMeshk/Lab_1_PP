package org.example.command;

import org.example.salad.Salad;

public class SortByCalories implements Command {
    private Salad salad;

    public SortByCalories(Salad salad) {
        this.salad = salad;
    }

    @Override
    public void execute() {
        salad.getIngredients().sort((v1, v2) -> Integer.compare(v1.getCalories(), v2.getCalories()));
        System.out.println("Інгредієнти салату відсортовані за калорійністю:");
        salad.getIngredients().forEach(System.out::print);
    }
}
