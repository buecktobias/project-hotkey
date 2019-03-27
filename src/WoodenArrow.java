import greenfoot.GreenfootImage;

public class WoodenArrow extends Ammunition implements Countable, Equippable {

    private ItemManager itemManager = ItemManager.WoodenArrow;
    private final int itemSlotId = itemManager.getItemSLOTID();
    private final int itemId = itemManager.getItemID();
    private final String itemType = itemManager.getItemTYPE();
    private String itemName = itemManager.getItemNAME();
    private GreenfootImage itemImage = itemManager.getItemIMAGE();
    private boolean IEquipped = false;
    private int itemCount;

    public WoodenArrow(int itemCount){
        this.itemCount = itemCount;
        setImage(itemImage);
    }
    public void compareIDWith(Item item, Item[] inventoryArray){
        Countable cItem = (Countable) item;
        if (item.getItemId() == this.getItemId()) {
            getWorld().removeObject(this);

        }else {
            pick(inventoryArray);
        }
        cItem.setItemCount(this.itemCount + cItem.getItemCount());
    }

    //Countable Getters & Setters
    public int getItemCount() {
        return itemCount;
    }
    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }
    //Item Getters and Setters
    public int getItemSlotId() {
        return itemSlotId;
    }
    public int getItemId() {
        return itemId;
    }
    public String getItemType() {
        return itemType;
    }
    public String getItemName() {
        return itemName;
    }
    public GreenfootImage getItemImage() {
        return itemImage;
    }
    public boolean isIEquipped() {
        return IEquipped;
    }
    public void setIEquipped(boolean IEquipped) {
        this.IEquipped = IEquipped;
    }
}
