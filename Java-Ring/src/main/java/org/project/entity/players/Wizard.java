package org.project.entity.players;

import org.project.entity.Entity;
import org.project.object.armors.MageArmor;
import org.project.object.weapons.Scepter;

public class Wizard extends Player{

    private int spellHeal = 40;
    private int spellDamage = 30;

    public Wizard(String name, Scepter scepter, MageArmor mageArmor) {
        super(name, 100, 100, scepter, mageArmor);
    }

    public void specialSpell(Entity target) {
        spendMana(40);
        heal(spellHeal);
        target.takeDamage(spellDamage);
    }
    
}
