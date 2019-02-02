import greenfoot.Greenfoot;


public class Player extends MovingActor {


    private void performMovement() {
        if(Greenfoot.isKeyDown("W")) {
            moveUp();
        }
        if(Greenfoot.isKeyDown("A")) {
            moveLeft();
        }
        if(Greenfoot.isKeyDown("S")) {
            moveDown();
        }
        if(Greenfoot.isKeyDown("D")) {
            moveRight();
        }
    }


    public void act() {
        performMovement();
    }

}
