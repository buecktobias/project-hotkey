import greenfoot.GreenfootImage;

public class HealthPotion extends Item implements Countable, Equippable, Usable {


    private ItemManager itemManager = ItemManager.HealthPotion;
    private final int itemSlotId = itemManager.getItemSLOTID();
    private final int itemId = itemManager.getItemID();
    private final String itemType = itemManager.getItemTYPE();
    private String itemName = itemManager.getItemNAME();
    private GreenfootImage itemImage = itemManager.getItemIMAGE();
    private boolean IEquipped = false;
    private int itemCount;

    private int healValue = 50;

    public HealthPotion(int itemCount){
        setImage(itemImage);
        this.itemCount = itemCount;
    }
    public void use(Player p){
        double currentLife = p.getLife();
        double maxLife = p.getMaxLife();
        if(currentLife + healValue > maxLife){
            p.setLife(maxLife);
        }else{
            p.setLife(currentLife + healValue);
        }
    }

    public void compareIDWith(Item[] inventoryArray){
        for(Item item1: inventoryArray){
            if(item1 != null){
                if (item1.getItemId() == itemId) {
                    Countable cItem = (Countable) item1;
                    cItem.setItemCount(cItem.getItemCount() + this.itemCount);
                    getWorld().removeObject(this);
                    return;
                }
            }
        }
        pick(inventoryArray);
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
