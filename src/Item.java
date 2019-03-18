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
    //abstract void pick(Item[] inventoryArray);
    public void pick(Item[] inventoryArray){
        for (int i = 0; i < 30; i++) {
            if(inventoryArray[i] == null){
                inventoryArray[i] = this;
                getWorld().removeObject(this);
                break;
            }
        }
    }
}
