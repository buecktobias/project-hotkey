import greenfoot.GreenfootImage;

public class Grass extends Environment implements Explodes {
    public Grass(){
        GreenfootImage grass = new GreenfootImage(Files.getENVIRONMENT_PATH() + "grass.png");
        setImage(grass);
    }
}
