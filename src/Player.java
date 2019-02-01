import greenfoot.Greenfoot;
import helper.Direction;

import java.util.List;

/**
 * The main character called player.
 * @author SAE
 */
public class Player extends MovingActor {

    //Attribute


    //Konstruktoren


    //Methoden
    private void performMovement() {
        if(Greenfoot.isKeyDown("W")) {
            setLocation(getX(), getY()-1);
        }
        if(Greenfoot.isKeyDown("A")) {
            setLocation(getX()-1, getY());
        }
        if(Greenfoot.isKeyDown("S")) {
            setLocation(getX(), getY()+1);
        }
        if(Greenfoot.isKeyDown("D")) {
            setLocation(getX()+1, getY());
        }


    }

    /**
     * Is called once per time unit.
     */
    public void act() {
        performMovement();
    }

}
