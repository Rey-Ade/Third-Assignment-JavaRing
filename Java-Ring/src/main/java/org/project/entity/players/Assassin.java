package org.project.entity.players;

import org.project.entity.Entity;
import org.project.object.weapons.Scimitar;
import org.project.object.armors.LeatherArmor;

public class Assassin extends Player{
    // Can become invisible once per battle

    private int counter = 0;
    private int extraDamage = 10;
    private boolean invisible = false;
    public boolean isInvisible = false;

    public Assassin(String name, Scimitar scimitar, LeatherArmor leatherArmor) {
        super(name, " (Assassin)", 100, 50, scimitar, leatherArmor);
    }

    @Override
    public void attack(Entity target) {
        if (invisible && counter < 3){
            target.takeDamage(weapon.getDamage() + extraDamage);
            counter++;
        }
        else {
            target.takeDamage(weapon.getDamage());
            isInvisible = false;
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
        isInvisible = true;
    }

    public void resetInvisible() {
        invisible = false;
        isInvisible = false;
        counter = 0;
    }

    public boolean getIsInvisible() {
        return isInvisible;
    }

    public boolean getInvisible() {
        return invisible;
    }

    public int getExtraDamage() {
        return extraDamage;
    }
}
