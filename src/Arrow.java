import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class Arrow extends Projectile{
    private GreenfootImage defaultImage = new GreenfootImage("images/ItemImages/Arrow.png");

    @Override
    public GreenfootImage getDefaultImage() {
        return defaultImage;
    }

    public Arrow(int damage, double speed, double drag, Actor whoIsShooting, int scatter){
        super(damage, speed, drag, whoIsShooting, scatter);
        setImage(defaultImage);
    }
    public void act() {
        updatePosition();
    }
}