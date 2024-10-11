package org.example.droidModel;

import org.example.visualizer.ConsoleVisualizer;
public class Mage extends Droid {
    private int energy;
    private final int WEAPON_DAMAGE_BOOST = 10;
    private final int WEAPON_ENERGY_BOOST = 20;

    public Mage(String name, boolean hasWeapon) {
        super(name, 100, 20, 50, hasWeapon);
        this.energy = 50;
    }

    @Override
    protected void applyWeapon() {
        this.damage += WEAPON_DAMAGE_BOOST;
        this.energy += WEAPON_ENERGY_BOOST;
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
        int superDamage = this.damage * 2 + this.energy;
        opponent.receiveDamage(superDamage);
        superAttackUsed = true;
        visualizer.printSuperAttack(this, opponent, superDamage);
    }
}
