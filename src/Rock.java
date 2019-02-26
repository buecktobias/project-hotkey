import greenfoot.GreenfootImage;

public class Rock extends Environment implements Blocking  {
    public Rock(){
        GreenfootImage img = new GreenfootImage("images/Environment/rock.gif");
        img.scale(32,32);
        setImage(img);
    }
}
