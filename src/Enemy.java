import java.util.List;

public class Enemy extends Hostile {
    private int visualRange = 1000;
    private int speed = 1;

    @Override
    public int getSpeed() {
        return speed;
    }
    @Override
    public void act() {
        Player player = getPlayer(this.visualRange);
        if(player != null) {
            moveInDirectionOf(player);
        }
        }


    }
