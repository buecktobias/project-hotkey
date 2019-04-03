import greenfoot.GreenfootImage;
import greenfoot.World;

import java.util.Random;

public class FireEnemy extends Hostile implements  Blocking,Attackable {
    private int attackRange = 50;
    private int fireSpawnSpeed = 60;
    private int fireSpawnRange = 200;
    private long lastSpawnFire = 0;
    private int damage = 1;
    private final int DEFAULT_SPEED = 2;
    private int speed = DEFAULT_SPEED;
    private double life = 20;
    private int visualRange = 200;
    private int attackSpeed = 30;
    private static Random random= new Random();


    @Override
    public double getLife() {
        return life;
    }

    public void setLife(double life) {
        if(life < 0){
            getWorld().removeObject(this);
        }
        this.life = life;
    }

    private GreenfootImage defaultImage = new GreenfootImage(Files.getCHARACTERS_PATH() + "FireEnemy1.png");
    private GreenfootImage move1 = new GreenfootImage(Files.getCHARACTERS_PATH() + "FireEnemy2.png");
    private GreenfootImage move2 = new GreenfootImage(Files.getCHARACTERS_PATH() + "FireEnemy3.png");

    @Override
    void movingAnimation() {
        animate(4,defaultImage,move1,move2);
    }

    @Override
    protected void addedToWorld(World world) {

        setImage(defaultImage);
    }

    public boolean spawnFireNearby(){
        if(FPS.getFrame()- lastSpawnFire > fireSpawnSpeed){
            lastSpawnFire = FPS.getFrame();
            int x = getX();
            int y = getY();
            World world = getWorld();
            int randomX;
            int randomY;
            for(int i = 0;i < 5;i++){
                randomX = random.nextInt(fireSpawnRange)-fireSpawnRange/2;
                randomY = random.nextInt(fireSpawnRange)-fireSpawnRange/2;
                world.addObject(new Fire(60),x+randomX,y+randomY);
            }
            return true;
        }
        return false;

    }
    @Override
    public void act() {
        boolean fireSpawned = spawnFireNearby();
        setSpeed(DEFAULT_SPEED);
        getEffects();
        if(!(fireSpawned)) {
            if (moveToPlayer(this.visualRange)) {
                attack(attackSpeed);
            } else {
                setImage(defaultImage);
                randomMove(200);
            }
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
