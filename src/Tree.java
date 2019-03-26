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

    @Override
    public void act() {
        GreenfootImage img = new GreenfootImage(defaultImage);
        img.rotate((int)Math.round(rotation));
        if(rotation > 5){
            rotateLeft = true;
        }
        if(rotation < -5){
            rotateLeft = false;
        }
        if(rotateLeft){
            rotation -= 0.2;
        }else{
            rotation += 0.2;
        }
        setImage(img);
    }
}
