import greenfoot.GreenfootImage;

public class WalkingBomb extends Hostile implements Attackable, Blocking, FireSensitive {
    private int visualRange = 500;
    private int attackRange;
    private int damage = 5;
    private int speed = 1;
    private double life = 100;
    private int hitboxRadius = getWidth();
    private GreenfootImage defaultImage = new GreenfootImage("src/images/Characters/lilpig.png");

    private double fireDamage = 0;
    private double fireDamageReduction = 0.99;

    public double getFireDamageReduction() {
        return fireDamageReduction;
    }

    public void setFireDamageReduction(double fireDamageReduction) {
        this.fireDamageReduction = fireDamageReduction;
    }

    @Override
    public double getFireDamage() {
        return fireDamage;
    }

    @Override
    public void setFireDamage(double fireDamage) {
        this.fireDamage = fireDamage;
    }
    @Override
    GreenfootImage[] getMovingAnimationImages() {
        return new GreenfootImage[]{defaultImage};
    }

    @Override
    int getAttackRange() {
        return attackRange;
    }

    @Override
    int getDamage() {
        return damage;
    }

    public WalkingBomb(){
        GreenfootImage img = new GreenfootImage("images/Characters/Enemy.png");
        img.scale(128,128);
        setImage(img);
        defaultImage = img;
        attackRange =  img.getWidth();
    }
    @Override
    public double getLife() {
        return life;
    }

    @Override
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

    GreenfootImage getAngryImage() {
        return defaultImage;
    }

    @Override
    public void act() {
        subtractFireDamageFromLife();
        getEffects();
        if(moveToPlayer(this.visualRange)){

        }else{
            randomMove(500);
        }
        if(attackPlayer(this.attackRange, this.damage)){
        }
        if(life <0){
            getWorld().removeObject(this);
        }
    }

}
