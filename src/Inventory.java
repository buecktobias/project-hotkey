import greenfoot.Actor;
import greenfoot.GreenfootImage;

import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;


public class Inventory extends Actor implements Fixed {
    private Player p;
    private LinkedList<Pickable> allItems;
    private LinkedList<Pickable> ArmorList = new LinkedList<>();
    private LinkedList<Pickable> WeaponList = new LinkedList<>();
    private LinkedList<Pickable> ItemList = new LinkedList<>();
    private int inventoryTab = 0;
    GreenfootImage InventoryScreen = new GreenfootImage("images/MyInventoryV3.png");

    public Inventory(Player p){
        this.p = p;
        setImage(InventoryScreen);
        drawTabFonts(InventoryScreen);
        System.out.println("I made it 1");
        getItems(p);
        System.out.println("I made it3");
        Iterator<Pickable> allItemsIT = allItems.iterator();
        if (!allItemsIT.hasNext()) {
            return;
        }else{
            System.out.println("Inventory says:" + allItemsIT.next().getItemName() + "now in Inventory");
        }
        drawCurrentTab();
    }
    public void act(){
        //System.out.println("I made it4");
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
        System.out.println("I made it2");
        allItems = p.getInventory();

    }
    public void sortItems(LinkedList<Pickable> allItems){
        Iterator<Pickable> allItemsIT = allItems.iterator();
        if (!allItemsIT.hasNext()) {
            return;
        }
        for(Pickable item :allItems){
            if(item.getItemType() == "Weapon"){
                WeaponList.add(item);
            }else if(item.getItemType() == "Armor"){
                ArmorList.add(item);
            }else {
                ItemList.add(item);
            }
        }
    }
    public void drawCurrentTab(){
        sortItems(allItems);
        if(inventoryTab == 0){
            drawTab(WeaponList);
        }else if(inventoryTab == 1){
            drawTab(ArmorList);
        }else if(inventoryTab == 2){
            drawTab(ItemList);
        }else{
            setInventoryTab(0);
        }
    }

    public void drawTab(LinkedList<Pickable> itemsToDraw){
        int drawAtX = 500;
        int drawAtY = 500;
        int itemsDrawn = 0;
        for (Pickable item: itemsToDraw) {
            /*
            if(itemsDrawn == 7){
                drawAtY = drawAtY +32;
                drawAtX = drawAtX - 32*7;
                itemsDrawn = 0;
            }
            */
            System.out.println(item.getItemName());
            InventoryScreen.drawImage(item.getItemImage(), drawAtX, drawAtY);
            drawAtX = drawAtX + 32;
            itemsDrawn++;
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