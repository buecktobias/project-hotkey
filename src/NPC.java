import helper.Direction;

import java.util.List;
import java.util.Random;

public abstract class NPC extends MovingActor{
    private int speed = 1;
    private static final Random r = new Random();
    public Player getPlayer(int visualRange) {
        List<Player> playersInVisualRange = getObjectsInRange(visualRange, Player.class);
        if (playersInVisualRange.size() != 0) {
            return playersInVisualRange.get(0);
        }
        return null;
    }
    public Player getPlayer(){
        List<Player> playersInVisualRange = getWorld().getObjects(Player.class);
        if (playersInVisualRange.size() != 0) {
            return playersInVisualRange.get(0);
        }
        return null;

    }
    public void randomMove(){
        switch(r.nextInt(4)){
            case 0:
                moveDirection(Direction.DOWN,speed);
                break;
            case 1:
                moveDirection(Direction.LEFT,speed);
                break;
            case 2:
                moveDirection(Direction.RIGHT,speed);
                break;
            case 3:
                moveDirection(Direction.UP,speed);
                break;
        }
    }
}
