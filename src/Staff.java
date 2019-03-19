import greenfoot.GreenfootImage;

public class Staff extends Weapon implements Equippable {

    private double damage;
    private int attackSpeed;
    private int attackRange;

    //Item Attributes
    private ItemManager itemManager = ItemManager.Staff;
    private final int itemSlotId = itemManager.getItemSLOTID();
    private final int itemId = itemManager.getItemID();
    private final String itemType = itemManager.getItemTYPE();
    private String itemName = itemManager.getItemNAME();
    private GreenfootImage itemImage = itemManager.getItemIMAGE();
    private boolean IEquipped = false;
    private Player player;

    public void useWeapon(){
        player.getWorld().addObject(new WeaponAnimation(this.getItemImage(), 7, 80, 180), player.getWorld().getWidth()/2+16, player.getWorld().getHeight()/2);

    }

    public Staff(int attackRange, Player player) {
        this.attackRange = attackRange;
        this.attackSpeed = attackSpeed;
        this.damage = damage;
        this.player = player;
        setImage(itemImage);
    }

    //Item Getters and Setters
    public int getItemSlotId() {
        return itemSlotId;
    }
    public int getItemId() {
        return itemId;
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
