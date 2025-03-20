package org.project.object.consumables;

import org.project.entity.Entity;

public class Flask extends Consumable{

    public Flask() {
        super("Flask", "Heals 10 HP.");
    }

    // TODO: (BONUS) UPDATE USE METHOD
    @Override
    public void use(Entity target) {
        target.heal(target.getMaxHP() / 10);
    }
}
