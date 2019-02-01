import greenfoot.Actor;
import helper.Coordinates;
import helper.Direction;
import helper.DummyActor;

import java.util.List;

/**
 * MovingActor provides basic methods for movement control, as well as text based methods.
 * @author SAE
 */
public abstract class MovingActor extends Environment {


    private DummyActor dummyActor = new DummyActor();




    /**
     * Determines if the spot in front of the Actor is blocked, or the Actor should be able to move.
     *
     * @return true if the Actor should be able to move, false otherwise
     */
    public boolean canMove() {
        List<Environment> environmentList = getObjectsInFront(Environment.class);

        for (Environment env : environmentList) {
            if (env.isBlocking()) {
                return false;
            }
        }
        return true;

    }


    /**
     * Returns a list of Actors in front of the current MovingActor.
     *
     * @param objClass Class of the Actors that shall be returned
     * @param <A> the desired type of Actor
     * @return List of Actors
     */
    public <A extends Actor> List<A> getObjectsInFront(Class<A> objClass) {
        Coordinates point = getNextCoordinates();

        List<A> toReturn = getWorld().getObjectsAt(point.getX(), point.getY(), objClass);
        getWorld().removeObject(this.dummyActor);

        return toReturn;
    }


    /**
     * Predicts coordinates based on the assumpotion that the MovingActor will make its next steps in the current
     * direction.
     *
     * @return the forecast of future x and y (@see Coordinates)
     */
    public Coordinates getNextCoordinates() {
        getWorld().addObject(this.dummyActor, getX(), getY());
        this.dummyActor.setRotation(getRotation());
        this.dummyActor.move(1);
        Coordinates coordinates = new Coordinates(this.dummyActor.getX(), this.dummyActor.getY());
        getWorld().removeObject(this.dummyActor);
        System.out.println(this.toString() + " - " + coordinates);
        return coordinates;
    }


    /**
     * Turns the Actor so he faces upwards
     */
    public void faceUp() {
        turn(Direction.UP);
    }

    /**
     * Turns the Actor so he faces downwards
     */
    public void faceDown() {
        turn(Direction.DOWN);
    }

    /**
     * Turns the Actor so he faces left
     */
    public void faceLeft() {
        turn(Direction.LEFT);
    }

    /**
     * Turns the Actor so he faces right
     */
    public void faceRight() {
        turn(Direction.RIGHT);
    }


    @Override
    public boolean isBlocking() {
        return true;
    }

}
