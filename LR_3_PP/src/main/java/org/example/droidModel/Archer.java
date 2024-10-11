package org.example.droidModel;

import org.example.visualizer.ConsoleVisualizer;

public class Archer extends Droid {
    private final int WEAPON_DAMAGE_BOOST = 12;
    private final int WEAPON_RANGE_BOOST = 30;

    public Archer(String name, boolean hasWeapon) {
        super(name, 90, 18, 80, hasWeapon);
    }

    @Override
    protected void applyWeapon() {
        this.damage += WEAPON_DAMAGE_BOOST;
        // this.range += WEAPON_RANGE_BOOST; // Range is not being used in combat logic
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
        int superDamage = this.damage * 2; // Remove range dependency
        opponent.receiveDamage(superDamage);
        superAttackUsed = true;
        visualizer.printSuperAttack(this, opponent, superDamage);
    }
}
