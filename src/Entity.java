import greenfoot.Actor;
import greenfoot.GreenfootImage;
import helper.Direction;

import java.util.List;

public abstract class Entity extends Actor {

    private int timer = 0;
    private int animationImage=0;
    private long lastFrameAnimated =0;
    private GreenfootImage defaultImage;


    /**
     * Default constructor
     */

    public Entity() {

        this.defaultImage = new GreenfootImage(getImage());
    }
    public Player getPlayer(int visualRange) {
        List<Player> playersInVisualRange = getObjectsInRange(visualRange, Player.class);
        if (playersInVisualRange.size() != 0) {
            return playersInVisualRange.get(0);
        }
        return null;
    }
    public <A extends Actor> List<A> getIntersectingObjects(Class<A> c){
        return super.getIntersectingObjects(c);
    }
    @Override
    public void act() {
        timer++;
    }
    /*
    public void setLocation(int x, int y) {
        if (OpenWorld.WORLD_WIDTH != 0) {
            if (getDistanceToScrollingActor('x') - ((OpenWorld)getWorld()).getTotalXMovement() > OpenWorld.WORLD_WIDTH/2) {
                x = (int) (getStartingPoint().getX() + OpenWorld.WORLD_WIDTH/2);
            }
            else if (getDistanceToScrollingActor('x') - ((OpenWorld)getWorld()).getTotalXMovement() < - OpenWorld.WORLD_WIDTH/2) {
                x = (int) (getStartingPoint().getX() - OpenWorld.WORLD_WIDTH/2);
            }
        }
        if (OpenWorld.WORLD_HEIGHT != 0) {
            if(getDistanceToScrollingActor('y') - ((OpenWorld)getWorld()).getTotalYMovement() > OpenWorld.WORLD_HEIGHT/2) {
                y = (int) (getStartingPoint().getY() + OpenWorld.WORLD_HEIGHT/2);
            }
            else if(getDistanceToScrollingActor('y') - ((OpenWorld)getWorld()).getTotalYMovement() < - OpenWorld.WORLD_HEIGHT/2) {
                y = (int) (getStartingPoint().getY() - OpenWorld.WORLD_HEIGHT/2);
            }
        }
        exactX = x;
        exactY = y;
        super.setLocation(x, y);
    }
    */
    /**
     * Determines if this part of the environment is blocking.
     *
     * @return true if it is blocking
     */
    public int getWidth(){
        return this.getImage().getWidth();
    }
    public int getHeight(){
        return this.getImage().getHeight();
    }
    /**
     * Determines the direction the Actor is currently facing.
     *
     * @return the current direction
     */
    public Direction getDirection() {
        return Direction.fromValue(getRotation());
    }
    public void animate(GreenfootImage... images ){
        assert images.length != 0;
        if(animationImage>=images.length){
            animationImage = 0;
        }
        setImage(images[animationImage]);
        animationImage++;
    }
    public void animate(int speed,GreenfootImage... images){
        assert images.length != 0;
        if(animationImage >= images.length){
            animationImage = 0;
        }
        if(FPS.getInstance().getFrame() - lastFrameAnimated > speed) {
            setImage(images[animationImage]);
            animationImage++;
            lastFrameAnimated = FPS.getInstance().getFrame();
        }
    }

    @Override
    public void turn(int amount) {
        if (amount % 90 != 0) {
            return;
        }
        super.turn(amount);
    }


    @Override
    public void setRotation(int rotation) {
        if (rotation % 90 != 0) {
            return;
        }

        super.setRotation(rotation);
    }

    /**
     * Turns toward the given direction.
     *
     * @param direction target direction.
     */
    public void turn(Direction direction) {
        int toRotate = direction.getValue() - getRotation();

        if (toRotate != 0) {
            turn(toRotate);
            getImage().rotate(-toRotate);
        }
    }


    /**
     * Prints the text above the actor
     *
     * @param text that shall be printed
     */
    public void print(boolean text) {
        print(String.valueOf(text));
    }

    /**
     * Prints the text above the actor
     *
     * @param text that shall be printed
     */
    public void print(int text) {
        print(String.valueOf(text));
    }

    /**
     * Prints the text above the actor
     *
     * @param text that shall be printed
     */
    public void print(double text) {
        print(String.valueOf(text));
    }

    /**
     * Prints the text above the actor
     *
     * @param text that shall be printed
     */
    public void print(String text) {
        print(text, 0, 10);

    }


    /**
     * Prints the text above the actor at the given offset (x, y).
     *
     * @param text text that shall be printed on the current actor image
     * @param x x coodinates relative to the top left
     * @param y y coordinates relative to the top left
     */
    public void print(String text, int x, int y) {
        Direction currentDirection = Direction.fromValue(getRotation());
        turn(Direction.RIGHT);
        super.setImage(new GreenfootImage(this.defaultImage));

        if (text != null && !text.equals("")) {
            getImage().drawString(text, x, y);
        }
        turn(currentDirection);

    }


    @Override
    public void setImage(String fileName) {
        this.setImage(new GreenfootImage(fileName));
    }


    @Override
    public void setImage(GreenfootImage image) {
        Direction currentDirection = Direction.fromValue(getRotation());
        turn(Direction.RIGHT);
        super.setImage(image);
        turn(currentDirection);
    }


    @Override
    public String toString() {
        return this.getClass().getCanonicalName() + "; at=(" + getX() + ", " + getY() + ")";
    }

    public int getTimer() {
        return timer;
    }
}
