import greenfoot.Actor;
import greenfoot.GreenfootImage;
import greenfoot.World;

import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;

public class Inventory extends Actor implements Fixed {
    // TODO clear Method/ fix bug where items are drawn new each time inventory is opened
    // TODO implement InventoryTab is full message /
    // TODO drag and drop Items to respective slots
    // TODO better colors/Item background
    // TODO make "switchTab-Buttons" look good
    // TODO ItemInfo displayed when clicked and/or mouse hovers over it
    private Player p;
    private World world;
    private int inventoryTab = 0;
    private LinkedList<Button>   buttonList;
    private LinkedList<Pickable> allItems;
    private LinkedList<Pickable> ArmorList  = new LinkedList<>();
    private LinkedList<Pickable> WeaponList = new LinkedList<>();
    private LinkedList<Pickable> ItemList   = new LinkedList<>();
    private GreenfootImage InventoryScreen  = new GreenfootImage("images/Hud_Menu_Images/MyInventoryV3.png");

    protected void addedToWorld(World world) {
        inventoryLogic();
    }

    public Inventory(Player p, World world){
        this.p = p;
        this.world = world;
        buttonList = new LinkedList<>();
        buttonList = new LinkedList<>();
    }

    public void inventoryLogic(){
        getItems(p);
        setImage(InventoryScreen);
        drawTabFonts();
        createLeftArrow();
        createRighttArrow();
    }
    public void drawTabFonts(){
        String armor = "Armor";
        String weapons = "Weapons";
        String items = "Items";
        InventoryScreen.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        InventoryScreen.setColor(Color.WHITE);
        InventoryScreen.drawString(weapons,   470,175);
        InventoryScreen.drawString(armor,     580,175);
        InventoryScreen.drawString(items,     680,175);
        InventoryScreen.setColor(Color.RED);
        if(inventoryTab == 0){
            InventoryScreen.drawString(weapons,   470,175);
        }else if(inventoryTab == 1){
            InventoryScreen.drawString(armor,     580,175);
        }else if(inventoryTab == 2){
            InventoryScreen.drawString(items,     680,175);
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
            if(item.getItemType().equals("Weapon")) {
                WeaponList.add(item);
            }else if(item.getItemType().equals("Armor")){
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

    public void clearInventoryScreen(){
        deleteButtons();
    }


    private void createLeftArrow(){
        Button button;
        GreenfootImage buttonImgUnClicked;
        GreenfootImage buttonImgClicked;
            buttonImgUnClicked = new GreenfootImage("images/Arrows/Arrow_left.png");
            buttonImgClicked = new GreenfootImage("images/Arrows/Arrow_left_aktive.png");
            buttonImgUnClicked.scale(20, 30);
            buttonImgClicked.scale(20, 30);
            button = new Button(buttonImgUnClicked,buttonImgClicked) {
                @Override
                public void clicked() {
                    if(0 < inventoryTab){
                        inventoryTab--;
                    }else{
                        inventoryTab = 2;
                    }
                    System.out.println(inventoryTab);
                }
            };
            buttonList.add(button);
            world.addObject(button, 440,165);

    }
    private void createRighttArrow(){
        Button button;
        GreenfootImage buttonImgUnClicked;
        GreenfootImage buttonImgClicked;
        buttonImgUnClicked = new GreenfootImage("images/Arrows/Arrow_right.png");
        buttonImgClicked = new GreenfootImage("images/Arrows/Arrow_right_aktive.png");
        buttonImgUnClicked.scale(20, 30);
        buttonImgClicked.scale(20, 30);
        button = new Button(buttonImgUnClicked,buttonImgClicked) {
            @Override
            public void clicked() {
                if(inventoryTab < 2){
                    inventoryTab++;
                }else{
                    inventoryTab = 0;
                }
                System.out.println(inventoryTab);
            }
        };
        buttonList.add(button);
        world.addObject(button, 780,165);

    }
    public void deleteButtons(){
        for(Button button:buttonList){
            world.removeObject(button);
        }
        buttonList = new LinkedList<>();
    }

    public void act(){
            drawTabFonts();
            drawCurrentTab();
    }




    //Getters and Setters
    public int getInventoryTab() {
        return inventoryTab;
    }
    public void setInventoryTab(int inventoryTab) {
        this.inventoryTab = inventoryTab;
    }

}