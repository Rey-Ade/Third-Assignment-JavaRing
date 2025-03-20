package org.project.entity.enemies;

import org.project.entity.Entity;
import org.project.object.weapons.Weapon;

public abstract class Enemy implements Entity {
    private String className;
    Weapon weapon;
    protected int hp;
    private int mp;
    private int maxHP;
    private int maxMP;

    public Enemy(String className, int hp, int mp, Weapon weapon) {
        this.className = className;
        this.hp = hp;
        maxHP = hp;
        this.mp = mp;
        maxMP = mp;
        this.weapon = weapon;
    }

    // TODO: (BONUS) UPDATE THE FORMULA OF TAKING DAMAGE
    @Override
    public void takeDamage(int damage) {
        hp -= damage;
        if (hp < 0) {
            hp = 0;
        }
    }

    @Override
    public void attack(Entity target) {
        target.takeDamage(weapon.getDamage());
    }

    @Override
    public void defend() {
        // TODO: (BONUS) IMPLEMENT A DEFENSE METHOD FOR SHIELDS
    }

    @Override
    public void heal(int health) {
        hp += health;
        if (hp > maxHP) {
            hp = maxHP;
        }
    }

    public int getHp() {
        return hp;
    }

    @Override
    public int getMaxHP() {
        return maxHP;
    }

    public int getMp() {
        return mp;
    }

    @Override
    public int getMaxMP() {return maxMP;}

    @Override
    public String getClassName() { return className; }

    public Weapon getWeapon() {
        return weapon;
    }
}
