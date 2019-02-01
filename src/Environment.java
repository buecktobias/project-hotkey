import greenfoot.Actor;
import greenfoot.GreenfootImage;
import helper.Direction;

/**
 * @author SAE
 */
public class Environment extends Actor {

    private GreenfootImage defaultImage;

    /**
     * Default constructor
     */
    public Environment() {
        this.defaultImage = new GreenfootImage(getImage());
    }

    /**
     * Determines if this part of the environment is blocking.
     *
     * @return true if it is blocking
     */
    public boolean isBlocking() {
        return false;
    }

    /**
     * Determines the direction the Actor is currently facing.
     *
     * @return the current direction
     */
    public Direction getDirection() {
        return Direction.fromValue(getRotation());
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
}
