package org.project.entity.players;

import org.project.entity.Entity;
import org.project.object.weapons.Sword;
import org.project.object.armors.LeatherArmor;

public class Assassin extends Player{
    // Can become invisible once per battle

    private int counter = 0;
    private boolean invisible = false;

    public Assassin(String name, Sword sword, LeatherArmor leatherArmor) {
        super(name, 100, 50, sword, leatherArmor);
    }

    @Override
    public void attack(Entity target) {
        if (!invisible) {
            target.takeDamage(weapon.getDamage());
        }
        else if (invisible && counter < 3){
            target.takeDamage(weapon.getDamage() + 20);
            counter++;
        }
        else {
            resetInvisible();
        }
    }

    @Override
    public void takeDamage(int damage) {
        if (!invisible) {
            super.takeDamage(damage);
        }
    }

    public void becomeInvisible() {
        invisible = true;
    }

    public void resetInvisible() {
        invisible = false;
        counter = 0;
    }
}
