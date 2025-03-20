package org.project.entity.players;

import org.project.entity.Entity;
import org.project.object.armors.Armor;
import org.project.object.armors.PlateArmor;
import org.project.object.weapons.*;

public class Knight extends Player {

    private int Kick = 35;
    private boolean usedKick = true;

    public Knight (String name, Sword sword, PlateArmor PlateArmor) {
        super(name, " (Knight)",100, 40, sword, PlateArmor);
    }

    public void specialAttack(Entity target) {
        target.takeDamage(Kick);
    }

    public int getKick() {
        return Kick;
    }

    public boolean getUseKick() {
        return usedKick;
    }

    public void setUsedKick(boolean kick) {
        usedKick = kick;
    }
}
