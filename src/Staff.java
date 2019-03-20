import greenfoot.GreenfootImage;

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
        setDamage(3);
        setAttackRange(10);
        setAttackSpeed(5);
        setAnimationStartDegrees(50);
        setAnimationStopDegrees(200);
    }

    public void shootFrom(Player player) {
        Random r = new Random();
        for (int i = 0; i < 12; i++) {
            int scatter = r.nextInt(65)-32;
            int rangeScatter = r.nextInt(5)-2;
            player.getWorld().addObject(new Projectile(getDamage(), getAttackRange()+rangeScatter, .2, player, new GreenfootImage("images/ItemImages/magicMatter.png"), 30), player.getX()+scatter, player.getY()+scatter);

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