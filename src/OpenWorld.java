import greenfoot.Actor;
import greenfoot.GreenfootImage;
import greenfoot.World;

import java.util.List;

public abstract class OpenWorld extends World {

    //A maximum size of the Scrolling World. If the value for width or height is 0 the world is infinite in this direction.
    //The variables are final so they have to be set before compiling the game and can't be set while executing the game.
    private static int WORLD_WIDTH;
    private static int WORLD_HEIGHT;

    private int totalXMovement = 0;
    private int totalYMovement = 0;

    //This image is used as the background image of the scrolling world.
    //If you want to use another image just chang the path.
    private GreenfootImage textur;

    public OpenWorld(int screenWidth, int screenHeight) {
        super(screenWidth, screenHeight, 1, false);
        WORLD_WIDTH = 0;
        WORLD_HEIGHT = 0;
    }

    public OpenWorld(int screenWidth, int screenHeight, int cellSize) {
        super(screenWidth, screenHeight, cellSize, false);
        WORLD_WIDTH = 0;
        WORLD_HEIGHT = 0;
    }

    public OpenWorld(int screenWidth, int screenHeight, int scrollingWidth, int scrollingHeight) {
        super(screenWidth, screenHeight, 1, false);
        WORLD_WIDTH = scrollingWidth;
        WORLD_HEIGHT = scrollingHeight;
    }

    public OpenWorld(int screenWidth, int screenHeight, int cellSize, int scrollingWidth, int scrollingHeight) {
        super(screenWidth, screenHeight, cellSize, true);
        WORLD_WIDTH = scrollingWidth;
        WORLD_HEIGHT = scrollingHeight;
    }

    public final void resetPlayersPosition(Player player) {
        int xMovement = (int) ((double) getWidth() / 2 - player.getX());
        int yMovement = (int) ((double) getHeight() / 2 - player.getY());
        totalXMovement += xMovement;
        totalYMovement += yMovement;
        System.out.println(totalYMovement);
        System.out.println(totalYMovement);
        List<Actor> actors = getObjects(Actor.class);
        for (Actor actor : actors) {
            if (actor instanceof Player) {
                if(actor instanceof HUD){
                    continue;
                }
                ((Player) actor).setLocation(actor.getX() + xMovement, actor.getY() + yMovement);
            } else if (actor instanceof InventoryDisplayAdapter || actor instanceof HUD) {

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

    /**
     * Change the background image of the scrolling world to the given image.
     *
     * @param bgImage The new background image.
     */
    public void setScrollingBackground(GreenfootImage bgImage) {
        textur = bgImage;
    }
}
