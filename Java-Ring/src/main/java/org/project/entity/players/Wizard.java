package org.project.entity.players;

import org.project.entity.Entity;
import org.project.object.armors.MageArmor;
import org.project.object.weapons.Scepter;

public class Wizard extends Player{
    // Can cast special spell once per battle

    private int spellHeal = 40;
    private int spellDamage = 30;
    private boolean castSpell = false;

    public Wizard(String name, Scepter scepter, MageArmor mageArmor) {
        super(name, " (Wizard)", 100, 100, scepter, mageArmor);
    }

    @Override
    public void specialAttack(Entity target) {
        System.out.println(name + className + " casted Special Spell!");
        System.out.println(name + className + " healed " + spellHeal + " health!");
        heal(spellHeal);
        target.takeDamage(spellDamage);
        castSpell = true;
    }

    public boolean getCastSpell() {
        return castSpell;
    }

    public int getSpellDamage() {
        return spellDamage;
    }

    public int getSpellHeal() {
        return spellHeal;
    }

    @Override
    public void resetAbility() {
        castSpell = false;
    }
}
