import greenfoot.Greenfoot;

import java.util.LinkedList;

public class Bow extends Weapon implements Pickable{

    private int attackSpeed;
    private Player player;

    // Pickable Atributes
    public int itemCount;
    public final int itemId = 3;
    public String itemName = "Dragonslayer GreatBow";

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
    public void setItemNameame(String itemName) {
        this.itemName = itemName;
    }
}
