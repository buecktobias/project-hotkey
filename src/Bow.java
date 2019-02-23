import greenfoot.Greenfoot;

import java.util.LinkedList;

public class Bow extends Weapon implements Pickable{

    private int attackSpeed;
    private Player player;

    // Pickable Atributes
    public int count;
    public final int id = 3;
    public String name = "Dragonslayer GreatBow";

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
        this.count = this.count + 1;
        inventory.add(this);
        getWorld().removeObject(this);
        System.out.println("Count: " + this.getCount() + "| Id: " + this.getId() + "| Name: " + this.getName());
    }
    public void compareIDs(Player p, LinkedList inventory, Pickable item) {
        if (item.getId() == this.getId()) {
            item.setCount(item.getCount() + 1);
            getWorld().removeObject(this);
            return;
        }else{
            inventory.add(this);
            getWorld().removeObject(this);
            return;
        }
    }

    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
