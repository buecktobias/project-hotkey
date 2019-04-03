import greenfoot.GreenfootImage;

public class Rock extends Environment implements Blocking  {
    public Rock(){
        GreenfootImage img = new GreenfootImage(Files.getENVIRONMENT_PATH() + "rock.png");
        setImage(img);
    }
}
