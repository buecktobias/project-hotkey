import greenfoot.GreenfootImage;

public class Pig extends Friendly implements Blocking {
    private int speed = 1;
    public Pig(){
        GreenfootImage img = new GreenfootImage("images/lilpig.png");
        img.scale(64,32);
        setImage(img);
    }

    @Override
    public void act() {
        randomMove();

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
