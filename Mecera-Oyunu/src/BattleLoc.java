import java.util.Random;

public abstract class BattleLoc extends Location {


    private Enemies enemies;
    private String award;
    private int maxObstacle;
    static Random rand = new Random();

    public BattleLoc(Player player, String name, Enemies enemies, String award, int maxObstacle) {
        super(player, name);
        this.enemies = enemies;
        this.award = award;
        this.maxObstacle = maxObstacle;
    }

    @Override
    public boolean onLocation() {
        int numberOfEnemies = randomEnemiesNumber();
        System.out.println("Şu an buradasınız -->   " + this.getName());
        System.out.println("Dikkatli ol! Burada " + numberOfEnemies + " adet " + this.getEnemies().getName() + " yaşıyor ! ");
        System.out.print("<S>avaş veya <K>aç. S/K ? : ");
        String selectBattleCase = input.nextLine();
        selectBattleCase = selectBattleCase.toUpperCase();
        if (selectBattleCase.equals("S") && combat(numberOfEnemies)) {
            System.out.println(this.getName() + " tüm düşmanları yendiniz.");
            if (this.award.equals("Food") && !getPlayer().getInventory().isFood()) {
                System.out.println(this.award + " Kazandınız : ");
                getPlayer().getInventory().setFood(true);
            }else if (this.award.equals("Firewood") && !getPlayer().getInventory().isFirewood()) {
                System.out.println(this.award + " Kazandınız : ");
                getPlayer().getInventory().setFirewood(true);
            }else if (this.award.equals("Water") && !getPlayer().getInventory().isWater()) {
                System.out.println(this.award + " Kazandınız : ");
                getPlayer().getInventory().setWater(true);
            }
            return true;
        }

        if (this.getPlayer().getHealth() <= 0) {
            System.out.println("Öldünüz.");
            return false;
        }
        return true;

    }

    public boolean combat(int enemiesNumber) {

        for (int i = 1; i <= enemiesNumber; i++) {
            this.getEnemies().setHealth(this.getEnemies().getOriginalHealth());
            playerStats();
            getEnemiesStats(i);
            Random firstAttackNumber = new Random();

           int firstAttack = firstAttackNumber.nextInt(2) + 1;
            while (this.getPlayer().getHealth() > 0 && this.getEnemies().getHealth() > 0) {
                System.out.print("<V>ur veya <K>aç! : ");
                String selectCombat = input.nextLine().toUpperCase();

                if (selectCombat.equals("V") && firstAttack == 1) {
                    System.out.println();
                    System.out.println("SALDIRI SIRASI SENDE");
                    System.out.println("Siz vurdunuz !");
                    this.getEnemies().setHealth(this.getEnemies().getHealth() - this.getPlayer().getTotalDamage());
                    afterHit();
                    if (this.getEnemies().getHealth() > 0) {
                        System.out.println();
                        System.out.println("Canavar size atak yaptı ! ");
                        int enemiesDamage = this.getEnemies().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                        if (enemiesDamage < 0) {
                            enemiesDamage = 0;
                        }
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - enemiesDamage);
                        afterHit();
                    }

                } else if (selectCombat.equals("V")) {
                    System.out.println();
                    System.out.println("SALDIRI SIRASI KARŞI TARAFTA");
                    System.out.println();
                    if (this.getEnemies().getHealth() > 0) {
                        System.out.println();
                        System.out.println("Canavar size atak yaptı ! ");
                        System.out.println();
                        int enemiesDamage = this.getEnemies().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                        if (enemiesDamage < 0) {
                            enemiesDamage = 0;
                        }
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - enemiesDamage);
                        afterHit();
                        System.out.println();
                        System.out.println("Siz vurdunuz !");
                        System.out.println();
                        this.getEnemies().setHealth(this.getEnemies().getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();
                    }
                } else {
                    return false;
                }
            }
            if (this.getEnemies().getHealth() < this.getPlayer().getHealth()) {
                System.out.println("Düşmanı Yendiniz !");
                System.out.println();
                if (this.getEnemies().getName().equals("Yılan")){
                    getLuckofAward();
                }else {
                    System.out.println(this.getEnemies().getAward() + " para kazandınız ! ");
                }
                this.getPlayer().setGold(this.getPlayer().getGold() + this.getEnemies().getAward());
                System.out.println("Güncel Paranız : " + this.getPlayer().getGold());
                System.out.println();
            } else {
                return false;
            }
        }

        return true;
    }


// Düşman eğer yılansa bu kod bloğu ödül kısmı için çalışır
    public void getLuckofAward() {
        int snakeAward = rand.nextInt(100) + 1;
        if (snakeAward <= 15){
            int weaponAward = rand.nextInt(100) + 1;
            if (weaponAward <= 20){
                this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(3));
                System.out.println();
                System.out.println("Tüfek Kazandın");
                System.out.println();
            }if (weaponAward > 20 && weaponAward <= 30){
                this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(2));
                System.out.println();
                System.out.println("Kılıç Kazandın");
                System.out.println();
            }else if (weaponAward > 30){
                this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(1));
                System.out.println();
                System.out.println("Tabanca Kazandın");
                System.out.println();
            }

        }if (snakeAward > 15 && snakeAward <= 30){
            int armorAward = rand.nextInt(100) + 1;
            if (armorAward <= 20){
                this.getPlayer().getInventory().setArmor(Armor.getArmorObjByID(3));
                System.out.println();
                System.out.println("Ağır Zırh Kazandın");
                System.out.println();

            }if (armorAward > 20 && armorAward <= 30){
                this.getPlayer().getInventory().setArmor(Armor.getArmorObjByID(2));
                System.out.println();
                System.out.println("Orta Zırh Kazandın");
                System.out.println();
            }else if (armorAward > 30){
                this.getPlayer().getInventory().setArmor(Armor.getArmorObjByID(1));
                System.out.println();
                System.out.println("Hafif Zırh Kazandın");
                System.out.println();
            }
        }if (snakeAward > 30 && snakeAward <= 55){
            int goldAward = rand.nextInt(100) + 1;
            if (goldAward <= 20){
                this.getPlayer().setGold(this.getPlayer().getGold() + 10);
                System.out.println();
                System.out.println("10 Gold Kazandın");
                System.out.println();

            }if (goldAward > 20 && goldAward <= 30){
                this.getPlayer().setGold(this.getPlayer().getGold() + 5);
                System.out.println();
                System.out.println("5 Gold Kazandın");
                System.out.println();
            }else if (goldAward > 30){
                this.getPlayer().setGold(this.getPlayer().getGold() + 1);
                System.out.println();
                System.out.println("1 Gold Kazandın");
                System.out.println();
            }
        }if (snakeAward > 55){
            System.out.println("Maalesef Hiçbir şey Kazanamadın ! ");
        }
    }


    public void afterHit() {
        System.out.println("Canınız : " + this.getPlayer().getHealth());
        System.out.println(this.getEnemies().getName() + " Canı : " + this.getEnemies().getHealth());
        System.out.println("----------");


    }

    public void playerStats() {

        System.out.println("Oyuncu Değerleri ");
        System.out.println("-----------------");
        System.out.println("Sağlık : " + this.getPlayer().getHealth());
        System.out.println("Silah : " + this.getPlayer().getWeapon().getName());
        System.out.println("Zırh : " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Blocklama : " + this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Hasar : " + this.getPlayer().getTotalDamage());
        System.out.println("Para : " + this.getPlayer().getGold());
        System.out.println();

    }

    public void getEnemiesStats(int i) {

        System.out.println(i + "." + this.getEnemies().getName() + " Statları");
        System.out.println("-----------------");
        System.out.println("Sağlık : " + this.getEnemies().getHealth());
        if (getEnemies().getName().equals("Yılan")) {
            System.out.println("Hasar : " + Snake.getRandomDamage());
        }else{
            System.out.println("Hasar : " + this.getEnemies().getDamage());
        }
        if (getEnemies().getName().equals("Yılan")){
            System.out.println("Bu korkutucu Yılan'ın ödülü gizli ! : ");
            System.out.println();
        }else{
            System.out.println("Ödül : " + this.getEnemies().getAward());
            System.out.println();
        }


    }


    public int randomEnemiesNumber() {
        Random r = new Random();

        return r.nextInt(this.getMaxObstacle()) + 1;
    }

    public Enemies getEnemies() {
        return enemies;
    }

    public void setEnemies(Enemies enemies) {
        this.enemies = enemies;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }

}
