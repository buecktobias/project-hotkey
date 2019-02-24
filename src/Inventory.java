import greenfoot.Actor;
import greenfoot.GreenfootImage;

import java.awt.*;
import java.util.LinkedList;


public class Inventory extends Actor implements Fixed {
    private Player p;
    private LinkedList<Pickable> items;
    private LinkedList<Pickable> ArmorTab;
    private LinkedList<Pickable> WeaponTab;
    private LinkedList<Pickable> ItemTab;
    private int inventoryTab = 0;

    private LinkedList<Button> buttonList;

    private int capacity;
    GreenfootImage InventoryScreen = new GreenfootImage("images/MyInventoryV2.png");

    public Inventory(Player p){
        this.p = p;
        this.capacity = 42;
        setImage(InventoryScreen);
        drawTabFonts(InventoryScreen);
        getItems(p);
        sortItems(items);
        //drawCurrentTab();
    }

    public void drawTabFonts(GreenfootImage g){
        String armor = "Armor";
        String weapons = "Weapons";
        String items = "Items";
        g.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        g.setColor(Color.WHITE);
        g.drawString(weapons,   470,175);
        g.drawString(armor,     580,175);
        g.drawString(items,     680,175);
        g.setColor(Color.RED);
        if(inventoryTab == 0){
            g.drawString(weapons,   470,175);
        }else if(inventoryTab == 1){
            g.drawString(armor,     580,175);
        }else if(inventoryTab == 2){
            g.drawString(items,     680,175);
        }
    }
    public void getItems(Player p){
        items = p.getInventory();

    }
    public void sortItems(LinkedList<Pickable> items){
        for(Pickable item :items){
            if(item.getItemType() == "Weapon"){
                WeaponTab.add(item);
            }else if(item.getItemType() == "Armor"){
                ArmorTab.add(item);
            }else {
                ItemTab.add(item);
            }
        }
    }
    public void drawCurrentTab(){
        if(inventoryTab == 0){
            drawTab(WeaponTab);
        }else if(inventoryTab == 1){
            drawTab(ArmorTab);
        }else if(inventoryTab == 2){
            drawTab(ItemTab);
        }else{
            setInventoryTab(0);
        }
    }

    public void drawTab(LinkedList<Pickable> itemsToDraw){
        for (Pickable item:itemsToDraw) {
            return;
        }
    }

    //Getters and Setters

    public int getInventoryTab() {
        return inventoryTab;
    }
    public void setInventoryTab(int inventoryTab) {
        this.inventoryTab = inventoryTab;
    }
}