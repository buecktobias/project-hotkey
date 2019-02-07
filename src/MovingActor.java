import greenfoot.Actor;
import helper.Direction;

import java.util.List;

/**
 * MovingActor provides basic methods for movement control, as well as text based methods.
 * @author SAE
 */
public abstract class MovingActor extends General {

    abstract int getSpeed();
    public void moveInDirectionOf(Actor actor){
        if(getX() < actor.getX()){
            moveRight(getSpeed());
        }else if(getX() > actor.getX()){
            moveLeft(getSpeed());
        }else if(getY() < actor.getY()){
            moveDown(getSpeed());
        }else if(getY() > actor.getY()){
            moveUp(getSpeed());
        }

    }
    public void move(Direction d){
        moveDirection(d,1);
    }
    public void moveDirection(Direction d,int distance){
        switch(d){
            case RIGHT:
                moveRight(distance);
                break;
            case LEFT:
                moveLeft(distance);
                break;
            case UP:
                moveUp(distance);
                break;
            case DOWN:
                moveDown(distance);
                break;
        }
    }
    public void moveUp() {
        moveUp(1);
    }
    public void moveRight() {
        moveRight(1);
    }
    public void moveLeft() {
        moveLeft(1);
    }
    public void moveDown() {
        moveDown(1);
    }

    public boolean intersectsWithBlockingObject(){
        List<General> actors= getIntersectingObjects(General.class);
        for(General actor:actors){
            if(actor.isBlocking()){
                return true;
            }
        }
        return false;
    }
    public void moveTo(int x,int y){
        int oldX = this.getX();
        int oldY = this.getY();
        setLocation(x,y);
        if(intersectsWithBlockingObject()){
            setLocation(oldX,oldY);
        }

    }
    public void moveUp(int distance){
        int x = getX();
        int y = getY() - distance;
        moveTo(x,y);
    }
    public void moveRight(int distance){
        int x = getX()+distance;
        int y = getY();
        moveTo(x,y);
    }
    public void moveLeft(int distance){
        int x = getX()-distance;
        int y = getY();
        moveTo(x,y);
    }
    public void moveDown(int distance){
        int x = getX();
        int y = getY()+distance;
        moveTo(x,y);
    }


    @Override
    public boolean isBlocking() {
        return true;
    }

}
