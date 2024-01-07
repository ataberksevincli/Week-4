import java.util.Scanner;

public class Game {
    private Scanner input = new Scanner(System.in);

    public void start() {
        System.out.println("Macera oyununa hoşgeldiniz !");
        System.out.print("Lütfen bir isim giriniz : ");
        String playerName = input.nextLine();
        Player player = new Player(playerName);
        System.out.println("Sayın " + player.getPlayerName() + " adaya hoşgeldiniz !!");
        System.out.println("Lütfen bir karakter seçiniz : ");
        player.selectCharacter();

        while (true) {
            player.printInfo();
            System.out.println();
            Location location = null;
            System.out.println("Bölgeler");
            System.out.println();
            System.out.println("1- Güvenli Ev --> Burası sizin için güvenli");
            System.out.println("2- Eşya Dükkanı --> Silah veya Zırh satın alabilirsiniz.");
            System.out.println("3- Mağara --> Mağaraya gir, dikkatli ol Zombi çıkabilir!.");
            System.out.println("4- Orman --> Ormana gir, dikkatli ol Vampir çıkabilir !.");
            System.out.println("5- Nehir --> Nehire gir, dikkatli ol Ayı çıkabilir !.");
            System.out.println("6- Maden --> Madene gir, dikkatli ol Yılan çıkabilir !.");
            System.out.println("0- Oyunu Sonlandır --> Çıkış yapıp oyunu sonlandırabilirsin.");
            System.out.println();
            System.out.print("Lütfen gitmek istediğiniz bölgeyi seçiniz : ");
            int selectLoc = input.nextInt();

            switch (selectLoc) {
                case 0:
                    location = null;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    break;

                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    location = new Cave(player);
                    break;
                case 4:
                    location = new Forest(player);
                    break;
                case 5:
                    location = new River(player);
                    break;
                case 6:
                    location = new Mine(player);
                    break;

                default:
                    System.out.println("Lütfen geçerli bir bölge giriniz !.");

            }

            if (player.getInventory().isFood() && player.getInventory().isWater() && player.getInventory().isFirewood()){
                System.out.println("Tebrikler !! Oyunu kazandınız. Gerekli Tüm Eşyaları Topladın !");
                break;
            }

            if (location == null) {
                System.out.println("Oyun bitti, umarım tekrar oynarsın.");
                break;
            }
            if (!location.onLocation()) {
                System.out.println("Game over!");
                break;
            }

        }
    }
}
