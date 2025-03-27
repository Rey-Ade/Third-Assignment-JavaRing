package org.project.object.consumables;

import org.project.object.Object;

public abstract class Consumable implements Object {
    private String name;
    private String info;

    public Consumable(String name, String info) {
        this.name = name;
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }
}
