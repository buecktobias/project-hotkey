import greenfoot.Greenfoot;
import greenfoot.World;

public class LoadScreen extends World {
    public LoadScreen(){
        super(1024, 736, 1);
        setBackground(Files.getSCREENS_PATH() + "loadscreen.png");
    }

    @Override
    public void started() {
        Greenfoot.setWorld(new Sector0_0());
    }
}
