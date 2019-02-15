import greenfoot.Actor;
import greenfoot.GreenfootImage;
import greenfoot.World;

import java.util.List;

public abstract class OpenWorld extends World {
    public OpenWorld() {
        super(1024, 736, 1, false);
        setPaintOrder(Player.class);
    }

    private int totalXMovement = 0;
    private int totalYMovement = 0;

    //This image is used as the background image of the scrolling world.
    //If you want to use another image just chang the path.
    private GreenfootImage textur;

    public final void resetPlayersPosition(Player player) {
        int xMovement = (int) ((double) getWidth() / 2 - player.getX());
        int yMovement = (int) ((double) getHeight() / 2 - player.getY());
        totalXMovement += xMovement;
        totalYMovement += yMovement;
        List<Actor> actors = getObjects(Actor.class);
        for (Actor actor : actors) {
            if (actor instanceof Player) {
                ((Player) actor).setLocation(actor.getX() + xMovement, actor.getY() + yMovement);
            } else if (actor instanceof Fixed) {
                ;
            } else {
                actor.setLocation(actor.getX() + xMovement, actor.getY() + yMovement);
            }
        }
        createTextur();
    }

    protected final void createTextur() {
        int x;
        int y;
        if (totalXMovement > 0) {
            for (x = totalXMovement; x > 0; x -= textur.getWidth()) {
                ;
            }
        } else {
            for (x = totalXMovement; x < 0; x += textur.getWidth()) {
                ;
            }
            x -= textur.getWidth();
        }
        if (totalYMovement > 0) {
            for (y = totalYMovement; y > 0; y -= textur.getHeight()) {
                ;
            }
        } else {
            for (y = totalYMovement; y < 0; y += textur.getHeight()) {
                ;
            }
            y -= textur.getHeight();
        }
        getBackground().clear();
        for (int i = x; i < getWidth(); i += textur.getWidth()) {
            for (int j = y; j < getHeight(); j += textur.getHeight()) {
                getBackground().drawImage(textur, i, j);
            }
        }
    }
    public void setScrollingBackground(GreenfootImage bgImage) {
        textur = bgImage;
    }

}
