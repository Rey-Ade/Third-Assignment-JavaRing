package org.project.object.armors;

import org.project.entity.Entity;
import org.project.object.Object;

public abstract class Armor implements Object {
    private String name;
    private int defense;
    private int maxDefense;
    private int durability;
    private int maxDurability;
    private boolean isBroke;

    public Armor(String name, int defense, int durability) {
        this.name = name;
        this.defense = defense;
        this.durability = durability;
        this.maxDefense = defense;
        this.maxDurability = durability;
    }

    public void takeDamage(int damage) {
        durability -= damage / 3;
        if (durability <= 0) {
            durability = 0;
        }
    }

    public void checkBreak() {
        if (durability <= 0) {
            isBroke = true;
            defense = 0;
        }
    }

    // TODO: (BONUS) UPDATE THE REPAIR METHOD
    public void repair() {
        isBroke = false;
        defense = maxDefense;
        durability = maxDurability;
    }

    public void use(Entity target) {

    }

    public int getDefense() {
        return defense;
    }

    public int getDurability() {
        return durability;
    }

    public boolean isBroke() {
        return isBroke;
    }

    public String getName() { return name; }
}
