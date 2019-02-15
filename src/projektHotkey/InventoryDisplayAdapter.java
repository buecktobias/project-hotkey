package projektHotkey;

import greenfoot.Actor;
import greenfoot.GreenfootImage;
import greenfoot.World;

public class InventoryDisplayAdapter extends Actor {
    private int itemSelected;
    private Actor[] items;
    private Actor[] itemImages;
    private GreenfootImage emtpySlot = new GreenfootImage("./images/EmptySlot.png");

    public InventoryDisplayAdapter(int size) {
        getImage().setTransparency(0);
        items = new Actor[size];
        itemImages = new Actor[size];
        itemSelected = 0;
    }
    public int getItemSelected() {
        return itemSelected;
    }

    public void setItemSelected(int itemSelected) {
        this.itemSelected = itemSelected;
    }

    /**
     * Method of Actor, will be called once the Adapter will be added/inserted (in)to the world.
     *
     * @param world target world
     */
    protected void addedToWorld(World world) {
        for (int i = 0; i < itemImages.length; i++) {
            itemImages[i] = createDummyImageActor(i);
        }
    }

    public void act() {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null && itemImages[i].getImage() != emtpySlot) {
                itemImages[i].setImage(emtpySlot);
            } else if (items[i] != null && itemImages[i].getImage() != items[i].getImage()) {
                itemImages[i].setImage(items[i].getImage());
            }
        }
        GreenfootImage tempImage = new GreenfootImage(itemImages[itemSelected].getImage());
        tempImage.drawRect(0, 0, 35, 45);
        itemImages[itemSelected].setImage(tempImage);

    }

    /**
     * Links an array of Items to the InventoryDisplayAdapter so it can be displayed inside the adapter.
     *
     * @param inventory array of Actors that shall be displayed
     */
    public void linkItems(Actor[] inventory) {
        int length = Math.max(inventory.length, items.length);
        items = inventory;
        for (int i = 0; i < length; i++) {
            Actor dummyImageActor = createDummyImageActor(i);
            itemImages[i] = dummyImageActor;
        }
    }


    private Actor createDummyImageActor(int i) {
        Actor actor = new Actor() {
        };
        getWorld().addObject(actor, i, getWorld().getHeight()-200);
        itemImages[i] = actor;
        actor.setImage(emtpySlot);

        return itemImages[i];
    }
}
