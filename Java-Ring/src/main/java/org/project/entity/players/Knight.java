package org.project.entity.players;

import org.project.entity.Entity;
import org.project.object.armors.Armor;
import org.project.object.armors.PlateArmor;
import org.project.object.weapons.*;

// TODO: UPDATE IMPLEMENTATION (Done)
public class Knight extends Player {
    // TODO: DESIGN KNIGHT'S WEAPON AND ARMOR AND IMPLEMENT THE CONSTRUCTOR (Done)

    private int strongKick = 20;
    public Knight (String name, Sword sword, PlateArmor PlateArmor) {
        super(name, 100, 40, sword, PlateArmor);
    }

    public void specialAttack(Entity target) {
        target.takeDamage(strongKick);
    }
}
