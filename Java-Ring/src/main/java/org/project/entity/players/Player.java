package org.project.entity.players;

import org.project.entity.Entity;
import org.project.object.armors.Armor;
import org.project.object.consumables.Consumable;
import org.project.object.weapons.Weapon;

import java.util.ArrayList;

public abstract class Player implements Entity{
    protected String name;
    protected String className;
    Weapon weapon;
    Armor armor;
    ArrayList<Consumable> items = new ArrayList<>();
    private int hp;
    private int maxHP = 100;
    private int mp;
    private int maxMP = 100;

    public Player(String name, String className, int hp, int mp, Weapon weapon, Armor armor) {
        this.className = className;
        this.name = name;
        this.hp = hp;
        this.mp = mp;

        this.weapon = weapon;
        this.armor = armor;
    }

    @Override
    public void attack(Entity target) {
        System.out.println(name + className + " attacked " + target.getClassName() + "!");
        target.takeDamage(weapon.getDamage());
    }

    public abstract void specialAttack(Entity target);

    @Override
    public void defend() {
        // TODO: (BONUS) IMPLEMENT A DEFENSE METHOD FOR SHIELDS
    }

    // TODO: (BONUS) UPDATE THE FORMULA OF TAKING DAMAGE
    @Override
    public void takeDamage(int damage) {
        armor.checkBreak();
        if (armor.isBroke()) {
            hp -= damage;
            System.out.println(name + className + " took " + damage + " damage!");
        }
        else if (!armor.isBroke() && armor.getDefense() <= damage) {
            hp -= damage - armor.getDefense();
            System.out.println(name + className + " took " + (damage - armor.getDefense()) + " damage!");
            armor.takeDamage(armor.getDefense());
        }
        else {
            System.out.println(name + className + " took 0 damage!");
            armor.takeDamage(damage);
        }
        if (hp < 0) {
            hp = 0;
        }
    }

    @Override
    public void heal(int health) {
        hp += health;
        if (hp > maxHP) {
            hp = maxHP;
        }
    }

    @Override
    public void displayHP(){
        System.out.println(name + className + " has " + hp + " health remaining.");
    }

    public abstract void resetAbility();

    public String getName() {
        return name;
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
    public int getMaxMP() {
        return maxMP;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    @Override
    public String getClassName() {
        return className;
    }

    public void foundItem(Consumable item) {
        items.add(item);
    }

    public ArrayList<Consumable> itemsList() {
        return items;
    }

    @Override
    public boolean isAlive() {
        return hp > 0;
    }
}
