import greenfoot.GreenfootImage;

public class Grass extends Environment {
    public Grass(){
        GreenfootImage grass = new GreenfootImage("images/Environment/grass.png");
        grass.scale(32,32);
        setImage(grass);
    }
}
