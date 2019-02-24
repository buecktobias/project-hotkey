import java.util.LinkedList;

public class Staff extends Weapon implements Pickable {

    private double damage;
    private int attackSpeed;
    private int attackRange;

    //Pickable Atributes
    ItemManager itemManager = ItemManager.Staff;
    public int itemCount = 0;
    public final int itemId = itemManager.getItemID();
    public final String itemType = itemManager.getItemTYPE();
    public String itemName = itemManager.getItemNAME();


    public Staff(int attackRange) {
        this.attackRange = attackRange;
        this.attackSpeed = attackSpeed;
        this.damage = damage;
    }
    public void act() {

    }

    //Pickable Methods
    public void pick(Player p, LinkedList inventory){
        this.itemCount = this.itemCount + 1;
        inventory.add(this);
        getWorld().removeObject(this);
        System.out.println("Count: " + this.getItemCount() + "| Id: " + this.getItemId() + "| Name: " + this.getItemName());
    }
    public void compareIDs(Player p, LinkedList inventory, Pickable item) {
            if (item.getItemId() == this.getItemId()) {
                item.setItemCount(item.getItemCount() + 1);
                getWorld().removeObject(this);
                return;
            }else{
                inventory.add(this);
                getWorld().removeObject(this);
                return;
            }
    }

    public int getItemCount() {
        return itemCount;
    }
    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }
    public int getItemId() {
        return itemId;
    }
    public String getItemName() {
        return itemName;
    }
}
