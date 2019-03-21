import helper.Direction;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public abstract class NPC extends MovingActor {
    private int speed = 1;

    private Queue<Direction> movingList = new LinkedList<>();
    private static final Random r = new Random();


    @Override
    public void act() {
        super.act();
    }

    public Player getPlayer() {
        List<Player> playersInVisualRange = getWorld().getObjects(Player.class);
        if (playersInVisualRange.size() != 0) {
            return playersInVisualRange.get(0);
        }
        return null;

    }

    private Direction getRandomDirection() {
        switch (r.nextInt(4)) {
            case 0:
                return Direction.DOWN;
            case 1:
                return Direction.LEFT;
            case 2:
                return Direction.RIGHT;
            case 3:
                return Direction.UP;
        }
        return null;
    }

    public void randomMove() {
        moveDirection(getRandomDirection(), getSpeed());
    }

    public boolean move() {
        Direction directionToGo = movingList.poll();
        if (directionToGo != null) {
            moveDirection(directionToGo, getSpeed());
            return true;
        }
        return false;
    }

    public void addToMovingList(Direction d, int times) {
        for (int i = 0; i < times; i++) {
            movingList.add(d);
        }
    }
    private Direction getRandomDirectionOrNull(){
        if(r.nextInt(100)<80) {
            return getRandomDirection();
        }else{
            return null;
        }
    }


    public boolean randomMove(int range) {
        while (movingList.size() <= 0) {
            Direction d = getRandomDirectionOrNull();
            addToMovingList(d, r.nextInt(range));
        }
        return move();
    }

}
