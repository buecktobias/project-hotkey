import greenfoot.GreenfootImage;

public abstract class Item extends NotMoving {

    abstract int getItemId();
    abstract int getItemSlotId();
    abstract int getItemCount();
    abstract String getItemType();
    abstract String getItemName();
    abstract GreenfootImage getItemImage();
    abstract void setItemCount(int itemCount);
    abstract void setIEquipped(boolean IEquipped);
    abstract boolean isIEquipped();
    abstract void pick(Item[] inventoryArray);
    abstract void compareIDWith(Item item, Item[] inventoryArray);
}
