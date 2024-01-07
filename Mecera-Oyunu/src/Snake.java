import java.util.Random;

public class Snake extends Enemies{
    int randomDamage;
    static Random rand = new Random();
    public Snake() {
        super(4,"Yılan", getRandomDamage(),12,0);
        this.randomDamage = getRandomDamage();
    }

    public static int getRandomDamage() {
        int max = 6,min = 3;
        int snakeRandomDamage = rand.nextInt(max - min + 1) + min;
        return snakeRandomDamage;
    }
    public void setRandomDamage(int randomDamage) {
        this.randomDamage = randomDamage;
    }


}
