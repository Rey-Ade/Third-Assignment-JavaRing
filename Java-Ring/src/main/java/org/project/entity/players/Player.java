package org.project.entity.players;

import org.project.entity.Entity;
import org.project.object.armors.Armor;
import org.project.object.armors.Shield;
import org.project.object.consumables.Consumable;
import org.project.object.weapons.Weapon;

import java.util.ArrayList;

public abstract class Player implements Entity{
    protected String name;
    protected String className;
    private String abilityName;
    Weapon weapon;
    Armor armor;
    Shield shield = new Shield();
    ArrayList<Consumable> items = new ArrayList<>();
    private int hp;
    private int maxHP = 100;
    private int mp;
    private int maxMP = 100;
    private boolean isdefending = false;
    protected boolean specialAbility = true; // can use special ability

    public Player(String name, String className, int hp, int mp, Weapon weapon, Armor armor, String abilityName) {
        this.className = className;
        this.name = name;
        this.abilityName = abilityName;
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
        System.out.println(name + className + " chose to defend.");
        isdefending = true;
    }

    // TODO: (BONUS) UPDATE THE FORMULA OF TAKING DAMAGE
    @Override
    public void takeDamage(int damage) {
        armor.checkBreak();
        if (isdefending) {
            shield.checkBreak();
            // player has no protection
            if (armor.isBroke() && shield.isBroke()) {
                hp -= damage;
                System.out.println(name + className + " took " + damage + " damage!");
            }
            // player has no armor
            else if (!shield.isBroke() && armor.isBroke()) {
                if (shield.getDefense() >= damage) {
                    System.out.println(name + className + " took 0 damage!");
                    shield.takeDamage(damage);
                }
                else {
                    hp -= damage - shield.getDefense();
                    System.out.println(name + className + " took " + (damage - shield.getDefense()) + " damage!");
                    shield.takeDamage(shield.getDefense());
                }
            }
            // player has no shield
            else if (shield.isBroke() && !armor.isBroke()) {
                if (armor.getDefense() >= damage) {
                    System.out.println(name + className + " took 0 damage!");
                    armor.takeDamage(damage);
                }
                else {
                    hp -= damage - armor.getDefense();
                    System.out.println(name + className + " took " + (damage - armor.getDefense()) + " damage!");
                    shield.takeDamage(armor.getDefense());
                }
            }
            // player has both armor and shield
            else {
                // shield takes all the damage
                if (shield.getDefense() >= damage) {
                    System.out.println(name + className + " took 0 damage!");
                    shield.takeDamage(damage);
                }
                else {
                    // player takes no damage
                    if (shield.getDefense() + armor.getDefense() >= damage) {
                        System.out.println(name + className + " took 0 damage!");
                        shield.takeDamage(shield.getDefense());
                        armor.takeDamage(damage - shield.getDefense());
                    }
                    // players takes damage
                    else  {
                        hp -= damage - armor.getDefense() - shield.getDefense();
                        System.out.println(name + className + " took " + (damage - armor.getDefense() - shield.getDefense())
                                + " damage!");
                        shield.takeDamage(shield.getDefense());
                        armor.takeDamage(armor.getDefense());
                    }
                }
            }
            isdefending = false;
        }
        else {
            if (!armor.isBroke()) {
                if (armor.getDefense() >= damage) {
                    System.out.println(name + className + " took 0 damage!");
                    armor.takeDamage(damage);
                }
                else {
                    hp -= damage - armor.getDefense();
                    System.out.println(name + className + " took " + (damage - armor.getDefense()) + " damage!");
                    armor.takeDamage(armor.getDefense());
                }
            }
            else {
                hp -= damage;
                System.out.println(name + className + " took " + damage + " damage!");
            }
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

    public void resetAbility(){
        specialAbility = true;
    }

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

    public Shield getShield() {
        return shield;
    }

    @Override
    public String getClassName() {
        return className;
    }

    public String getAbilityName() {
        return abilityName;
    }

    public boolean getSpecialAbility() {
        return specialAbility;
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

    public void setIsDefending(boolean isdefending) {
        this.isdefending = isdefending;
    }

    public boolean getIsdefending() {
        return isdefending;
    }
}
