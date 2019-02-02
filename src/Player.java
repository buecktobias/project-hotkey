import greenfoot.Greenfoot;


public class Player extends MovingActor {
    private final int speed = 1;

    private void performMovement() {
        if(Greenfoot.isKeyDown("W")) {
            moveUp(speed);
            if(getWorld() instanceof  OpenWorld){
                ((OpenWorld) getWorld()).resetPlayersPosition(this);
            }
        }
        if(Greenfoot.isKeyDown("A")) {
            moveLeft(speed);
            if(getWorld() instanceof  OpenWorld){
                ((OpenWorld) getWorld()).resetPlayersPosition(this);
            }
        }
        if(Greenfoot.isKeyDown("S")) {
            moveDown(speed);
            if(getWorld() instanceof  OpenWorld){
                ((OpenWorld) getWorld()).resetPlayersPosition(this);
            }
        }
        if(Greenfoot.isKeyDown("D")) {
            moveRight(speed);
            if(getWorld() instanceof  OpenWorld){
                ((OpenWorld) getWorld()).resetPlayersPosition(this);
            }
        }
    }


    public void act() {
        performMovement();
    }

}
