import greenfoot.GreenfootImage;

public class Tree extends Environment implements Explodes{
    private GreenfootImage defaultImage = new GreenfootImage(Files.getENVIRONMENT_PATH() + "pine_tree.png");
    public Tree(){
        setImage(defaultImage);
    }
}
