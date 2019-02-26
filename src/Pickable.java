import greenfoot.GreenfootImage;

import java.util.LinkedList;

public interface Pickable {
    int itemCount = 0;
    int itemId = 0;
    GreenfootImage placeHolderImage = new GreenfootImage("images/plus.png");
    String itemType = "noTypeAssigned";
    String itemName = "noNameAssigned";

    int getItemId();
    int getItemCount();
    String getItemType();
    String getItemName();
    GreenfootImage getItemImage();
    void setItemCount(int itemCount);
    void pick(Player p, LinkedList inventory);
    void compareIDs(Player p, LinkedList<Pickable> inventory, Pickable item);
}

