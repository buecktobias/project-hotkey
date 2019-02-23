import greenfoot.Actor;
import helper.Direction;

import java.util.*;

/**
 * MovingActor provides basic methods for movement control, as well as text based methods.
 * @author SAE
 */
public abstract class MovingActor extends General {
    public int hitboxRadius=getWidth()*4;

    abstract int getSpeed();
    abstract void setSpeed(int n);

    public void getEffects(){
        List<Environment> environments = getIntersectingObjects(Environment.class);
        HashSet<Class> classes = new HashSet<>();
        for(Environment hasEffect:environments){
            if(hasEffect instanceof HasEffect){
                if( !(classes.contains(hasEffect.getClass()))) {
                    ((HasEffect) hasEffect).effects(this);
                }
                classes.add(hasEffect.getClass());
            }
        }
    }
    @Override
    public void act() {
        getEffects();
    }

    public void moveInDirectionOf(Actor actor){
        int actorX = actor.getX();
        int actorY = actor.getY();

        int xDifference = this.getX() - actorX;
        int yDifference = this.getY() - actorY;

        int absXDifference = Math.abs(xDifference);
        int absYDifference = Math.abs(yDifference);

        if(Math.max(absXDifference,absYDifference) == absXDifference){
            if(xDifference > 0){
                moveDirection(Direction.LEFT,this.getSpeed());
            }else if (xDifference <0){
                moveDirection(Direction.RIGHT,this.getSpeed());
            }
        }else if(Math.max(absXDifference,absYDifference) == absYDifference){
            if(yDifference > 0){
                moveDirection(Direction.UP,this.getSpeed());
            }else{
                moveDirection(Direction.DOWN,this.getSpeed());

            }

        }
    }

    public void moveInDirectionOf2(Actor actor) {
        int actorX = actor.getX();
        int actorY = actor.getY();

        HashMap<Direction, Integer> distances = new HashMap<>();
        distances.put(Direction.UP, Math.abs(actorX - getX()) + Math.abs(actorY - getY() - 1));
        distances.put(Direction.RIGHT, Math.abs(actorX - getX() + 1) + Math.abs(actorY - getY()));
        distances.put(Direction.DOWN, Math.abs(actorX - getX()) + Math.abs(actorY - getY() + 1));
        distances.put(Direction.LEFT, Math.abs(actorX - getX() - 1) + Math.abs(actorY - getY()));

        List<Direction> possibleDirections = new ArrayList<>();

        int max = 0;

        for(int distance : distances.values()) {
            if(distance > max) {
                max = distance;
            }
        }

        for(Map.Entry<Direction, Integer> entry : distances.entrySet()) {
            if(entry.getValue() == max) {
                possibleDirections.add(entry.getKey());
            }
        }

        Direction directionToGo = possibleDirections.get(new Random().nextInt(possibleDirections.size()));

        move(directionToGo);
    }

    public void move(Direction d){
        moveDirection(d,1);
    }
    public void moveDirection(helper.Direction d,int distance){
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
    private void moveUp() {
        moveUp(1);
    }
    private void moveRight() {
        moveRight(1);
    }
    private void moveLeft() {
        moveLeft(1);
    }
    private void moveDown() {
        moveDown(1);
    }

    public boolean intersectsWithBlockingObject(boolean again){
        List<General> intersectingObjects = getIntersectingObjects(General.class);
        intersectingObjects.removeIf(intersect -> !(intersect instanceof Blocking));
        List<General> objectsInRange = getObjectsInRange(this.hitboxRadius,General.class);
        objectsInRange.removeIf(object -> !(object instanceof Blocking));
        if(intersectingObjects.size() > 0) {
            for(General intersect:intersectingObjects){
                for(General object:objectsInRange){
                    if(intersect == object){
                        if(!(again)){
                            return true;
                        }else{
                        if(intersect instanceof MovingActor){
                            if(((MovingActor) intersect).intersectsWithBlockingObject(false)){
                                return true;
                            }
                        }else{
                            return true;
                        }
                    }
                    }
                }

            }
        }
        return false;
    }
    private void moveTo(int x,int y){
        int oldX = this.getX();
        int oldY = this.getY();
        setLocation(x,y);
        if(intersectsWithBlockingObject(true)){
            setLocation(oldX,oldY);
        }

    }
    private void moveUp(int distance){
        int x = getX();
        int y = getY() - distance;
        moveTo(x,y);
    }
    private void moveRight(int distance){
        int x = getX()+distance;
        int y = getY();
        moveTo(x,y);
    }
    private void moveLeft(int distance){
        int x = getX()-distance;
        int y = getY();
        moveTo(x,y);
    }
    private void moveDown(int distance){
        int x = getX();
        int y = getY()+distance;
        moveTo(x,y);
    }
    public void attack(Attackable actor,int damage){
        actor.setLife(actor.getLife()-damage);
    }

}
