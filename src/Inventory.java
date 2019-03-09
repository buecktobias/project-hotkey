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
import java.util.LinkedList;

public class Inventory extends Actor implements Fixed {
    // TODO enable player to sort items as wished -> arrays needed, way to safe arrays even if inventory is closed
    // TODO drag and drop Items to respective slots
    // TODO make Armor and Weapons not stackable (remove count variable/ compare id method)
    // TODO !! rework createArrows method
    // TODO GUI overhaul, make everything look nice

    // TODO Testing : check compareIdWith method of Pickable,
    private Player p;
    private World world;
    private Pickable itemForInfo;
    private JSONParser parser = new JSONParser();
    private ItemInfoScreen itemInfoScreenInstance;

    private int itemsDrawn;
    private int drawAtX = 416;
    private int drawAtY = 196;
    private int inventoryTab = 0;
    private boolean infoScreenActive = false;
    private boolean itemsEquipped = false;
    private String keyCreateItemInfoScreen;
    private Pickable[] armorArray;
    private Pickable[] weaponArray;
    private Pickable[] itemArray;
    private Pickable[] equippedItems = new Pickable[7];
    private LinkedList<Button>  buttonList;
    private GreenfootImage InventoryScreen      = new GreenfootImage("images/Hud_Menu_Images/MyInventoryV3.png");
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
        equippedItems = p.getEquippedItems();

        InventoryScreen.clear();
        InventoryScreen  = new GreenfootImage("images/Hud_Menu_Images/MyInventoryV4.png");
        setImage(InventoryScreen);
        drawTabFonts();
        drawCurrentTab();
        if(itemsEquipped){
            drawEquippedItems();
        }

        p.setArmorArray(armorArray);
        p.setWeaponsArray(weaponArray);
        p.setItemsArray(itemArray);

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
    private void addItemToArray(Pickable[] array, Pickable  item){
        for(int i = 0; i < array.length; i++){
            if(array[i] == null){
                addItemToArrayAt(array, item, i);
                return;
            }
        }
    }
    public void addItemToArrayAt(Pickable[] array, Pickable item, int index){
        array[index] = item;
    }
    private void removeItemFromInventory(Pickable item){
        if(item.getItemType().contains("Armor")){
            armorArray[java.util.Arrays.asList(armorArray).indexOf(item)] = null;
        }else if(item.getItemType().contains("Weapon")) {
            weaponArray[java.util.Arrays.asList(weaponArray).indexOf(item)] = null;
        }else {
            itemArray[java.util.Arrays.asList(itemArray).indexOf(item)] = null;
        }
    }

    // draw methods
    private void drawCurrentTab(){
        if(inventoryTab == 0){
            drawTab(weaponArray);
        }else if(inventoryTab == 1){
            drawTab(armorArray);
        }else if(inventoryTab == 2){
            drawTab(itemArray);
        }else{
            setInventoryTab(0);
        }
    }
    private void drawTab(Pickable[] itemsToDraw){
        drawAtX = 416;
        drawAtY = 196;
        if(itemsDrawn == 6){
          drawAtX = 416;
          drawAtY = drawAtY + 55 + 12;
        }
        itemsDrawn = 0;
        for (int i = 0; i < 30; i++) {
            if(itemsToDraw[i] == null){
                drawAtX += 35;
                itemsDrawn++;
            }else{
                drawItemAt(drawAtX,drawAtY, itemsToDraw[i]);
                drawAtX += 35;
                itemsDrawn++;
            }
        }
    }
    private void drawEquippedItems(){
        for(int i = 0; i < 6; i++){
            if(equippedItems[i] != null){
                Pickable item = equippedItems[i];
                switch (item.getItemSlotId()){
                    case 0 :
                        drawItemAt(196,196, item);
                        break;
                    case 1 :
                        drawItemAt(196,286, item);
                        break;
                    case 2:
                        drawItemAt(196,376, item);
                        break;
                    case 3:
                        drawItemAt(196,466, item);
                        break;
                    case 4:
                        drawItemAt(276,196, item);
                        break;
                    case 5:
                        drawItemAt(276,286, item);
                        break;
                    case 6:
                        drawItemAt(276,376, item);
                        break;
                }
            }
        }
    }
    private void drawItemAt(int X, int Y, Pickable item){
        if(item != null){
            InventoryScreen.setColor(Color.WHITE);
            InventoryScreen.fillRect(X, Y, 55,55);
            InventoryScreen.setColor(Color.BLUE);
            InventoryScreen.drawRect(X, Y, 55, 55);
            InventoryScreen.drawRect(X + 1,Y +1, 54, 54);
            InventoryScreen.drawImage(item.getItemImage(), X, Y);
            itemMouseLogic(X, Y, item);
        }
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

    private void itemMouseLogic(int X, int Y, Pickable item){
        int width = 55, height = 55;
        if (Greenfoot.getMouseInfo() != null){
            int mouseX = Greenfoot.getMouseInfo().getX();
            int mouseY = Greenfoot.getMouseInfo().getY();

            //System.out.println(Greenfoot.mouseDragged(item));
            //System.out.println(Greenfoot.mouseDragEnded(item));

            if(mouseX > X - width / 2 && mouseX < X + width  && mouseY < Y + height && mouseY > Y - height / 2) {
                if(item instanceof Equippable){

                    //System.out.println(Greenfoot.mousePressed(item));

                   if(Greenfoot.getMouseInfo().getClickCount() == 2) {
                       if(item.isIEquipped()){
                           unequippItem(item);
                       }else if(item.getItemSlotId() != -1 && !item.isIEquipped()){
                           equipItem(item);
                       }
                   }else if(Greenfoot.mouseDragged(item)){

                       //System.out.println(Greenfoot.mouseDragged(item));

                       drawItemAt(mouseX, mouseY, item);
                   }
                }
                itemHoverInfo(mouseX, mouseY, item);
            }
        }
    }
    private void itemHoverInfo(int X, int Y, Pickable item){
        String InfoOpenInfo = "Item info: X";
        String InfoEquippItem = "equip Item: DoubleLeftClick";
        String InfoMouseButton = "select Item: right Click";
        InventoryScreen.setColor(Color.DARK_GRAY);
        InventoryScreen.fillRect(X, Y, 300, 70);
        InventoryScreen.setColor(Color.lightGray);
        InventoryScreen.drawRect(X, Y, 300, 70);
        InventoryScreen.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 18));
        InventoryScreen.setColor(Color.decode("#FFD700"));
        InventoryScreen.drawString(item.getItemName(), X + 10,Y + 20 );
        InventoryScreen.drawString(InfoOpenInfo, X + 10, Y + 40);
        if(item instanceof Equippable){
            InventoryScreen.drawString(InfoEquippItem,X + 10,Y + 60);
        }
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

    // equipment methods
    public void equipItem(Pickable item){
        // Helmet  0, Chest   1, Legs    2, Boots   3, Pet     4, Primary 5, Secondary 6,
       if(equippedItems[item.getItemSlotId()] == null){
           equippedItems[item.getItemSlotId()] = item;
           removeItemFromInventory(item);
           item.setIEquipped(true);
           itemsEquipped = true;
       }else{
           Pickable oldItem = equippedItems[item.getItemSlotId()];
           equippedItems[item.getItemSlotId()] = item;
           itemsEquipped = true;
           item.setIEquipped(true);
           removeItemFromInventory(item);
           unequippItem(oldItem);
       }
    }
    private boolean unequippItem(Pickable oldItem){
        if(oldItem.getItemType().contains("Armor")){
            if(unequippItem(armorArray, oldItem, p.getArmorPicked())){
                p.setArmorPicked(p.getArmorPicked() +1);
                return true;
            }
        }else if(oldItem.getItemType().contains("Weapon")) {
            if(unequippItem(weaponArray, oldItem, p.getWeaponsPicked())){
                p.setWeaponsPicked(p.getWeaponsPicked() +1);
                return true;
            }
        }
        return false;
    }
    private boolean unequippItem(Pickable[] addIto, Pickable item, int alreadyPicked){
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
    public Pickable getItemForInfo() {
        return itemForInfo;
    }
}