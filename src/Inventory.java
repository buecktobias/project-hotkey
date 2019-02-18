import greenfoot.Actor;
import greenfoot.GreenfootImage;

import java.awt.*;
import java.util.List;

public class Inventory extends Actor {

    private Item[] items;
    private int capacity;
    private boolean active = false;
    GreenfootImage InventoryScreen = new GreenfootImage("images/inventoryScreenTest.png");

    public Inventory(){
        this.capacity = 42;
    }

    public void act(){
        if(active){
            List<HUD>  huds = getWorld().getObjectsAt(getX(), getY(), HUD.class);
            if (huds != null){
                drawInventory(huds.get(0));
            }
        }else removeInventory();
    }

    public void drawInventory(HUD hud){
        System.out.println("Inventory open");
        hud.Background.drawImage(InventoryScreen, 42,42);
    }
    public void removeInventory(){

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
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
}
