import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

public class Bow extends Weapon implements Equippable{

    private int attackSpeed;
    private Player player;

    // Item Atributes
    ItemManager itemManager = ItemManager.Bow;
    public int itemCount;
    public final int itemSlotId = itemManager.getItemSLOTID();
    public final int itemId = itemManager.getItemID();
    public final String itemType = itemManager.getItemTYPE();
    public String itemName = itemManager.getItemNAME();
    public GreenfootImage itemImage = itemManager.getItemIMAGE();
    public boolean IEquipped = false;

    public Bow(int attackSpeed, Player player) {
        this.attackSpeed = attackSpeed;
        this.player = player;
        setImage(itemImage);
    }

    public void shoot() {
        Arrow arrow = new Arrow(42, 1,100,player);
        getWorld().addObject(arrow,getX(),getY());
        attackSpeedWait(attackSpeed);

    }

    public void act() {
        if(Greenfoot.isKeyDown("V")) {
            shoot();
        }
    }

    public void attackSpeedWait(int attackSpeed) {
        try {
           Thread.sleep(attackSpeed);
        }
        catch (Exception e) {}
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
