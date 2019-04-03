import greenfoot.GreenfootImage;
import greenfoot.World;

public class Sand extends Environment implements Explodes {
    private GreenfootImage defaultImage = new GreenfootImage(Files.getENVIRONMENT_PATH() + "Sand.jpg");
    @Override
    protected void addedToWorld(World world) {
        defaultImage.scale(32,32);
        setImage(defaultImage);
    }
}