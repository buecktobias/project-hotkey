import greenfoot.GreenfootImage;

import java.util.Random;

public class Tree extends Environment implements Explodes{
    private double rotation;
    private boolean rotateLeft = false;
    private GreenfootImage defaultImage = new GreenfootImage("images/Environment/pine_tree.png");
    public Tree(){
        rotation = new Random().nextInt(10)-5;
        setImage(defaultImage);
    }
}
