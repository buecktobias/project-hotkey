import greenfoot.GreenfootImage;
import greenfoot.World;

public class Sand extends Environment implements Explodes {
    private GreenfootImage defaultImage = new GreenfootImage("images/Environment/Sand.jpg");
    @Override
    protected void addedToWorld(World world) {
        defaultImage.scale(32,32);
        setImage(defaultImage);
    }
}