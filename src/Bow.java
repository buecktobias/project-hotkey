import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class Bow extends RangedWeapon {
    // Item Attributes
    private ItemManager itemManager = ItemManager.Bow;
    private final int itemSlotId = itemManager.getItemSLOTID();
    private final int itemId = itemManager.getItemID();
    private final String itemType = itemManager.getItemTYPE();
    private String itemName = itemManager.getItemNAME();
    private GreenfootImage itemImage = itemManager.getItemIMAGE();
    private boolean IEquipped = false;
    private Class<Projectile> projectileClass;

    public Bow() {
        setImage(itemImage);
        setDamage(7);
        setAttackRange(12);
        setAttackSpeed(1);
        setAnimationStartDegrees(-10);
        setAnimationStopDegrees(10);
    }
    public Bow(int damage,int attackRange) {
        setImage(itemImage);
        setDamage(damage);
        setAttackRange(attackRange);
        setAttackSpeed(1);
        setAnimationStartDegrees(-10);
        setAnimationStopDegrees(10);

    }

    public void shootFrom(Actor a, int toX, int toY,Projectile projectile) {
        a.getWorld().addObject(projectile, a.getX(), a.getY());
        projectile.shootFromTo(a.getX(),a.getY(),toX,toY);
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