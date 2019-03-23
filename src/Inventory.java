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
import java.util.LinkedList;

public class Inventory extends GUI implements Fixed {
    //TODO GUI overhaul, make everything look nice

    //TODO enable Player to summon different familiars -> will not be done in inventory

    //TODO fix known issues:
    // 1) item info screen does not open/close as it is supposed;

    private Player p;
    private World world;
    private Item itemForInfo;
    private JSONParser parser = new JSONParser();
    private ItemInfoScreen itemInfoScreenInstance;
    private int itemsDrawn = 0;
    private int inventoryTab = 0;
    private boolean infoScreenActive = false;
    private boolean itemsEquipped = false;
    private String keyCreateItemInfoScreen;
    private Item[] armorArray;
    private Item[] weaponArray;
    private Item[] itemArray;
    private Item[] beltItems;
    private Item[] ammunition;
    private Item[] equippedItems = new Item[7];
    private LinkedList<Button>  buttonList;
    private GreenfootImage InventoryScreen      = new GreenfootImage("images/Hud_Menu_Images/MyInventoryV5.png");
    private GreenfootImage leftArrowClicked     = new GreenfootImage("images/Arrows/Arrow_left_aktive.png");
    private GreenfootImage leftArrowNotClicked  = new GreenfootImage("images/Arrows/Arrow_left.png");
    private GreenfootImage rightArrowClicked    = new GreenfootImage("images/Arrows/Arrow_right_aktive.png");
    private GreenfootImage rightArrowNotClicked = new GreenfootImage("images/Arrows/Arrow_right.png");

    protected void addedToWorld(World world) {
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
        armorArray  = p.getArmorArray();
        weaponArray = p.getWeaponsArray();
        itemArray   = p.getItemsArray();
        beltItems = p.getBeltItems();
        ammunition = p.getAmmunition();
        equippedItems = p.getEquippedItems();

        InventoryScreen.clear();
        InventoryScreen  = new GreenfootImage("images/Hud_Menu_Images/MyInventoryV5.png");
        setImage(InventoryScreen);
        drawTabFonts();
        drawCurrentTab();
        if(itemsEquipped){
            drawEquippedWeaponry();
            drawEquippedConsumables(ammunition, 204, 404);
            drawEquippedConsumables(beltItems, 204, 496);
        }

        p.setArmorArray(armorArray);
        p.setWeaponsArray(weaponArray);
        p.setItemsArray(itemArray);
        p.setBeltItems(beltItems);
        p.setAmmunition(ammunition);
        p.setEquippedItems(equippedItems);
        if(p.getActiveConsumable() == null){
            p.setActiveConsumable(beltItems[0]);
        }

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
    }

    // "Item Array" methods
    private void addItemToArray(Item[] array, Item  item){
        for(int i = 0; i < array.length; i++){
            if(array[i] == null){
                addItemToArrayAt(array, item, i);
                return;
            }
        }
    }
    public void addItemToArrayAt(Item[] array, Item item, int index){
        array[index] = item;
    }
    private int getIndexOfItemInArray(Item item, Item[] itemArray){
         return java.util.Arrays.asList(itemArray).indexOf(item);
    }
    private void removeItemFromInventory(Item item){
        // TODO Switch case?
        if(item.getItemType().contains("Armor")){
            armorArray[getIndexOfItemInArray(item, armorArray)] = null;
        }else if(item.getItemType().contains("Weapon")) {
            weaponArray[getIndexOfItemInArray(item, weaponArray)] = null;
        }else {
            itemArray[getIndexOfItemInArray(item, itemArray)] = null;
        }
    }

    // draw methods
    private void drawCurrentTab(){
        switch (inventoryTab){
            case 1:
                drawTab(armorArray);
                break;
            case 2:
                drawTab(itemArray);
                break;
            default:
                drawTab(weaponArray);
                break;
        }
    }
    private void drawTab(Item[] itemsToDraw){
        int drawAtX = 754;
        int drawAtY = 226;
        if(itemsDrawn == 4){
          drawAtX = 754;
          drawAtY = drawAtY + 55 + 12;
            itemsDrawn = 0;
        }
        for (int i = 0; i < 20; i++) {
            if(itemsToDraw[i] == null){
                drawAtX -= 55 + 12;
                itemsDrawn++;
            }else{
                drawItemAt(drawAtX,drawAtY, itemsToDraw[i]);
                drawAtX -= 55 + 12;
                itemsDrawn++;
            }
        }
    }
    private void drawEquippedWeaponry(){
        for(int i = 0; i < 6; i++){
            if(equippedItems[i] != null){
                Item item = equippedItems[i];
                switch (item.getItemSlotId()){
                    case 0 :
                        drawItemAt(204,312, item);
                        break;
                    case 1 :
                        drawItemAt(204,312, item);
                        break;
                    case 2:
                        drawItemAt(375,312, item);
                        break;
                    case 3:
                        drawItemAt(459,312, item);
                        break;
                    case 4:
                        drawItemAt(375,226, item);
                        break;
                    case 5:
                        drawItemAt(289,226, item);
                        break;
                }
                // Helmet  0
                // Chest   1
                // Legs    2
                // Boots   3
                // Primary 4
                // Secondary 5
            }
        }
    }
    private void drawEquippedConsumables(Item[] itemArray, int startX, int startY){
            for (Item item: itemArray) {
                if(item != null)
                drawItemAt(startX, startY, item);
                startX = startX + 55 + 12 + 40;
            }
    }
    private void drawItemAt(int X, int Y, Item item){
        drawItemAt(InventoryScreen, X, Y,item);
        itemMouseLogic(X, Y, item);
    }
    private void drawTabFonts(){
        String armor = "Armor";
        String weapons = "Weapons";
        String items = "Items";
        InventoryScreen.setFont(GUILargeFont);
        InventoryScreen.setColor(Color.WHITE);
        InventoryScreen.drawString(weapons,   560,196);
        InventoryScreen.drawString(armor,     660,196);
        InventoryScreen.drawString(items,     740,196);
        InventoryScreen.setColor(Color.decode("#FFD700"));
        if(inventoryTab == 0){
            InventoryScreen.drawString(weapons,   560,196);
        }else if(inventoryTab == 1){
            InventoryScreen.drawString(armor,     660,196);
        }else if(inventoryTab == 2){
            InventoryScreen.drawString(items,     740,196);
        }
    }

    private void itemMouseLogic(int X, int Y, Item item){
        int width = 55, height = 55;
        if (Greenfoot.getMouseInfo() != null){
            int mouseX = Greenfoot.getMouseInfo().getX();
            int mouseY = Greenfoot.getMouseInfo().getY();
            if(mouseX > X - width / 2 && mouseX < X + width  && mouseY < Y + height && mouseY > Y - height / 2) {
                if(item instanceof Equippable){
                   if(Greenfoot.getMouseInfo().getClickCount() == 2) {
                       if(item.isIEquipped()){
                           obtainItem(item);
                       }else if(item.getItemSlotId() != -1 && !item.isIEquipped()){
                           equipItem(item);
                       }
                   }else if(Greenfoot.mouseDragged(item)){
                       drawItemAt(mouseX, mouseY, item);
                   }
                }
                itemHoverInfo(mouseX, mouseY, item);
            }
        }
    }
    private void itemHoverInfo(int X, int Y, Item item){
        String InfoOpenInfo = "Item info: X";
        String InfoEquippItem = "equip Item: DoubleClick";
        String InfoMouseButton = "select Item: right Click";
        InventoryScreen.setColor(Color.DARK_GRAY);
        InventoryScreen.fillRect(X, Y, 300, 70);
        InventoryScreen.setColor(Color.lightGray);
        InventoryScreen.drawRect(X, Y, 300, 70);
        InventoryScreen.setFont(GUILargeFont);
        InventoryScreen.setColor(Color.decode("#FFD700"));
        InventoryScreen.drawString(item.getItemName(), X + 10,Y + 20 );
        InventoryScreen.drawString(InfoOpenInfo, X + 10, Y + 40);
        if(item instanceof Equippable){
            InventoryScreen.drawString(InfoEquippItem,X + 10,Y + 60);
        }
        itemForInfo = item;
        createItemInfoScreen();
    }

    private void createArrow(String position) {
        if (position.equals("left")) {
            createButton(leftArrowNotClicked, leftArrowClicked, position,540, 189);
        }else{
            createButton(rightArrowNotClicked, rightArrowClicked, position, 820, 189);
        }
    }
    private void createButton(GreenfootImage buttonImgUnClicked1, GreenfootImage buttonImgClicked1, String position, int X, int Y) {
        Button button;
        GreenfootImage buttonImgUnClicked;
        GreenfootImage buttonImgClicked;
        buttonImgUnClicked = buttonImgUnClicked1;
        buttonImgClicked = buttonImgClicked1;
        buttonImgUnClicked.scale(20, 30);
        buttonImgClicked.scale(20, 30);
        button = new Button(buttonImgUnClicked, buttonImgClicked) {
            @Override
            public void clicked() {
                if (position.equals("left")) {
                    if (inventoryTab > 0) {
                        inventoryTab--;
                    } else {
                        inventoryTab = 2;
                    }
                } else {
                    if (inventoryTab < 2) {
                        inventoryTab++;
                    } else {
                        inventoryTab = 0;
                    }
                }
            }
        };
        buttonList.add(button);
        world.addObject(button, X, Y);
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

    // equipment methods
    private void equipItem(Item item){
        switch(item.getItemSlotId()){
            case 6:
                equipItem(ammunition, item);
                item.setIEquipped(true);
                itemsEquipped = true;
                break;
            case 7:
                equipItem(beltItems, item);
                break;
            default:
                 equipItem(equippedItems, item);
                 break;
        }
        // Helmet  0, Chest   1, Legs    2, Boots   3, Primary 4, Secondary 5, ammunition 6, consumable 7
    }
    private void equipItem(Item[] itemArray, Item item){
        if(item.getItemType().contains("Consumable")){
            addItemToArray(itemArray, item);
        }else{
            int index = item.getItemSlotId();
            if(itemArray[index] == null){
                itemArray[index] = item;
            }else{
                Item oldItem = itemArray[index];
                obtainItem(oldItem);
                itemArray [index] = item;
            }
        }
        removeItemFromInventory(item);
        item.setIEquipped(true);
        itemsEquipped = true;
    }
    private void obtainItem(Item oldItem){
        switch (oldItem.getItemType()) {
            case "Weapon":
                if (obtainItem(weaponArray, oldItem, p.getWeaponsPicked())) {
                    p.setWeaponsPicked(p.getWeaponsPicked() + 1);
                }
                break;
            case "Armor":
                if (obtainItem(armorArray, oldItem, p.getArmorPicked())) {
                    p.setArmorPicked(p.getArmorPicked() + 1);
                }
                break;
            default:
                obtainItem(itemArray, oldItem, p.getItemsPicked());
                p.setItemsPicked(p.getItemsPicked() + 1);
                break;
        }
    }
    private boolean obtainItem(Item[] addIto, Item item, int alreadyPicked){
        if(alreadyPicked < 30){
            addItemToArray(addIto, item);
            item.setIEquipped(false);
            equippedItems[item.getItemSlotId()] = null;
            return true;
        }
        else return false;
    }

    //Getters and Setters
    public void setInventoryTab(int inventoryTab){
        this.inventoryTab = inventoryTab;
    }
    public Item getItemForInfo() {
        return itemForInfo;
    }
}