import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

public class Bow extends Weapon implements Equippable{

    private int attackSpeed;
    private Player player;

    // Item Attributes
    private ItemManager itemManager = ItemManager.Bow;
    private final int itemSlotId = itemManager.getItemSLOTID();
    private final int itemId = itemManager.getItemID();
    private final String itemType = itemManager.getItemTYPE();
    private String itemName = itemManager.getItemNAME();
    private GreenfootImage itemImage = itemManager.getItemIMAGE();
    private boolean IEquipped = false;

    public Bow(int attackSpeed, Player player) {
        this.attackSpeed = attackSpeed;
        this.player = player;
        setImage(itemImage);
    }

    public void useWeapon(){}

    public void shoot() {
        Arrow arrow = new Arrow();
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
