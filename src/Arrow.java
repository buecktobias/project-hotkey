import greenfoot.Actor;
import greenfoot.GreenfootImage;
import greenfoot.GreenfootSound;

public class Arrow extends Projectile{
    private GreenfootImage defaultImage = new GreenfootImage("images/ItemImages/Arrow.png");
    private GreenfootSound arrowSound = new GreenfootSound("sounds/arrow2.wav");
    @Override
    public GreenfootImage getDefaultImage() {
        return defaultImage;
    }

    @Override
    public void makeShootingSound() {
        arrowSound.play();
    }

    public Arrow(int damage, double speed, double drag, Actor whoIsShooting, int scatter){
        super(damage, speed, drag, whoIsShooting, scatter);
        setImage(defaultImage);
    }
    public void act() {
        updatePosition();
    }
}