package command;

import salad.Vegetable;

import java.util.ArrayList;

public class ShowAllVegetables implements Command {
    private ArrayList<Vegetable> vegetables;

    public ShowAllVegetables(ArrayList<Vegetable> vegetables) {
        this.vegetables = vegetables;
    }

    @Override
    public void execute() {
        if (vegetables.isEmpty()) {
            System.out.println("Овочів немає.");
        } else {
            System.out.println("Овочі:");
            vegetables.forEach(vegetable -> System.out.print(vegetable));
        }
    }
}
