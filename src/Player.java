import greenfoot.Greenfoot;


public class Player extends MovingActor {

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


    public void act() {
        performMovement();
    }

}
