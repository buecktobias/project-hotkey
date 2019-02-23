import greenfoot.Actor;
import greenfoot.GreenfootImage;

import java.awt.*;
import java.util.LinkedList;


public class Inventory extends Actor implements Fixed {
    private Player p;
    private LinkedList<Pickable> items;
    private int capacity;
    GreenfootImage InventoryScreen = new GreenfootImage("images/MyInventoryV2.png");

    public Inventory(Player p){
        this.p = p;
        this.capacity = 42;
        setImage(InventoryScreen);
        getItems(p);
    }
    public void drawItems(){

    }
    public void getItems(Player p){
        items = p.getInventory();
    }

    //shows Text on screen|not working|may be used for WeaponNameDisplay
    public void fontTest(Graphics g, String name){
        g.drawString(name, 100,100);
        Font equipedWeapon = new Font("Arial", Font.BOLD, 12);
        g.setColor(Color.BLACK);
        g.setFont(equipedWeapon);
    }

    //Getters and Setters
}