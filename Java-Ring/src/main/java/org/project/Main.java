package org.project;

import org.project.location.Location;
import org.project.entity.players.*;
import org.project.entity.enemies.*;
import org.project.object.weapons.*;
import org.project.object.armors.*;
import org.project.object.consumables.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Location> locations = new ArrayList<>();

        Location valley = new Location("Valley of Oblivion", 1);
        Location mountain = new Location("Mountain of Enlightenment", 2);
        Location forest = new Location("Forest of Shimmer", 3);
        Location desert = new Location("Desert of the Lost", 4);

        locations.add(valley);
        locations.add(mountain);
        locations.add(forest);
        locations.add(desert);

        // Menu
        System.out.println("---------------------");
        System.out.println("Welcome to Java Ring!");
        System.out.println("---------------------");
        System.out.println("1. Play\n2. Exit");
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        switch (choice) {
            case 1:
                Player player = null;

                // Choose a character
                System.out.println("----------------------");
                System.out.println("Choose you character!");
                System.out.println("----------------------");
                System.out.println("1. Knight\n2. Assassin\n3. Wizard");
                int character = in.nextInt();
                System.out.println("Enter your name: ");
                String name = in.next();
                switch (character) {
                    case 1:
                        player = new Knight(name, new Sword(), new PlateArmor());
                        break;
                    case 2:
                        player = new Assassin(name, new Scimitar(), new LeatherArmor());
                        break;
                    case 3:
                        player = new Wizard(name, new Scepter(), new MageArmor());
                        break;
                    default:
                        System.out.println("Invalid choice. Choosing Knight...");
                        player = new Knight(name, new Sword(), new PlateArmor());
                }

                // Choose a location
                for (int counter = 0; counter < locations.size(); counter++) {
                    System.out.println("---------------------------------------");
                    System.out.println("Welcome to " + locations.get(counter).getName() + "!");
                    System.out.println("---------------------------------------");

                    // Choose an enemy
                    for (Enemy enemy : locations.get(counter).getEnemies()) {
                        System.out.println("------------------------------------");
                        System.out.println(player.getName() + player.getClassName() + " has encountered " +
                                           enemy.getClassName() + "!");
                        System.out.println("------------------------------------");
                        System.out.println("1. Battle\n2. Run away");
                        int option = in.nextInt();

                        // Move to another location
                        if (option == 2) {
                            break;
                        }

                        // repair armor and shield before battle begins
                        int repair = 0;
                        if (player.getShield().isBroke()) {
                            System.out.println("Do you want to repair your shield?");
                            System.out.println("1. Spend " + player.getShield().getManaCost() + " MP\n2. No");
                            repair = in.nextInt();
                        }
                        if (repair == 1) {
                            if (player.getMp() >= player.getShield().getManaCost()) {
                                player.getShield().repair();
                                player.spendMana(player.getShield().getManaCost());
                            }
                            else {
                                System.out.println("Not enough MP.");
                            }
                        }
                        if (player.getArmor().isBroke()) {
                            System.out.println("Do you want to repair your armor?");
                            System.out.println("1. Spend " + player.getArmor().getManaCost() + " MP\n2. No");
                            repair = in.nextInt();
                        }
                        if (repair == 1) {
                            if (player.getMp() >= player.getArmor().getManaCost()) {
                                player.getArmor().repair();
                                player.spendMana(player.getArmor().getManaCost());
                            }
                            else {
                                System.out.println("Not enough MP.");
                            }
                        }

                        player.resetAbility();

                        // Game loop
                        while (player.isAlive() && enemy.isAlive()) {
                            boolean exit = false;

                            //Menu
                            System.out.println("..................");
                            if (player.getSpecialAbility()) {
                                if (player.getShield().isBroke()) {
                                    System.out.println("1. Attack\n2. " + player.getAbilityName() +
                                            "\n3. --unavailable--\n4. Use item");
                                }
                                else {
                                    System.out.println("1. Attack\n2. " + player.getAbilityName() +
                                            "\n3. Defend\n4. Use item");
                                }
                            }
                            else {
                                if (player.getShield().isBroke()) {
                                    System.out.println("1. Attack\n2. --unavailable--\n3. --unavailable--\n4. Use item");
                                }
                                else {
                                    System.out.println("1. Attack\n2. --unavailable--\n3. Defend\n4. Use item");
                                }
                            }
                            System.out.println("..................");

                            int ans = in.nextInt();
                            switch (ans) {
                                case 1:
                                    player.attack(enemy);
                                    enemy.displayHP();
                                    break;
                                case 2:
                                    player.specialAttack(enemy);
                                    enemy.displayHP();
                                    break;
                                case 3:
                                    player.defend();
                                    break;
                                case 4:
                                    boolean useItem = false;
                                    while (!useItem) {
                                        System.out.println("Items:");
                                        for (int i = 0; i < player.itemsList().size(); i++) {
                                            System.out.println((i + 1) + ". " + player.itemsList().get(i).getName());
                                        }
                                        System.out.println("0. Exit");
                                        int pick = in.nextInt();
                                        if (pick == 0) {
                                            exit = true;
                                            break;
                                        }
                                        System.out.println("1. Use\n2. Info");
                                        int answer = in.nextInt();
                                        switch (answer) {
                                            case 1:
                                                player.itemsList().get(pick - 1).use(player);
                                                player.itemsList().remove(pick - 1);
                                                useItem = true;
                                                break;
                                            case 2:
                                                System.out.println(player.itemsList().get(pick - 1).getInfo());
                                        }
                                    }
                            }

                            // if player didn't use any items and exited the items menu, they can attack
                            if (exit) {
                                continue;
                            }

                            //enemy's turn
                            if (enemy instanceof Skeleton) {
                                ((Skeleton) enemy).specialAbility();
                            }

                            if (enemy.isAlive()) {
                                enemy.attack(player);
                                player.displayHP();
                                player.getArmor().displayDurability();
                                player.getShield().displayDurability();
                            }
                        }
                        if (player.isAlive()) {
                            // reward
                            System.out.println("-----------------------");
                            System.out.println(player.getName() + player.getClassName() + " got Flask!");
                            System.out.println("-----------------------");
                            player.foundItem(new Flask());
                        }
                        else {
                            System.out.println("You are dead.");
                            break;
                        }
                    }
                    if (!player.isAlive()) {
                        break;
                    }
                    if (counter < locations.size() - 1) {
                        System.out.println("------------------------------");
                        System.out.println("Moving to the next location...");
                        System.out.println("------------------------------");
                    }
                }
                if (player.isAlive()) {
                    System.out.println("---------------------------------------");
                    System.out.println("Congratulations! You finished the game!");
                    System.out.println("---------------------------------------");
                }
                break;
            case 2:
                break;
        }
    }
}