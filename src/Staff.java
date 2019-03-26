import greenfoot.Actor;
import greenfoot.GreenfootImage;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Staff extends RangedWeapon {
    // Item Attributes
    private ItemManager itemManager = ItemManager.Staff;
    private final int itemSlotId = itemManager.getItemSLOTID();
    private final int itemId = itemManager.getItemID();
    private final String itemType = itemManager.getItemTYPE();
    private String itemName = itemManager.getItemNAME();
    private GreenfootImage itemImage = itemManager.getItemIMAGE();
    private boolean IEquipped = false;

    public Staff() {
        setImage(itemImage);
        setDamage(20);
        setAttackRange(10);
        setAttackSpeed(5);
        setAnimationStartDegrees(50);
        setAnimationStopDegrees(200);
    }

    public void shootFrom(Actor a, int toX, int toY,Projectile p) {
        Random r = new Random();
        List<Projectile> projectiles = new LinkedList<>();
        for (int i = 0; i < 12; i++) {
            int scatter = r.nextInt(65)-32;
            int rangeScatter = r.nextInt(5)-2;
            Projectile projectile = new MagicMatter(getDamage(), getAttackRange()+rangeScatter, .2,a, 30);
            projectiles.add(projectile);
            a.getWorld().addObject(projectile, a.getX()+scatter, a.getY()+scatter);
            projectile.shootFromTo(a.getX()+scatter,a.getY()+scatter,toX,toY);
        }


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