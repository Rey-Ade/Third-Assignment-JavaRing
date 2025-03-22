package org.project.entity.enemies;

import org.project.object.weapons.Sword;


public class Skeleton extends Enemy{

    private boolean resurrected = false;

    public Skeleton(Sword sword) {
        super("Skeleton", 80, 40, sword);
    }

    public void specialAbility() {
        if (!resurrected && hp == 0) {
            hp = 80;
            resurrected = true;
            System.out.println("Skeleton has been resurrected!");
        }
    }

    public boolean getResurrected() {
        return resurrected;
    }
}
