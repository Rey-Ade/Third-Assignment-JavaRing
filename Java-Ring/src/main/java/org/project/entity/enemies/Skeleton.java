package org.project.entity.enemies;

import org.project.entity.Entity;
import org.project.object.weapons.Sword;
import org.project.object.weapons.Sword;


public class Skeleton extends Enemy{

    private boolean resurrected = false;
    Sword sword = new Sword();

    public Skeleton() {
        super("Skeleton", 80, 40);
    }

    public void specialAbility() {
        if (!resurrected && hp == 0) {
            hp = 80;
            resurrected = true;
            System.out.println("Skeleton has been resurrected!");
        }
    }

    @Override
    public void attack(Entity target) {
        super.attack(target);
        target.takeDamage(sword.getDamage());
    }
}
