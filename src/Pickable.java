import greenfoot.GreenfootImage;

public interface Pickable {

    int getItemId();
    int getItemSlotId();
    int getItemCount();
    String getItemType();
    String getItemName();
    GreenfootImage getItemImage();
    void setItemCount(int itemCount);
    void setIEquipped(boolean IEquipped);
    boolean isIEquipped();
    boolean compareIDWith(Pickable item, Pickable[] inventoryArray);
    void pick(Pickable[] inventoryArray);
}

