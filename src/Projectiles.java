import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.World;
import helper.Direction;

import java.util.*;

public class Projectiles extends Weapon{
    private double PositionX;
    private double PositionY;
    private double xSpeed;
    private double ySpeed;
    private double xAcceleration;
    private double yAcceleration;
    private double damage;
    private double attackRange;
    private double projectileSpeed;
    private MovingActor movingActor;


    public double getPositionX() {
        return PositionX;
    }

    public void setPositionX(double positionX) {
        this.PositionX = positionX;
    }


    public double getPositionY() {
        return PositionY;
    }

    public void setPositionY(double positionY) {
        this.PositionY = positionY;
    }

    public Projectiles(String fileName,int damage, int projectileSpeed, int attackRange, MovingActor movingActor) {
        setImage(fileName);
        this.damage = damage;
        this.attackRange = attackRange;
        this.projectileSpeed = projectileSpeed;
        this.movingActor = movingActor;
    }
    @Override
    protected void addedToWorld(World world) {
        PositionX = movingActor.getX();
        PositionY = movingActor.getY();
        setLocation((int) PositionX,(int) PositionY);
    }

    @Override
    public void act() {
        PositionX += xSpeed;
        PositionY += ySpeed;
        xSpeed *= xAcceleration;
        ySpeed *= yAcceleration;
        setLocation((int)Math.round(PositionX),(int)Math.round(PositionY));
    }
    public void moveInDirectionOfMouse(double velocity){
        int mouseX=Greenfoot.getMouseInfo().getX();
        int mouseY=Greenfoot.getMouseInfo().getY();
        double adjacent = mouseX - PositionX;
        double opposite = mouseY - PositionY;
        double angle = Math.atan(opposite/adjacent);
        xSpeed = velocity * Math.cos(angle);
        ySpeed = velocity * Math.sin(angle);
        xAcceleration = 0.9;
        yAcceleration = 0.9;
    }

    public int hitboxRadius=getWidth()*4;
    public void moveInDirectionOf(Actor actor){
        int actorX = actor.getX();
        int actorY = actor.getY();

        int xDifference = this.getX() - actorX;
        int yDifference = this.getY() - actorY;

        int absXDifference = Math.abs(xDifference);
        int absYDifference = Math.abs(yDifference);

        if(Math.max(absXDifference,absYDifference) == absXDifference){
            if(xDifference > 0){
                moveLeft();
            }else if (xDifference <0){
                moveRight();
            }
        }else if(Math.max(absXDifference,absYDifference) == absYDifference){
            if(yDifference > 0){
                moveUp();
            }else{
                moveDown();

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

    public boolean intersectsWithBlockingObject(boolean again){
        List<General> intersectingObjects = getIntersectingObjects(General.class);
        intersectingObjects.removeIf(intersect -> !(intersect instanceof Blocking));
        List<General> objectsInRange = getObjectsInRange(this.hitboxRadius,General.class);
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
                            }
                        }
                    }
                }

            }
        }
        return false;
    }
    public void moveTo(int x,int y){
        int oldX = this.getX();
        int oldY = this.getY();
        setLocation(x,y);
        if(intersectsWithBlockingObject(true)){
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

}
