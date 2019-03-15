import greenfoot.GreenfootImage;

public class Pig extends Friendly implements Blocking,Attackable {
    private int speed = 1;
    private final int defaultLife = 20;
    private int life = defaultLife;
    private GreenfootImage defaultImage = new GreenfootImage("images/Characters/lilpig.png");
    @Override
    public int getLife() {
        return life;
    }

    @Override
    public void setLife(int life) {
        this.life = life;
    }

    @Override
    GreenfootImage[] getMovingAnimationImages() {
        return new GreenfootImage[]{defaultImage};
    }

    public Pig(){
        defaultImage.scale(64,32);
        setImage(defaultImage);
    }

    @Override
    public void act() {
        getEffects();
        randomMove(200);
        if(life<0){
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
}
