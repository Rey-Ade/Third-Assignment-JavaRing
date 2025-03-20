package org.project.object.weapons;

import org.project.entity.Entity;
import org.project.object.Object;

public abstract class Weapon implements Object {
    private int damage;
    private int manaCost;

    public Weapon(int damage, int manaCost) {
        this.damage = damage;
        this.manaCost = manaCost;
    }

    @Override
    public void use(Entity target) {
        target.takeDamage(damage);
    }

    public int getDamage() {
        return damage;
    }

    public int getManaCost() {
        return manaCost;
    }
}
