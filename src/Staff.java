import greenfoot.GreenfootImage;

public class Staff extends Weapon implements Equippable {

    private double damage;
    private int attackSpeed;
    private int attackRange;

    //Item Atributes
    ItemManager itemManager = ItemManager.Staff;
    public int itemCount = 0;
    public final int itemSlotId = itemManager.getItemSLOTID();
    public final int itemId = itemManager.getItemID();
    public final String itemType = itemManager.getItemTYPE();
    public String itemName = itemManager.getItemNAME();
    private GreenfootImage itemImage = itemManager.getItemIMAGE();
    public boolean IEquipped = false;

    public Staff(int attackRange) {
        this.attackRange = attackRange;
        this.attackSpeed = attackSpeed;
        this.damage = damage;
        setImage(itemImage);
    }

    //Item Methods
    public void pick(Item[] inventoryArray){
        inventoryArray[0] = this;
        getWorld().removeObject(this);
    }
    public boolean compareIDWith(Item item, Item[] inventoryArray){
        if (item.getItemId() == this.getItemId()) {
            item.setItemCount(item.getItemCount() + 1);
            getWorld().removeObject(this);
            return true;
        }else{
            for (int i = 0; i < 30; i++) {
                if(inventoryArray[i] == null){
                    inventoryArray[i] = item;
                    getWorld().removeObject(this);
                }
                return true;
            }
        }
        return false;
    }

    //Item Getters and Setters
    public int getItemSlotId() {
        return itemSlotId;
    }
    public int getItemCount() {
        return itemCount;
    }
    public int getItemId() {
        return itemId;
    }
    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }
    public void setIEquipped(boolean IEquipped) {
        this.IEquipped = IEquipped;
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
}
