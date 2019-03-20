import greenfoot.GreenfootImage;

import java.util.Arrays;
import java.util.List;

public class Archer extends Hostile implements Attackable,Blocking {
    private double life = 100;
    private int speed = 1;
    private int damage = 10;
    private int attackRange = 200;
    private GreenfootImage defaultImage = new GreenfootImage("src/images/Characters/Player/player_standing.png");
    private GreenfootImage imageWalking1 = new GreenfootImage("src/images/Characters/Player/player_walking1.png");
    private GreenfootImage imageWalking2 = new GreenfootImage("src/images/Characters/Player/player_walking2.png");
    private GreenfootImage imageWalking3 = new GreenfootImage("src/images/Characters/Player/player_walking3.png");
    private GreenfootImage imageWalking4 = new GreenfootImage("src/images/Characters/Player/player_walking4.png");
    private GreenfootImage[] animationImages;
    public Archer(){
        GreenfootImage bowImage = new GreenfootImage(new Bow().getItemImage());
        bowImage.scale(30,30);
        GreenfootImage[] walkingImages = new GreenfootImage[]{imageWalking1,imageWalking2,imageWalking3,imageWalking4};
        List<GreenfootImage> listWalkingImages = Arrays.asList(walkingImages);
        listWalkingImages.forEach(walkingImage -> walkingImage.drawImage(bowImage,0,0));
        animationImages = (GreenfootImage[]) listWalkingImages.toArray();
    }
    @Override
    void movingAnimation() {
        animate(4,animationImages);
    }

    @Override
    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public int getAttackRange() {
        return attackRange;
    }

    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
    }

    @Override
    public double getLife() {
        return life;
    }

    public void setLife(double life) {
        this.life = life;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public void act() {
        randomMove(400);
    }
}
