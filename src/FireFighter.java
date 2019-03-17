import greenfoot.GreenfootImage;
import greenfoot.World;

public class FireFighter extends Hostile implements  Blocking,Attackable {
    private int attackRange = 50;
    private int damage = 1;
    private final int DEFAULT_SPEED = 2;
    private int speed = DEFAULT_SPEED;
    private double life = 20;
    private int visualRange = 400;
    private int attackSpeed = 30;


    @Override
    public double getLife() {
        return life;
    }

    public void setLife(double life) {
        this.life = life;
    }

    private GreenfootImage defaultImage = new GreenfootImage("images/Characters/lilpig.png");

    @Override
    protected void addedToWorld(World world) {

        setImage(defaultImage);
    }

    @Override
    public void act() {
        setSpeed(DEFAULT_SPEED);
        getEffects();
        if(moveToPlayer(this.visualRange)){
            attack(attackSpeed);
        }else{
            setImage(defaultImage);
            randomMove(200);
        }
        if(life <0){
            getWorld().removeObject(this);
        }
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
    GreenfootImage[] getMovingAnimationImages() {
        return new GreenfootImage[]{defaultImage};
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
}
