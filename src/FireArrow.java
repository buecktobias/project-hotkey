import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class FireArrow extends Projectile{
    private GreenfootImage defaultImage = new GreenfootImage("images/ItemImages/FireArrow.png");
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
    public void act() {
        updatePosition();
    }
}
