package command;

import salad.Salad;

public class SortByWeight implements Command {
    private Salad salad;

    public SortByWeight(Salad salad) {
        this.salad = salad;
    }

    @Override
    public void execute() {
        salad.getIngredients().sort((v1, v2) -> Double.compare(v1.getWeight(), v2.getWeight()));
        System.out.println("Інгредієнти салату відсортовані за вагою:");
        salad.getIngredients().forEach(System.out::print);
    }
}
