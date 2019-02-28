import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.World;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;


public class Inventory extends Actor implements Fixed {
    // TODO
    // TODO make Items equipable
    // TODO implement pick limit
    // TODO make "switchTab-Buttons" look good
    // TODO drag and drop Items to respective slots -> CREATE SLOTS
    private JSONParser parser = new JSONParser();
    private Player p;
    private World world;
    private Pickable itemForInfo;
    private int itemsDrawn;
    private int drawAtX = 416;
    private int drawAtY = 196;
    private int inventoryTab = 0;
    private boolean infoScreenActive = false;
    private ItemInfoScreen itemInfoScreenInstance;
    private LinkedList<Button>   buttonList;
    private LinkedList<Pickable> allItems;
    private LinkedList<Pickable> ArmorList;
    private LinkedList<Pickable> WeaponList;
    private LinkedList<Pickable> ItemList;
    private GreenfootImage InventoryScreen      = new GreenfootImage("images/Hud_Menu_Images/MyInventoryV3.png");
    private GreenfootImage leftArrowClicked     = new GreenfootImage("images/Arrows/Arrow_left_aktive.png");
    private GreenfootImage leftArrowNotClicked  = new GreenfootImage("images/Arrows/Arrow_left.png");
    private GreenfootImage rightArrowClicked    = new GreenfootImage("images/Arrows/Arrow_right_aktive.png");
    private GreenfootImage rightArrowNotClicked = new GreenfootImage("images/Arrows/Arrow_right.png");
    private String keyCreateItemInfoScreen;

    protected void addedToWorld(World world) {
        ArmorList  = new LinkedList<>();
        WeaponList = new LinkedList<>();
        ItemList   = new LinkedList<>();
        sortItems(p);
        createArrow("left");
        createArrow("right");
        itemInfoScreenInstance = new ItemInfoScreen(p, world);
    }

    public Inventory(Player p, World world){
        this.p = p;
        this.world = world;
        buttonList = new LinkedList<>();
    }
    public void act(){
        Object obj = null;

        try {
            obj = parser.parse(new FileReader("src/Settings.json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = (JSONObject)obj;
        JSONObject keys = (JSONObject) jsonObject.get("keys");
        keyCreateItemInfoScreen = keys.get("createItemInfoScreen").toString();
        InventoryScreen.clear();
        InventoryScreen  = new GreenfootImage("images/Hud_Menu_Images/MyInventoryV4.png");
        setImage(InventoryScreen);
        drawTabFonts();
        drawCurrentTab();
    }

    private void drawTabFonts(){
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
    private void sortItems(Player p){
        allItems = p.getInventory();
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
    private void drawCurrentTab(){
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
    private void drawTab(LinkedList<Pickable> itemsToDraw){
        drawAtX = 416;
        drawAtY = 196;
        if(itemsDrawn == 6){
          drawAtX = 416;
          drawAtY = drawAtY + 55 + 12;
        }
        itemsDrawn = 0;
        for (Pickable item: itemsToDraw) {
            InventoryScreen.setColor(Color.cyan);
            InventoryScreen.fillRect(drawAtX,drawAtY, 55,55);
            InventoryScreen.drawImage(item.getItemImage(), drawAtX, drawAtY);
            itemMouseLogic(drawAtX, drawAtY, item);
            drawAtX = drawAtX + 55 ;
            itemsDrawn++;
        }
    }
    private void itemMouseLogic(int X, int Y, Pickable item){
        int width = 55, height = 55;
        if (Greenfoot.getMouseInfo() != null){
            int mouseX = Greenfoot.getMouseInfo().getX();
            int mouseY = Greenfoot.getMouseInfo().getY();
            if(mouseX > X - width / 2 && mouseX < X + width  && mouseY < Y + height && mouseY > Y - height / 2) {
                itemHoverInfo(mouseX, mouseY, item);
            }
        }
    }
    private void itemHoverInfo(int X, int Y, Pickable item){
        String itemInfo = "Item info: X";
        String equipItem = "equip Item: C";
        InventoryScreen.setColor(Color.DARK_GRAY);
        InventoryScreen.fillRect(X, Y, 150, 70);
        InventoryScreen.setColor(Color.lightGray);
        InventoryScreen.drawRect(X, Y, 150, 70);
        InventoryScreen.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 18));
        InventoryScreen.setColor(Color.decode("#FFD700"));
        InventoryScreen.drawString(item.getItemName(), X + 10,Y + 20 );
        InventoryScreen.drawString(itemInfo, X + 10, Y + 40);
        InventoryScreen.drawString(equipItem,X + 10,Y + 60);
        itemForInfo = item;
        createItemInfoScreen();
    }
    private void createArrow(String position){
        Button button;
        GreenfootImage buttonImgUnClicked;
        GreenfootImage buttonImgClicked;
            if(position.equals("left")){
                buttonImgUnClicked = leftArrowNotClicked;
                buttonImgClicked = leftArrowClicked;
                buttonImgUnClicked.scale(20, 30);
                buttonImgClicked.scale(20, 30);
                button = new Button(buttonImgUnClicked,buttonImgClicked) {
                    @Override
                    public void clicked() {
                        if (inventoryTab > 0) {
                            inventoryTab--;
                        } else {
                            inventoryTab = 2;
                        }
                    }
                };
                buttonList.add(button);
                world.addObject(button, 440,165);
            }else{
                buttonImgUnClicked = rightArrowNotClicked;
                buttonImgClicked = rightArrowClicked;
                buttonImgUnClicked.scale(20, 30);
                buttonImgClicked.scale(20, 30);
                button = new Button(buttonImgUnClicked,buttonImgClicked) {
                    @Override
                    public void clicked() {
                        if (inventoryTab < 2 ) {
                            inventoryTab++;
                        } else {
                            inventoryTab = 0;
                        }
                    }
                };
                buttonList.add(button);
                world.addObject(button, 780,165);
            }
        }
    public void deleteButtons(){
        for(Button button:buttonList){
            world.removeObject(button);
        }
    }
    private void createItemInfoScreen(){
        String key = Greenfoot.getKey();
        if ((keyCreateItemInfoScreen.equals(key) && infoScreenActive) ){
            createArrow("left");
            createArrow("right");
            getWorld().removeObject(itemInfoScreenInstance);
            infoScreenActive = false;
        }else if(keyCreateItemInfoScreen.equals(key) && !infoScreenActive) {
            deleteButtons();
            getWorld().addObject(itemInfoScreenInstance, getWorld().getWidth()/2, getWorld().getHeight()/2);
            infoScreenActive = true;
        }
    }

    //Getters and Setters
    public int getInventoryTab() {
        return inventoryTab;
    }
    public void setInventoryTab(int inventoryTab){
        this.inventoryTab = inventoryTab;
    }
    public Pickable getItemForInfo() {
        return itemForInfo;
    }
    public void setItemForInfo(Pickable itemForInfo) {
        this.itemForInfo = itemForInfo;
    }
}