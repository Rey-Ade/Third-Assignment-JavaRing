package org.project.location;

import org.project.entity.enemies.Dragon;
import org.project.entity.enemies.Enemy;
import org.project.entity.enemies.Goblin;
import org.project.entity.enemies.Skeleton;

import java.util.ArrayList;
import java.util.Random;

public class Location {

    private String name;
    private int numberOfEnemies;
    private ArrayList<Enemy> enemies = new ArrayList<>();

    public Location(String name, int numberOfEnemies) {
        this.name = name;
        this.numberOfEnemies = numberOfEnemies;
        generateEnemies();
    }

    public void generateEnemies() {
        Random rand = new Random();
        for (int i = 0; i < numberOfEnemies; i++) {
            int num = rand.nextInt(3);
            switch (num) {
                case 0:
                    enemies.add(new Goblin());
                    break;
                case 1:
                    enemies.add(new Skeleton());
                    break;
                case 2:
                    enemies.add(new Dragon());
            }
        }
    }

    /*
    TODO: (BONUS) RESET EACH LOCATION AFTER PLAYER LEAVES
    */

    public String getName() {
        return name;
    }

    public ArrayList<Enemy> getEnemies() {return enemies;}
}
