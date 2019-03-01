import greenfoot.GreenfootImage;

import java.util.LinkedList;

public interface Pickable {

    int getItemId();
    int getItemSlotId();
    int getItemCount();
    String getItemType();
    String getItemName();
    GreenfootImage getItemImage();
    void setItemCount(int itemCount);
    void pick(Player p, LinkedList inventory);
    void compareIDs(Player p, LinkedList<Pickable> inventory, Pickable item);
    void setIEquipped(boolean IEquipped);
    boolean isIEquipped();

}

