import greenfoot.Greenfoot;
import greenfoot.World;

public class DeathScreen extends World {
    public DeathScreen(){
        super(1024, 736, 1);
        setBackground("images/DeathScreen.png");
    }

    @Override
    public void started() {
        Greenfoot.setWorld(new Sector0_0());
    }
}
