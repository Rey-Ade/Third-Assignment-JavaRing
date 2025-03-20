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
import java.util.Random;
import java.util.concurrent.locks.Condition;

public class Main {
    public static void main(String[] args) {
        List<Location> locations = new ArrayList<>();

        ArrayList<Enemy> hillEnemies = new ArrayList<>();
        hillEnemies.add(new Skeleton(new Sword()));
        hillEnemies.add(new Goblin(new Dagger()));
        hillEnemies.add(new Goblin(new Dagger()));
        Location hill = new Location("Valley of Oblivion", null, hillEnemies);

        ArrayList<Enemy> mountainEnemies = new ArrayList<>();
        mountainEnemies.add(new Skeleton(new Sword()));
        mountainEnemies.add(new Skeleton(new Sword()));
        mountainEnemies.add(new Goblin(new Dagger()));
        Location mountain = new Location("Mountain of Enlightenment", null, mountainEnemies);

        ArrayList<Enemy> woodsEnemies = new ArrayList<>();
        woodsEnemies.add(new Skeleton(new Sword()));
        woodsEnemies.add(new Skeleton(new Sword()));
        woodsEnemies.add(new Skeleton(new Sword()));
        woodsEnemies.add(new Skeleton(new Sword()));
        woodsEnemies.add(new Goblin(new Dagger()));
        Location woods = new Location("Forest of Shimmer", null, woodsEnemies);

        ArrayList<Enemy> desertEnemies = new ArrayList<>();
        desertEnemies.add(new Skeleton(new Sword()));
        desertEnemies.add(new Skeleton(new Sword()));
        desertEnemies.add(new Skeleton(new Sword()));
        //Dragon enemy
        Location desert = new Location("Desert of the Lost", null, desertEnemies);

        locations.add(hill);
        locations.add(mountain);
        locations.add(woods);
        locations.add(desert);

        // Menu
        System.out.print("Welcome!\n1. Play\n2. Exit\n");
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        switch (choice) {
            case 1:
                Player player = null;
                // Choose a character
                System.out.print("Choose you character!\n\n" +
                        "1. Knight\n" +
                        "Ability: strong kick - deals extra damage\n\n" +
                        "2. Assassin\n" +
                        "Ability: invisibility - immune to enemy attacks, deals extra damage\n\n" +
                        "3. Wizard\n" +
                        "Ability: special spell - heals himself, damages the enemy.\n");
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
                }
                int counter = 0;

                // chooses a location
                for (Location location : locations) {
                    System.out.println("Welcome to " + location.getName() + "!");
                    ArrayList<Enemy> enemies = location.getEnemies();

                    // chooses an enemy
                    for (Enemy enemy : enemies) {
                        System.out.println(player.getName() + player.getClassName() + " has encountered " +
                                           enemy.getClassName() + "!");
                        System.out.println("1. Battle\n2. Run away");
                        int option = in.nextInt();

                        // move to another location
                        if (option == 2) {
                            break;
                        }

                        // battle an enemy
                        if (player instanceof Assassin) {
                            ((Assassin) player).resetInvisible();
                        }
                        else if (player instanceof Wizard) {
                            ((Wizard) player).setCastSpell(false);
                        }

                        int round = 0;
                        // game loop
                        while (player.getHp() > 0 && enemy.getHp() > 0) {
                            boolean exit = false;
                            //special attack
                            if (player instanceof Knight) {
                                // strong kick takes 3 rounds to charge
                                if (round % 3 == 0 && round != 0) {
                                    System.out.println("1. Attack\n2. Strong kick\n3. Use item");
                                    ((Knight) player).setUsedKick(false);
                                }
                                // didn't use strong kick
                                else if (!((Knight) player).getUseKick() && round != 0) {
                                    System.out.println("1. Attack\n2. Strong kick\n3. Use item");
                                    round = 0;
                                }
                                // used strong kick
                                else {
                                    System.out.println("1. Attack\n2. --unavailable--\n3. Use item");
                                }
                            }
                            else if (player instanceof Assassin) {
                                // hasn't become invisible
                                if (!((Assassin) player).getInvisible()) {
                                    System.out.println("1. Attack\n2. Invisibility\n3. Use item");
                                }
                                // has become invisible
                                else {
                                    System.out.println("1. Attack\n2. --unavailable--\n3. Use item");
                                }
                            }
                            else {
                                // hasn't cast spell
                                if (!((Wizard) player).getCastSpell()) {
                                    System.out.println("1. Attack\n2. Special Spell\n3. Use item");
                                }
                                // has cast spell
                                else {
                                    System.out.println("1. Attack\n2. --unavailable--\n3. Use item");
                                }
                            }

                            int ans = in.nextInt();

                            // attack option
                            switch (ans) {
                                case 1:
                                    player.attack(enemy);
                                    System.out.println(player.getName() + player.getClassName() + " attacked " +
                                                       enemy.getClassName() + "!");

                                    // attacking in the invisible state
                                    if (player instanceof Assassin) {
                                        // is currently invisible
                                        if (((Assassin) player).getIsInvisible()) {
                                            System.out.println(enemy.getClassName() + " took " +
                                                    (player.getWeapon().getDamage() + ((Assassin) player).getExtraDamage())
                                                    + " damage!");
                                        }
                                        else {
                                            System.out.println(enemy.getClassName() + " took " +
                                                               player.getWeapon().getDamage() + " damage!");
                                        }
                                    }

                                    // normal attack
                                    else {
                                        System.out.println(enemy.getClassName() + " took " +
                                                           player.getWeapon().getDamage() + " damage!");
                                    }
                                    break;
                                // special attack
                                case 2:
                                    if (player instanceof Knight) {
                                        ((Knight) player).specialAttack(enemy);
                                        ((Knight) player).setUsedKick(true);
                                        System.out.println(player.getName() + player.getClassName() + " strong kicked " +
                                                enemy.getClassName() + "!\n" + enemy.getClassName() + " took " +
                                                ((Knight) player).getKick() + " damage!");
                                    } else if (player instanceof Wizard) {
                                        ((Wizard) player).specialSpell(enemy);
                                        ((Wizard) player).setCastSpell(true);
                                        System.out.println(player.getName() + player.getClassName() +
                                                " casted Special Spell!\n" + player.getName() + player.getClassName() +
                                                " healed " + ((Wizard) player).getSpellHeal() + " health!\n"
                                                + enemy.getClassName() + " took " + ((Wizard) player).getSpellDamage()
                                                + " damage!");
                                    } else {
                                        ((Assassin) player).becomeInvisible();
                                        player.attack(enemy);
                                        System.out.println(player.getName() + player.getClassName() + " became invisible!\n"
                                                + player.getName() + player.getClassName() + " attacked " +
                                                enemy.getClassName() + "!\n" + enemy.getClassName() + " took " +
                                                (player.getWeapon().getDamage() + ((Assassin) player).getExtraDamage())
                                                + " damage!");
                                    }
                                    break;
                                case 3:
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
                                                System.out.println("hello");
                                                System.out.println(player.itemsList().size());
                                                player.itemsList().remove(pick - 1);
                                                useItem = true;
                                                if (player.itemsList().get(pick - 1) instanceof Flask) {
                                                    System.out.println(player.getName() + player.getClassName() + " healed 10 HP!\n" +
                                                                     player.getName() + player.getClassName() + " has " + player.getHp() +
                                                                    " health remaining.");
                                                }
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
                            System.out.println(enemy.getClassName() + " has " + enemy.getHp() + " health remaining.");

                            if (enemy instanceof Skeleton && enemy.getHp() == 0 && !((Skeleton) enemy).getResurrected()) {
                                ((Skeleton) enemy).specialAbility();
                                System.out.println("Skeleton has been resurrected!");
                            }
                            else if (enemy.getHp() == 0) {
                                continue;
                            }

                            enemy.attack(player);
                            System.out.println(enemy.getClassName() + " attacked " + player.getName() +
                                               player.getClassName() + "!");

                            if (player.getArmor().isBroke()) {
                                System.out.println(player.getName() + player.getClassName() + " took " +
                                        enemy.getWeapon().getDamage() + " damage!");
                            }
                            else if (!player.getArmor().isBroke() && (enemy.getWeapon().getDamage() - player.getArmor().getDefense() < 0)) {
                                System.out.println(player.getName() + player.getClassName() + " took 0 damage!");
                                player.getArmor().takeDamage(enemy.getWeapon().getDamage());
                            }
                            else {
                                System.out.println(player.getName() + player.getClassName() + " took " +
                                        (enemy.getWeapon().getDamage() - player.getArmor().getDefense())
                                        + " damage!");
                                player.getArmor().takeDamage(player.getArmor().getDefense());
                            }

                            System.out.println(player.getName() + player.getClassName() + " has " + player.getHp() +
                                               " health remaining.");
                            System.out.println(player.getArmor().getName() + " has " +
                                               player.getArmor().getDurability() + " health remaining.");
                            round++;
                        }
                        if (player.getHp() == 0) {
                            System.out.println("You are dead");
                            break;
                        }
                        else {
                            System.out.println(player.getName() + player.getClassName() + " defeated " +
                                    enemy.getClassName() + "!");
                            // reward
                            System.out.println(player.getName() + player.getClassName() + " got Flask!");
                            player.foundItem(new Flask());
                        }
                    }
                    if (player.getHp() == 0) {
                        break;
                    }
                    if (counter < locations.size() - 1) {
                        System.out.println("Moving to the next location...\n");
                    }
                    counter++;
                }
                if (player.getHp() > 0) {
                    System.out.println("Congratulations! You finished the game!");
                }
                break;
            case 2:
                break;
        }
    }
}