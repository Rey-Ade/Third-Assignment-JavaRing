package org.project.entity.players;

import org.project.entity.Entity;
import org.project.object.weapons.Scimitar;
import org.project.object.armors.LeatherArmor;

public class Assassin extends Player{
    // Can become invisible once per battle

    private int counter = 0;
    private int extraDamage = 10;
    public boolean isInvisible = false;

    public Assassin(String name, Scimitar scimitar, LeatherArmor leatherArmor) {
        super(name, " (Assassin)", 100, 50, scimitar, leatherArmor, "Invisibility");
    }

    @Override
    public void attack(Entity target) {
        if (isInvisible && counter < 3){
            specialAttack(target);
        }
        else {
            super.attack(target);
            isInvisible = false;
        }
    }

    @Override
    public void specialAttack(Entity target) {
        specialAbility= false;
        isInvisible = true;
        System.out.println(name + className + "became invisible!");
        System.out.println(name + className + " attacked " + target.getClassName() + "!");
        target.takeDamage(weapon.getDamage() + extraDamage);
        counter++;
    }

    @Override
    public void takeDamage(int damage) {
        if (!isInvisible) {
            super.takeDamage(damage);
        }
    }

    @Override
    public void resetAbility() {
        super.resetAbility();
        isInvisible = false;
        counter = 0;
    }
}
