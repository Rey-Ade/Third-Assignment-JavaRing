package org.project.object.weapons;

import org.project.entity.Entity;

import java.util.ArrayList;

public class Sword extends Weapon{

    int abilityCharge = 0;
    int extraDamage = 0;

    public Sword() {
        super(25, 10);
    }

    public void uniqueAbility(Entity target) {
        abilityCharge += 2;
        if (abilityCharge == 20) {
            abilityCharge = 0;
            extraDamage = 10;
            target.takeDamage(getDamage() + extraDamage);
        }
        extraDamage = 0;
    }
}
