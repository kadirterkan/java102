package patika.game.location.batteloc;

import patika.game.location.Location;
import patika.game.obstacle.Obstacle;
import patika.game.player.Player;

import java.util.Random;

public abstract class BattleLoc extends Location {

    protected Obstacle obstacle;
    private String award;
    private Integer maxObstacle;

    public BattleLoc(Player player, String name, Obstacle obstacle, String award, Integer maxObstacle) {
        super(player, name);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle = maxObstacle;
    }

    public BattleLoc(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public Integer randomObstacleNumber() {
        Random r = new Random();
        return r.nextInt(this.maxObstacle) + 1;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public Integer getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(Integer maxObstacle) {
        this.maxObstacle = maxObstacle;
    }

    public Boolean onLocation() {

        Integer obsNumber = this.randomObstacleNumber();


        System.out.println("Şuan buradasınız : " + this.getName());
        System.out.println("Dikkatli ol! Burada " + obsNumber + " tane " + this.obstacle.getName() + " yaşıyor !");
        System.out.println("<S>avaş veya <K>aç");
        String selectCase = scanner.nextLine();
        selectCase = selectCase.toUpperCase();
        if (selectCase.equals("S") && combat(obsNumber)) {
            System.out.println("-----------------");
            System.out.println(this.getName() + " tüm düşmanları yendiniz");
            return true;
        } else if (selectCase.equals("K")) {
            System.out.println("-----------------");
            System.out.println("Kaçtınız");
        }

        if (this.getPlayer().getHealth() == 0) {
            System.out.println("-----------------");
            System.out.println("Öldünüz !");
            return false;
        }

        return true;
    }

    private Boolean combat(Integer obsNumber) {

        for (int i = 1; i <= obsNumber; i++) {
            this.getObstacle().setHealth(this.getObstacle().getDefaultHealth());
            playerStats();
            obstacleStats(i);

            while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0) {
                System.out.println("<V>ur veya <K>aç");
                String selectCombat = scanner.nextLine();
                selectCombat = selectCombat.toUpperCase();
                if (selectCombat.equals("V")) {
                    System.out.println("Siz vurdunuz");
                    obstacle.setHealth(this.obstacle.getHealth() - this.getPlayer().getTotalDamage());
                    afterHit();

                    if (this.getObstacle().getHealth() > 0) {
                        System.out.println();
                        System.out.println("Canavar Size Vurdu !");
                        Integer obstacleDamage = (this.obstacle.getDamage() - this.getPlayer().getInventory().getArmor().getBlock());
                        if (obstacleDamage < 0) obstacleDamage = 0;
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                        afterHit();
                    }
                } else {
                    return false;
                }
            }

            if (this.getObstacle().getHealth() < this.getPlayer().getHealth()) {
                System.out.println("-----------------");
                System.out.println("Düşmanı yendiniz");
                System.out.println(this.getObstacle().getAward() + " para kazandınız");
                this.getPlayer().getInventory().setMoney(this.getPlayer().getInventory().getMoney() + this.getObstacle().getAward());
                System.out.println("Güncel Paranız : " + this.getPlayer().getInventory().getMoney());
                System.out.println("-----------------");
            } else {
                return false;
            }
        }

        return true;
    }

    private void afterHit() {
        System.out.println("Canınız : " + this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName() + " Canı : " + this.getObstacle().getHealth());
    }

    private void playerStats() {
        System.out.println("Oyuncu Değerleri");
        System.out.println("--------------------");
        System.out.println("Sağlık : " + this.getPlayer().getHealth());
        System.out.println("Silah : " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Hasar : " + this.getPlayer().getTotalDamage());
        System.out.println("Zırh : " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Bloklama : " + this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Hasar : " + this.getPlayer().getInventory().getMoney());
    }

    private void obstacleStats(Integer i) {
        System.out.println(i + ". " + this.getObstacle().getName() + " Değerleri");
        System.out.println("------------------------------------------");
        System.out.println("Sağlık :" + this.getObstacle().getHealth());
        System.out.println("Hasar :" + this.getObstacle().getDamage());
        System.out.println("Ödül :" + this.getObstacle().getAward());
    }
}
