import greenfoot.GreenfootImage;

public class Rock extends Environment implements Blocking  {
    public Rock(){
        GreenfootImage img = new GreenfootImage("images/Environment/rock.png");
        setImage(img);
    }
}
