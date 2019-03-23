import greenfoot.GreenfootImage;

public class HealthPotion extends Item implements Countable, Equippable, Usable {


    private ItemManager itemManager = ItemManager.HealthPotion;
    private final int itemSlotId = itemManager.getItemSLOTID();
    private final int itemId = itemManager.getItemID();
    private final String itemType = itemManager.getItemTYPE();
    private String itemName = itemManager.getItemNAME();
    private GreenfootImage itemImage = itemManager.getItemIMAGE();
    private boolean IEquipped = false;
    private int itemCount = 0;

    private int healValue = 50;

    public HealthPotion(){
        setImage(itemImage);
    }
    public void use(Player p){
        p.setLife(p.getLife() + healValue);
    }
    public void compareIDWith(Item item, Item[] inventoryArray){
        if (item.getItemId() == this.getItemId()) {
            Countable cItem = (Countable) item;
            cItem.setItemCount(cItem.getItemCount() + 1);
            getWorld().removeObject(this);
        }else {
            pick(inventoryArray);
        }
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
