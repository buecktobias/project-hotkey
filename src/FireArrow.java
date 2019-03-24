import greenfoot.Actor;
import greenfoot.GreenfootImage;
import greenfoot.GreenfootSound;

public class FireArrow extends Projectile{
    private GreenfootImage defaultImage = new GreenfootImage("images/ItemImages/FireArrow.png");
    private GreenfootSound arrowSound = new GreenfootSound("sounds/arrow2.wav");
    private double fireDamage = 5;
    public GreenfootImage getDefaultImage() {
        return defaultImage;
    }

    public FireArrow(int damage, double speed, double drag, Actor whoIsShooting, int scatter,double fireDamage){
        super(damage, speed, drag, whoIsShooting, scatter);
        this.fireDamage = fireDamage;
        setImage(defaultImage);
    }

    public void hits(Attackable attackable){
        if(attackable instanceof FireSensitive){
            ((FireSensitive) attackable).setFireDamage(fireDamage);
        }
    }

    @Override
    void makeShootingSound() {
        arrowSound.play();
    }

    public void act() {
        updatePosition();
    }
}
