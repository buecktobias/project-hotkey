import greenfoot.Greenfoot;
import greenfoot.World;

public class LoadScreen extends World {
    public LoadScreen(){
        super(1024, 736, 1);
        setBackground("loadscreen.png");
    }
    public void act(){
        Greenfoot.setWorld(new Sector0_0());
    }
}
