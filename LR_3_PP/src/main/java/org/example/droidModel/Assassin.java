package org.example.droidModel;

import org.example.visualizer.ConsoleVisualizer;
import java.util.Random;

public class Assassin extends Droid {
    private final int WEAPON_DAMAGE_BOOST = 15;
    private final int WEAPON_PRECISION_BOOST = 25;

    public Assassin(String name, boolean hasWeapon) {
        super(name, 80, 25, 30, hasWeapon);
    }

    @Override
    protected void applyWeapon() {
        this.damage += WEAPON_DAMAGE_BOOST;
        this.precision += WEAPON_PRECISION_BOOST;
    }

    @Override
    protected int getWeaponDamageBoost() {
        return WEAPON_DAMAGE_BOOST;
    }

    @Override
    public void superAttack(Droid opponent, ConsoleVisualizer visualizer) {
        if (superAttackUsed) {
            visualizer.printGreen("Супер атака вже використана.");
            return;
        }
        Random rand = new Random();
        if (rand.nextInt(100) < this.precision) {
            int superDamage = this.damage * 3;
            opponent.receiveDamage(superDamage);
            visualizer.printSuperAttack(this, opponent, superDamage);
        } else {
            visualizer.printMiss(this, true);
        }
        superAttackUsed = true;
    }
}
