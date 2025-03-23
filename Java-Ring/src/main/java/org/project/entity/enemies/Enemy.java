package org.project.entity.enemies;

import org.project.entity.Entity;
import org.project.entity.players.Player;
import org.project.object.weapons.Weapon;

public abstract class Enemy implements Entity {
    private String className;
    protected int hp;
    private int mp;
    private int maxHP;
    private int maxMP;

    public Enemy(String className, int hp, int mp) {
        this.className = className;
        this.hp = hp;
        maxHP = hp;
        this.mp = mp;
        maxMP = mp;
    }

    // TODO: (BONUS) UPDATE THE FORMULA OF TAKING DAMAGE
    @Override
    public void takeDamage(int damage) {
        hp -= damage;
        System.out.println(className + " took " + damage + " damage!");
        if (hp < 0) {
            hp = 0;
        }
    }

    @Override
    public void attack(Entity target) {
        System.out.println(className + " attacked " + ((Player) target).getName() + target.getClassName() + "!");
    }

    @Override
    public void displayHP() {
        System.out.println(className + " has " + hp + " health remaining.");
        if (hp == 0) {
            System.out.println(className + " is defeated!");
        }
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

    @Override
    public boolean isAlive() {
        return hp > 0;
    }
}
