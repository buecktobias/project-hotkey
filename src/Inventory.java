import greenfoot.Actor;
import greenfoot.GreenfootImage;
import greenfoot.World;

import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;

public class Inventory extends Actor implements Fixed {
    //TODO implement "switchTab-Buttons"
    //TODO implement InventoryTab is full message /
    //TODO better colors
    //TODO drag and drop Items to respective slots
    private World world;
    private Player p;
    private LinkedList<Pickable> allItems;
    private LinkedList<Pickable> ArmorList = new LinkedList<>();
    private LinkedList<Pickable> WeaponList = new LinkedList<>();
    private LinkedList<Pickable> ItemList = new LinkedList<>();
    private int inventoryTab = 0;
    private GreenfootImage InventoryScreen = new GreenfootImage("images/MyInventoryV3.png");
    private GreenfootImage PfeilImage31 = new GreenfootImage("images/31.png");
    private GreenfootImage PfeilLinksAktiv = new GreenfootImage("images/PfeilLinksAkiv.png");
    private GreenfootImage PfeilLinksInAktiv = new GreenfootImage("images/PfeilLinksInAkiv.png");


    protected void addedToWorld(World world) {
        inventoryLogic();
    }

    public Inventory(Player p, World world){
        this.p = p;
        this.world = world;
    }

    public void inventoryLogic(){
        getItems(p);
        setImage(InventoryScreen);
        testArrows();
        drawTabFonts(InventoryScreen);
        drawCurrentTab();
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
        int drawAtX = 400;
        int drawAtY = 200;
        int itemsDrawn = 0;
        for (Pickable item: itemsToDraw) {
            if(itemsDrawn == 7){
                drawAtY = drawAtY +32;
                drawAtX = drawAtX - 32*7;
                itemsDrawn = 0;
            }
            drawItemBase();
            InventoryScreen.drawImage(item.getItemImage(), drawAtX, drawAtY);
            drawAtX = drawAtX + 32;
            itemsDrawn++;
        }
    }
    public void drawItemBase(){
        InventoryScreen.setColor(Color.cyan);
        InventoryScreen.fillRect(400,200, 32,32);
    }


    public void testArrows(){
        //Hight  okay
        // use normal images
        // touchups on red frame
        InventoryScreen.drawImage(PfeilImage31, 400, 150);
        InventoryScreen.drawImage(PfeilLinksAktiv, 350, 150);
        InventoryScreen.drawImage(PfeilLinksInAktiv, 300, 150);
    }

    //Getters and Setters
    public int getInventoryTab() {
        return inventoryTab;
    }
    public void setInventoryTab(int inventoryTab) {
        this.inventoryTab = inventoryTab;
    }
}