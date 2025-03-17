package org.project.object.weapons;

import org.project.entity.Entity;

import java.util.ArrayList;

// TODO: UPDATE IMPLEMENTATION (Done)
public class Sword extends Weapon{
    /*
    THIS IS AN EXAMPLE OF A WEAPON DESIGN.
    */

    int abilityCharge = 0;
    int extraDamage = 0;

    public Sword() {
        // TODO: DESIGN SWORD'S ATTRIBUTES IMPLEMENT THE CONSTRUCTOR
        super(15, 5);
    }

    // TODO: (BONUS) UPDATE THE UNIQUE ABILITY (Done)
    public void uniqueAbility(ArrayList<Entity> targets) {
        abilityCharge += 2;
        if (abilityCharge == 20) {
            abilityCharge = 0;
            extraDamage = 10;
        }
        for (Entity target : targets) {
            target.takeDamage(getDamage() + extraDamage);
        }
        extraDamage = 0;
    }
}
