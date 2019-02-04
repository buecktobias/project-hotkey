import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Enemy extends Hostile {
    private int visualRange = 1000;
    private int speed = 1;

    @Override
    public int getSpeed() {
        return speed;
    }
    @Override
    public void act() {
        List<Player> playersInVisualRange= getObjectsInRange(this.visualRange,Player.class);
        if(playersInVisualRange.size() != 0){
            Player player = playersInVisualRange.get(0);
            moveInDirectionTo(player);
        }


    }
}
