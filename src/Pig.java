import greenfoot.GreenfootImage;

public class Pig extends Friendly implements Blocking,Attackable {
    private int speed = 1;
    private final int defaultLife = 20;
    private int life = defaultLife;

    @Override
    public int getLife() {
        return life;
    }

    @Override
    public void setLife(int life) {
        this.life = life;
    }

    public Pig(){
        GreenfootImage img = new GreenfootImage("images/Characters/lilpig.png");
        img.scale(64,32);
        setImage(img);
    }

    @Override
    public void act() {
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
