package command;

import salad.Salad;

public class ShowSalad implements Command {
    private Salad salad;

    public ShowSalad(Salad salad) {
        this.salad = salad;
    }

    @Override
    public void execute() {
        if (salad.getIngredients().isEmpty()) {
            System.out.println("Салат не має інгредієнтів.");
        } else {
            System.out.println("Інгредієнти салату:");
            salad.getIngredients().forEach(System.out::print);
        }
    }
}
