package org.example.droidModel;

import org.example.visualizer.ConsoleVisualizer;

import java.util.Random;

public abstract class Droid {
    protected String name;
    protected int health;
    protected int initialHealth;
    protected int damage;
    protected int precision; // Точність атаки
    protected boolean hasWeapon;
    protected boolean superAttackUsed = false;

    public Droid(String name, int health, int damage, int precision, boolean hasWeapon) {
        this.name = name;
        this.health = health;
        this.initialHealth = health;
        this.damage = damage;
        this.precision = precision; // Ініціалізуємо точність
        this.hasWeapon = hasWeapon;
        if (hasWeapon) {
            applyWeapon();
        }
    }

    public void attack(Droid opponent, ConsoleVisualizer visualizer) {
        Random rand = new Random();
        // Врахування точності для атаки
        if (rand.nextInt(100) < this.precision) {
            int totalDamage = this.damage;
            if (hasWeapon) {
                totalDamage += getWeaponDamageBoost();
            }
            opponent.receiveDamage(totalDamage);
            visualizer.printAttack(this, opponent, totalDamage);
        } else {
            visualizer.printMiss(this, false);
        }
    }

    public void receiveDamage(int dmg) {
        this.health -= dmg;
        if (this.health < 0) this.health = 0;
    }

    public boolean isSuperAttackUsed() {return this.superAttackUsed; }

    public boolean isHasWeapon() { return this.hasWeapon; }

    public boolean isAlive() {
        return this.health > 0;
    }

    public String getName() {
        return this.name;
    }

    public int getHealth() {
        return this.health;
    }

    public int getDamage() {
        return this.damage;
    }

    public String getType() {
        return this.getClass().getSimpleName();
    }

    public void resetSuperAttack() {
        this.superAttackUsed = false;
    }

    public void resetStats() {
        this.health = initialHealth; // Відновлюємо здоров'я
        resetSuperAttack(); // Скидаємо статус супер атаки
    }

    protected abstract int getWeaponDamageBoost();

    protected abstract void applyWeapon();

    public abstract void superAttack(Droid opponent, ConsoleVisualizer visualizer);
}
