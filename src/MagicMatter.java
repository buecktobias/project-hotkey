import greenfoot.Actor;
import greenfoot.GreenfootImage;
import greenfoot.GreenfootSound;

public class MagicMatter extends Projectile {
    private GreenfootImage defaultImage =  new GreenfootImage("images/ItemImages/magicMatter.png");
    private GreenfootSound magicSound = new GreenfootSound("sounds/magicMatter2.wav");
    @Override
    public GreenfootImage getDefaultImage() {
        return defaultImage;
    }

    @Override
    void makeShootingSound() {
        magicSound.play();
    }

    public MagicMatter(int damage, double speed, double drag, Actor whoIsShooting, int scatter){
        super(damage, speed, drag, whoIsShooting, scatter);
        setImage(defaultImage);
    }
    public void act() {
        updatePosition();
    }
}
