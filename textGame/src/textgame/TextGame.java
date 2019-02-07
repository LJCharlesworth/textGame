package textgame;

import java.util.Scanner;
import java.util.Random;

public class TextGame {

    public static Scanner input = new Scanner(System.in);
    public static Random rand = new Random();

    public static boolean loop = false;
    public static int userInput;
    public static character player = new character(5, 0, 20);

    public static void updateCharacter(int attack, int defence, int agility) {
        player.setAttack(player.getAttack() + attack);
        player.setDefence(player.getDefence() + defence);
        player.setAgility(player.getAgility() + agility);
    }

    public static void weapons() {
        String userInputStr;
        do {
            try {
                System.out.println("Pick a weapon:\n1. Sword\n2. Dagger\n3. Warhammer");
                userInput = input.nextInt();

                switch (userInput) {
                    case 1:
                        System.out.println("Sword added; add a shield? (y/n)");
                        userInputStr = input.next();
                        if (userInputStr.equalsIgnoreCase("y")) {
                            updateCharacter(9, 12, -10);
                            System.out.println("Shield added");
                        } else {
                            if (userInputStr.equalsIgnoreCase("n")) {
                                System.out.println("No shield added");
                                updateCharacter(10, 5, -5);
                            } else {
                                System.out.println("Invalid input");
                                loop = true;
                            }
                        }
                        break;

                    case 2:
                        updateCharacter(5, 2, 0);
                        System.out.println("Dagger added");
                        break;

                    case 3:
                        updateCharacter(15, 5, -12);
                        System.out.println("Warhammer added");
                        break;

                    default:
                        System.out.println("Invalid input");
                        loop = true;
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error:\n" + e);
                loop = true;
            }
        } while (loop == true);
    }

    public static void armour() {
        do {
            try {
                System.out.println("Pick a Armour:\n1. None\n2. Light\n3. Heavy");
                userInput = input.nextInt();

                switch (userInput) {
                    case 1:
                        System.out.println("No armour added");
                        break;

                    case 2:
                        updateCharacter(0, 4, -4);
                        System.out.println("Light armour added");
                        break;

                    case 3:
                        updateCharacter(-1, 8, -7);
                        System.out.println("Heavy armour added");
                        break;

                    default:
                        System.out.println("Invalid input");
                        loop = true;
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error:\n" + e);
                loop = true;
            }
        } while (loop == true);
    }

    public static void fight() {
        System.out.println("test");
        int turn = 0;
        character enemy = createEnemy();
        character[] players = {player, enemy};
        double[] hp = {100, 100};
        String[] playerTurn = {"Player", "Enemy"};
        int[] mod = new int[2];
        double random;
        double attack;
        double damage;
        
        while (hp[0] >= 0 && hp[1] >= 0) {
            turn += 1;
            mod[0] = turn % 2;
            mod[1] = (mod[0] + 1) % 2;
            System.out.println("\n\nTurn: " + turn + ", " + playerTurn[mod[0]] + "'s turn!\n");

            random = rand.nextInt(51);
            if (random <= players[mod[1]].getAgility()) {
                System.out.println("Attack was dodged!");
            } else {
                random = rand.nextInt(101) * 0.001 + 1;
                attack = players[mod[0]].getAttack() * (players[mod[0]].getAgility() * 0.001 + 1) * (random);
                random = rand.nextInt(101) * 0.001 + 1;
                damage = attack * random;
                random = rand.nextInt(101) * 0.001 + 1;
                damage -= 0.25 * (players[mod[1]].getDefence() * random);
                if (damage > 0) {
                    hp[mod[1]] -= damage;
                }else{
                    damage = 0;
                }
                System.out.println(playerTurn[mod[0]] + " attacked with " + attack + " power, doing " + damage + " damage\n" + playerTurn[mod[1]] + " now has an hp of " + hp[mod[1]]);
            }
        }
        if(hp[0] <= 0) {
                System.out.println("\n You lose!");
            } else {
                System.out.println("\n You win!");
            }
    }

    public static character createEnemy() {
        int[] enemyStats = new int[3];
        for (int i = 0; i < 3; i++) {
            enemyStats[i] = rand.nextInt(20) + 1;
        }
        character enemy = new character(enemyStats[0], enemyStats[1], enemyStats[2]);

        return enemy;
    }

    public static void main(String[] args) {
        weapons();
        armour();
        fight();
    }

}
