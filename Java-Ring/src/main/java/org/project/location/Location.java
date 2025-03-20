package org.project.location;

import org.project.entity.enemies.Enemy;

import java.util.ArrayList;

public class Location {

    private String name;
    private ArrayList<Location> locations;
    private ArrayList<Enemy> enemies;

    public Location(String name, ArrayList<Location> locations, ArrayList<Enemy> enemies) {
        this.name = name;
        this.locations = locations;
        this.enemies = enemies;
    }

    /*
    TODO: (BONUS) RESET EACH LOCATION AFTER PLAYER LEAVES
    */

    public String getName() {
        return name;
    }

    public ArrayList<Location> getLocations() {return locations;}

    public ArrayList<Enemy> getEnemies() {return enemies;}
}
