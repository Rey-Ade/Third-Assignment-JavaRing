package org.project.entity.players;

import org.project.entity.Entity;
import org.project.object.armors.Armor;
import org.project.object.armors.PlateArmor;
import org.project.object.weapons.*;

public class Knight extends Player {

    private int Kick = 35;
    private int round = 0;

    public Knight (String name, Sword sword, PlateArmor PlateArmor) {
        super(name, " (Knight)",100, 40, sword, PlateArmor, "Strong kick");
    }

    @Override
    public void specialAttack(Entity target) {
        System.out.println(name + className + " strong kicked " + target.getClassName() + "!");
        target.takeDamage(Kick);
        specialAbility = false;
        round = 0;
    }

    @Override
    public void attack(Entity target) {
        super.attack(target);
        round++;
        if (round == 3) {
            specialAbility = true;
        }
    }

    public int getKick() {
        return Kick;
    }
}
