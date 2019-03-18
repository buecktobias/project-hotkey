import greenfoot.GreenfootImage;

public class Dagger extends Weapon implements Equippable{

    private double damage;
    private int attackSpeed;
    private int attackRange;

    // Item Attributes
    ItemManager itemManager = ItemManager.Dagger;
    private int            itemCount;
    private final int      itemSlotId = itemManager.getItemSLOTID();
    private final int      itemId = itemManager.getItemID();
    private final String   itemType = itemManager.getItemTYPE();
    private String         itemName = itemManager.getItemNAME();
    private GreenfootImage itemImage = itemManager.getItemIMAGE();
    private boolean        IEquipped = false;

    //Dagger methods
    public Dagger(int damage, int attackRange, int attackSpeed) {
        this.damage = damage;
        this.attackRange = attackRange;
        this.attackSpeed = attackSpeed;

    }


    /*public void specialEffects() {
        if(poisonApplied) {
            damage = damage * poisonDamage;
        }
        if(fireApplied) {
            damage = damage * fireDamage;
        }
        if(iceApplied) {
            damage = damage * iceDamage;
        }
        if(lightningStrikeApplied) {
            damage = damage * lightningStrikeDamage ;
        }
    }
    */

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

    //Getters and Setters
    public void setDamage(double damage) {
        this.damage = damage;
    }
    public double getDamage() {
        return damage;
    }
    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }
    public int getAttackSpeed() {
        return attackSpeed;
    }
    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
    }
}