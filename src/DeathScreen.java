import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.World;

public class DeathScreen extends World {
    private GreenfootImage backgroundImage = new GreenfootImage(Files.getSCREENS_PATH()+"DeathScreen.png");
    public DeathScreen(){
        super(1024, 736, 1);
        setBackground(backgroundImage);
        Greenfoot.stop();
    }

    @Override
    public void started() {
        Greenfoot.setWorld(new LoadScreen());
    }
}
