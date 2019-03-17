import greenfoot.GreenfootImage;

public class Dagger extends Weapon implements Pickable, Equippable{

    private double damage;
    private int attackSpeed;
    private int attackRange;

    // Pickable Atributes
    ItemManager itemManager = ItemManager.Dagger;
    public int itemCount;
    public final int itemSlotId = itemManager.getItemSLOTID();
    public final int itemId = itemManager.getItemID();
    public final String itemType = itemManager.getItemTYPE();
    public String itemName = itemManager.getItemNAME();
    public GreenfootImage itemImage = itemManager.getItemIMAGE();
    public boolean IEquipped = false;


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

    //Pickable Methods
    public void pick(Pickable[] inventoryArray){
        inventoryArray[0] = this;
        getWorld().removeObject(this);
    }
    public boolean compareIDWith(Pickable item, Pickable[] inventoryArray){
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

    //Pickable Getters and Setters
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