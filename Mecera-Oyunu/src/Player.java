import java.util.Scanner;

public class Player {
    private int damage;
    private int health;
    private int originalHealth;
    private int gold;
    private String playerName;
    private String characterName;
    private Scanner input = new Scanner(System.in);
    private Inventory inventory;


    public Player(String playerName) {

        this.playerName = playerName;
        this.inventory = new Inventory();
    }

    public void selectCharacter() {
        GameCharacter[] charList = {new Samurai(), new Archer(), new Knight()};
        System.out.println();
        System.out.println("Karakterler :");
        System.out.println("------------------");
        for (GameCharacter gameCharacter : charList) {
            System.out.println("ID => " + gameCharacter.getId() + " Karakter : " + gameCharacter.getCharacterName() +
                    "\t Hasar => " + gameCharacter.getDamage() +
                    "\t Sağlık => " + gameCharacter.getHealth() +
                    "\t Gold => " + gameCharacter.getGold());


        }
        System.out.println("------------------");
        System.out.print("Lütfen bir karakter ID'si seçiniz : ");
        int selectCharacter = input.nextInt();

        switch (selectCharacter) {
            case 1:
                initPlayer(new Samurai());
                break;

            case 2:
                initPlayer(new Archer());
                break;

            case 3:
                initPlayer(new Knight());
                break;
            default:
                initPlayer(new Samurai());
        }

        System.out.println(" Seçtiğiniz Karakter : " + this.characterName +
                "\t Hasar => " + this.damage +
                "\t Sağlık => " + this.health +
                "\t Gold => " + this.gold);

    }


    public void initPlayer(GameCharacter gameCharacter) {
        this.setCharacterName(gameCharacter.getCharacterName());
        this.setDamage(gameCharacter.getDamage());
        this.setHealth(gameCharacter.getHealth());
        this.setOriginalHealth(gameCharacter.getHealth());
        this.setGold(gameCharacter.getGold());
    }

    public void printInfo() {
        System.out.println();
        System.out.println("Silahınız : " + this.getInventory().getWeapon().getName() +
                ",\tZırhınız : " + this.getInventory().getArmor().getName() +
                ",\tBlocklama => " + this.getInventory().getArmor().getBlock() +
                ",\tHasarınız => " + this.getTotalDamage() +
                ",\t Sağlık => " + this.getHealth() +
                ",\t Gold => " + this.getGold());


    }

    public int getDamage() {
        return damage;
    }

    public int getTotalDamage(){

            return damage + this.getInventory().getWeapon().getDamage();
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if (health < 0){
            health = 0;
        }
        this.health = health;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public Scanner getInput() {
        return input;
    }

    public void setInput(Scanner input) {
        this.input = input;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory() {
        this.inventory = inventory;
    }
    public Weapon getWeapon(){

        return this.getInventory().getWeapon();
    }

    public int getOriginalHealth() {
        return originalHealth;
    }

    public void setOriginalHealth(int originalHealth) {
        this.originalHealth = originalHealth;
    }
}
