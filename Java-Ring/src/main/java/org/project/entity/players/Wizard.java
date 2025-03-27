package org.project.entity.players;

import org.project.entity.Entity;
import org.project.object.armors.MageArmor;
import org.project.object.weapons.Scepter;

public class Wizard extends Player{
    // Can cast special spell once per battle

    private int spellHeal = 40;
    private int spellDamage = 30;

    public Wizard(String name, Scepter scepter, MageArmor mageArmor) {
        super(name, " (Wizard)", 100, 100, scepter, mageArmor, "Special spell");
    }

    @Override
    public void specialAttack(Entity target) {
        System.out.println(name + className + " casted Special Spell!");
        heal(spellHeal);
        System.out.println(name + className + " healed " + spellHeal + " health!");
        System.out.println(name + className + " has " + getHp() + " health remaining.");
        target.takeDamage(spellDamage);
        specialAbility = false;
    }
}
