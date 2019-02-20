import greenfoot.Actor;
import greenfoot.GreenfootImage;

import java.awt.*;

public class Inventory extends Actor implements Fixed {
    private Player p;
    private Item[] items;
    private int capacity;
    GreenfootImage InventoryScreen = new GreenfootImage("images/inventoryScreenTest.png");

    public Inventory(Player p){
        this.p = p;
        this.capacity = 42;
        setImage(InventoryScreen);
    }
    public void drawInventory(HUD hud){
        System.out.println("Inventory open");
        hud.Background.drawImage(InventoryScreen, getWorld().getWidth()/4, 125);
    }

    public void getItems(Player p){
        items =  p.getInventory();
    }

    //shows Text on screen|not working|may be used for WeaponNameDisplay
    public void fontTest(Graphics itemName, String name){
        itemName.drawString(name, 100,100);
        Font equipedWeapon = new Font("Arial", Font.BOLD, 12);
        itemName.setColor(Color.BLACK);
        itemName.setFont(equipedWeapon);
    }


    //Getters and Setters
}