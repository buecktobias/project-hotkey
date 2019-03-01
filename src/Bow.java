import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

import java.util.LinkedList;

public class Bow extends Weapon implements Pickable{

    private int attackSpeed;
    private Player player;

    // Pickable Atributes
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
    }

    public void shoot() {
        Arrow arrow = new Arrow(42, 1,10120,player);
        getWorld().addObject(arrow,getX(),getY());
    }

    public void act() {
        if(Greenfoot.isKeyDown("V")) {
            shoot();
        }
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

}
