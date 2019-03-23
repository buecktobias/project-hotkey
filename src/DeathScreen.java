import greenfoot.Greenfoot;
import greenfoot.World;

public class DeathScreen extends World {
    public DeathScreen(){
        super(1024, 736, 1);
        setBackground("images/Screens/DeathScreen.png");
        Greenfoot.stop();
    }

    @Override
    public void started() {
        Greenfoot.setWorld(new LoadScreen());
    }
}
