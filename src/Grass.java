import greenfoot.GreenfootImage;

public class Grass extends Environment {
    public Grass(){
        GreenfootImage grass = new GreenfootImage("images/grass.png");
        grass.scale(32,32);
        setImage(grass);
    }
}
