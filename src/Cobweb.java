import greenfoot.GreenfootImage;

public class Cobweb extends Environment {
    private GreenfootImage defaultImage = new GreenfootImage("images/Environment/cobweb.png");
    public Cobweb(){
        defaultImage.scale(32,32);
    }

    @Override
    public void act() {
        setImage(defaultImage);
    }
}
