package org.project.entity.enemies;

import org.project.entity.Entity;
import org.project.entity.players.Player;

public class Dragon extends Enemy{

    private int damage = 40;

    public Dragon() {
        super("Dragon", 150, 60);
    }

    @Override
    public void attack(Entity target) {
        super.attack(target);
        if (((Player) target).getIsdefending()) {
            System.out.println("Dragon broke through " + ((Player) target).getName() + "'s" + target.getClassName() + " defense!");
            ((Player) target).setIsDefending(false);
        }
        target.takeDamage(damage);
    }
}
