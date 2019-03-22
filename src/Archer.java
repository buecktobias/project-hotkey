import greenfoot.GreenfootImage;

import java.util.Arrays;
import java.util.List;

public class Archer extends Hostile implements Attackable,Blocking,FireSensitive {
    private double life = 1;
    private int defaultSpeed = 1;
    private int speed = defaultSpeed;
    private int visualRange = 450;
    private int attackSpeed = 100;
    private int damage = 100;
    private long lastFrameAttacked = 0;
    private int attackRange = 350;
    private double fireDamage = 0;


    @Override
    public double getFireDamage() {
        return fireDamage;
    }

    public void setFireDamage(double fireDamage) {
        this.fireDamage = fireDamage;
    }

    private GreenfootImage defaultImage = new GreenfootImage("src/images/Characters/Player/player_standing.png");
    private GreenfootImage imageWalking1 = new GreenfootImage("src/images/Characters/Player/player_walking1.png");
    private GreenfootImage imageWalking2 = new GreenfootImage("src/images/Characters/Player/player_walking2.png");
    private GreenfootImage imageWalking3 = new GreenfootImage("src/images/Characters/Player/player_walking3.png");
    private GreenfootImage imageWalking4 = new GreenfootImage("src/images/Characters/archer_walking4.png");
    private GreenfootImage[] animationImages;
    private Bow bow;
    public Archer(){
        setImage(defaultImage);
        bow = new Bow(this.damage,10);
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


    public void attack(General g,double attackSpeed) {
        if(FPS.getFrame() - lastFrameAttacked > attackSpeed){
            lastFrameAttacked = FPS.getFrame();
            this.bow.shootFrom(this,g.getX(),g.getY(),new FireArrow(this.damage,10,.1,this,0,5));
        }
    }

    @Override
    public void act() {
        subtractFireDamageFromLife();
        setSpeed(defaultSpeed);
        getEffects();
        if(fireDamage > 0.1){
            drawFireImage();
        }
        if(getPlayer(attackRange) != null){
            attack((General) getPlayer(this.attackRange), this.attackSpeed);
        }else {
            if (moveToPlayer(this.visualRange)) {
            } else {
                randomMove(400);
            }
        }
        if (life < 0) {
            getWorld().removeObject(this);
        }
    }

    }
