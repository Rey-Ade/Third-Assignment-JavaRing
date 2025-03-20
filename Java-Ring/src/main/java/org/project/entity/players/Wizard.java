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

    public void specialSpell(Entity target) {
        heal(spellHeal);
        target.takeDamage(spellDamage);
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

    public void setCastSpell(boolean cast) {
        castSpell = cast;
    }
}
