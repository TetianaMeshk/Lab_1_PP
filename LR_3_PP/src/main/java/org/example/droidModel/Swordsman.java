package org.example.droidModel;

import org.example.visualizer.ConsoleVisualizer;

public class Swordsman extends Droid {
    private int defense;
    private final int WEAPON_DAMAGE_BOOST = 20;
    private final int WEAPON_DEFENSE_BOOST = 15;

    public Swordsman(String name, boolean hasWeapon) {
        super(name, 120, 15, 70, hasWeapon);
        this.defense = 10;
    }

    @Override
    protected void applyWeapon() {
        this.damage += WEAPON_DAMAGE_BOOST;
        this.defense += WEAPON_DEFENSE_BOOST;
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
        int superDamage = this.damage + this.defense;
        opponent.receiveDamage(superDamage);
        superAttackUsed = true;
        visualizer.printSuperAttack(this, opponent, superDamage);
    }
}
