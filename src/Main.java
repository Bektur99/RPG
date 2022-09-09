import java.util.Random;

public class Main {
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefenceType;
    public static int[] heroesHealth = {220, 270, 265,320,300,250,260,450};
    public static int[] heroesDamage = {25, 20, 15,0,22,10,15,16};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic","Medic","thor","lucy","Berserk","Golem"};
    public static int roundNumber = 0;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            playRound();

        }

    }

    public static void hp(){
        for (int i = 0; i < heroesHealth.length; i++) {
            if (i==3){
                continue;
            }
            if ( heroesHealth[3]>0&&heroesHealth[i]<100 && heroesHealth[i] > 0){
                heroesHealth[i]+=50;
            }
            System.out.println("Medic healed: "+heroesAttackType[i]);
            break;
        }
    }public static void thor(){
        Random random=new Random();
        boolean grom = random.nextBoolean();
        if (grom){
            bossDamage=0;
            System.out.println("Boss defence");
        }else {
            bossDamage=50;
        }

    }public static void lucky(){
        Random random=new Random();
        int mimo = random.nextInt(5)+1;
        switch (mimo) {
            case 1:
                heroesHealth[5] = heroesHealth[5] + bossDamage;
                System.out.println("lacy");
            case 2:

            case 3:

            case 4:
        }
    }
    public static void Golem(){
        for (int i = 0; i < heroesHealth.length ; i++) {
            if (heroesHealth[7] > 0 && heroesHealth[i] > 0 && heroesHealth[7] != heroesDamage[i]){
                heroesHealth[i] += bossDamage / 5;
                heroesHealth[7] -= bossDamage / 5;
            }


        }
    }
    public  static void Berserk(){
        Random random=new Random();
        int randomDamage = random.nextInt(15)+1;
        int randomB = random.nextInt(3)+1;
        if (heroesHealth[6]>0 && bossHealth>0) {
            switch (randomB) {
                case 1:
                    heroesDamage[6] = (heroesDamage[6]+ bossDamage)-randomDamage;
                    System.out.println("Берсерка урон критический");
                    System.out.println("потерие при увиличение урон а Берсерка"+randomDamage);
                    break;
                case 2:
                    bossDamage= 50;
                    break;
                case 3:
                    bossDamage=50;
                    break;
            }
        }
    }

    public static void playRound() {
        roundNumber++;
        chooseBossDefence();
        bossHits();
        heroesHit();
        printStatistics();
        hp();
        thor();
        lucky();
        Berserk();
        Golem();
    }

    public static void printStatistics() {
        System.out.println("ROUND " + roundNumber + " ---------------");
        /*String defence;
        if (bossDefenceType == null) {
            defence = "No defence";
        } else {
            defence = bossDefenceType;
        }*/
        System.out.println("Boss health: " + bossHealth + "; damage: "
                + bossDamage + "; defence: " + (bossDefenceType == null ? "No defence" : bossDefenceType));
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i] + "; damage: "
                    + heroesDamage[i]);
        }
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0,1,2
        bossDefenceType = heroesAttackType[randomIndex];
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                int hit = heroesDamage[i];
                if (heroesAttackType[i] == bossDefenceType) {
                    Random random = new Random();
                    int coeff = random.nextInt(6) + 2; // 2,3,4,5,6,7
                    hit = heroesDamage[i] * coeff;
                    System.out.println("Critical damage: " + hit);
                }
                if (bossHealth - hit < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - hit;
                }

            }
        }
    }
}