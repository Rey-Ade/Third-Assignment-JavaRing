package org.project.entity.enemies;

import org.project.entity.Entity;
import org.project.object.weapons.Dagger;

public class Goblin extends Enemy{

    Dagger dagger = new Dagger();

    public Goblin() {
        super("Goblin", 30, 0);
    }

    @Override
    public void attack(Entity target) {
        super.attack(target);
        target.takeDamage(dagger.getDamage());
    }
}
