package org.project.object.consumables;

import org.project.entity.Entity;
import org.project.entity.players.Player;

public class Flask extends Consumable{

    public Flask() {
        super("Flask", "Heals 10 HP.");
    }

    // TODO: (BONUS) UPDATE USE METHOD
    @Override
    public void use(Entity target) {
        ((Player) target).heal(target.getMaxHP() / 10);
        System.out.println(((Player) target).getName()  + target.getClassName() + " healed 10 HP!");
        System.out.println(((Player) target).getName()  + target.getClassName() + " has " + ((Player) target).getHp() +
                            " health remaining.");
    }
}
