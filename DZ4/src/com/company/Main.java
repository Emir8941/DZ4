package com.company;

import java.util.Random;

public class Main {

    public static int bossHealth = 1100;
    public static int bossDamage = 100;
    public static String bossDefenceType = "";
    public static int[] heroesHealth = {300, 285, 290, 255, 400, 250, 255,300};
    public static int[] heroesDamage = {20, 25, 15, 0, 5, 20, 22,30};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medical", "Golem", "Lucky", "Berserk","Thor"};

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) { // !false = true / !true = false
            round();
        }
    }

    public static void round() {
        if (bossHealth > 0) {
            chooseBossDefence();
            thor();
        }



        
        medical();
        lucky();
        heroesHit();
        heal();
        printStatistics();
    }

    public static void heal() {
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0, 1, 2
        bossDefenceType = heroesAttackType[randomIndex];
        System.out.println("Boss chose defence: " + bossDefenceType);
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes win!!!");
            return true;
        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                continue;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss win!!!");
        }
        return allHeroesDead;
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (bossDefenceType == heroesAttackType[i]) {
                    Random random = new Random();
                    int coeff = random.nextInt(8) + 2; // 2, 3, 4, 5, 6, 7, 8, 9
                    bossHealth = checkHealth(bossHealth - heroesDamage[i] * coeff);
                    System.out.println(heroesAttackType[i] + " hits boss critically " + heroesDamage[i] * coeff);
                } else {
                    bossHealth = checkHealth(bossHealth - heroesDamage[i]);
                }
            }
        }
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                heroesHealth[i] = checkHealth(heroesHealth[i] - bossDamage);
            }
        }
    }

    public static void medical() {
        if (heroesHealth[3] > 0)
            for (int i = 0; i < heroesHealth.length; i++) {
                if (heroesHealth[i] < 100 && heroesHealth[i] > 0) {
                    heroesHealth[i] += 70;
                    System.out.println("Medical help:" + heroesAttackType[i]);

                    break;
                }
            }
    }

    public static void berserk() {
        if (heroesHealth[6] > 0)
            for (int i = 0; i < heroesHealth.length; i++) {
                heroesHealth[6] += 50;
                bossHealth -= 50;
                break;
            }
    }


    public static void lucky() {
        if (heroesHealth[5] > 0) {
            for (int i = 0; i < heroesAttackType.length; i++) {
                Random random = new Random();
                int den = random.nextInt(7);
                if (den > 6) {
                    heroesHealth[5] += 100;
                    System.out.println("Lucky dodged");

                    break;


                }
            }
        }
    }


    public static void golem() {
        if (heroesHealth[4] > 0) {
            for (int i = 0; i < heroesHealth.length; i++) {

            }
        }
        else {

        }

    }public static void thor(){
        if (heroesHealth[7]>0){
            Random random =new Random();
            boolean torRandom= random.nextBoolean();
            if (torRandom){
                System.out.println("Tor attack boss");
            }else {
                berserk();
                bossHits();
                golem();
            }
        }
    }






    public static int checkHealth(int health) {
        if (health < 0) {
            return 0;
        } else {
            return health;
        }
    }

    public static void printStatistics() {
        System.out.println("________________");
        System.out.println("Boss health: " + bossHealth + " [" + bossDamage + "]");
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i] + " [" + heroesDamage[i] + "]");
        }
        System.out.println("________________");
    }
}
