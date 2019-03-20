import java.util.Random;

public class Chest extends GUI {

    Item[] contents;

    public Chest(Item[] contents) {
        setImage("images/Environment/chest.png");
        this.contents = contents;
    }

    // drops the contents of the chest on the ground and removes the chest
    public void open() {
        Random rand = new Random();
        for (Item item: contents) {
            int scatterX = rand.nextInt(129)-64;
            int scatterY = rand.nextInt(129)-64;
            getWorld().addObject(item, this.getX()+scatterX, this.getY()+scatterY);
        }
        getWorld().removeObject(this);
    }
}
