import greenfoot.GreenfootImage;

import java.util.Random;

public class Chest extends GUI {

    Item[] contents;
    private final int SCATTER = 128;
    private GreenfootImage defaultImage = new GreenfootImage(Files.getENVIRONMENT_PATH() + "chest.png");
    public Chest(Item[] contents) {
        setImage(defaultImage);
        this.contents = contents;
    }

    // drops the contents of the chest on the ground and removes the chest
    public void open() {
        Random rand = new Random();
        for (Item item: contents) {
            int scatterX = rand.nextInt(SCATTER + 1) - SCATTER / 2;
            int scatterY = rand.nextInt(SCATTER + 1) - SCATTER / 2;
            getWorld().addObject(item, this.getX()+scatterX, this.getY()+scatterY);
        }
        getWorld().removeObject(this);
    }
}
