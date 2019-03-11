import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.World;
import helper.Direction;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;


public class Player extends MovingActor implements Attackable, Blocking {
    private static Player ourInstance = new Player();

    public static Player getInstance() {
        return ourInstance;
    }

    private JSONParser parser = new JSONParser();
    private SettingsWindow settingsWindow = new SettingsWindow();
    private int weaponsPicked = 0;
    private int itemsPicked = 0;
    private int armorPicked = 0;
    private int currentSpeed;
    private int normalSpeed = 2;
    private int level = 1;
    private int lifeGeneration = 1;
    private int sprintSpeed = 4;
    private int attackRange = 500;
    private int damage = 5;
    private int lastFrameSkillWindowOpened = 0;
    private int lastFrameSettingsWindowOpened = 0;
    private int maxLife = 1000;
    private int life = maxLife;
    private int waitEndurance = 0;
    private int minEndurance = 0;
    private int maxEndurance = 1000;
    private final int waitTimeOpenSkillWindow = 10;
    private final int waitTimeOpenSettingsWindow = 10;
    private final int minLife = 0;
    private final int waitTimeWhenEnduranceIsZero = 5;
    private final int gameSpeed = 50;
    private double endurance = maxEndurance;
    private double enduranceRegeneration = 1;
    private boolean skillScreenShown = false;
    private boolean isIActive = false;
    private boolean isSettingsWindowShown = false;
    private final FPS fps = FPS.getInstance();
    private String keyMoveLeft;
    private String keyMoveRight;
    private String keyMoveUp;
    private String keyMoveDown;
    private String keySprint;
    private String keyAttack;
    private String keyPick;
    private String keyOpenSkillWindow;
    private String keyOpenInventar;
    private String keyOpenSettings;
    private String keyShootArrow;
    private Inventory inventoryInstance;
    private SkillWindow skillWindow;
    private Pickable[] equippedItems = new Pickable[7];
    private Pickable[] weaponsArray = new Pickable[30];
    private Pickable[] armorArray = new Pickable[30];
    private Pickable[] itemsArray = new Pickable[30];
    private GreenfootImage defaultImage = new GreenfootImage("src/images/Characters/Player/test_player.png");
    private GreenfootImage move1 = new GreenfootImage("src/images/Characters/Player/player_move1.png");

    @Override
    GreenfootImage[] getMovingAnimationImages() {
        return new GreenfootImage[]{defaultImage};
    }

    Player() {
        this.getImage().clear();
        setImage(defaultImage);
        Greenfoot.setSpeed(gameSpeed);
        updateKeys();
    }

    @Override
    protected void addedToWorld(World world) {
        skillWindow = new SkillWindow(world);
        inventoryInstance = new Inventory(this, world);
        this.setImage(defaultImage);
    }

    private void move(Direction d, int distance) {
        super.moveDirection(d, distance);
        if (getWorld() instanceof OpenWorld) {
            ((OpenWorld) getWorld()).resetPlayersPosition(this);
        }
    }

    private boolean testIfMoveKeys() {
        boolean iskey = false;
        if (Greenfoot.isKeyDown(keyMoveUp)) {
            iskey = true;
        }
        if (Greenfoot.isKeyDown(keyMoveLeft)) {
            iskey = true;
        }
        if (Greenfoot.isKeyDown(keyMoveDown)) {
            iskey = true;
        }
        if (Greenfoot.isKeyDown(keyMoveRight)) {
            iskey = true;
        }
        return iskey;
    }

    private void performMovement() {

        if (Greenfoot.isKeyDown(keyMoveUp)) {
            move(Direction.UP, this.currentSpeed);
        }
        if (Greenfoot.isKeyDown(keyMoveLeft)) {
            move(Direction.LEFT, this.currentSpeed);
        }
        if (Greenfoot.isKeyDown(keyMoveDown)) {
            move(Direction.DOWN, this.currentSpeed);
        }
        if (Greenfoot.isKeyDown(keyMoveRight)) {
            move(Direction.RIGHT, this.currentSpeed);
        }
    }

    private void calculateEndurance() {
        if (Greenfoot.isKeyDown(keySprint) && testIfMoveKeys()) {
            if (endurance > minEndurance) {
                this.currentSpeed = this.sprintSpeed;
            } else {
                this.currentSpeed = this.normalSpeed;
            }
        } else {
            this.currentSpeed = this.normalSpeed;
        }
        if (currentSpeed == this.sprintSpeed) {
            endurance -= this.sprintSpeed;
        } else {
            if (endurance <= minEndurance && waitEndurance < waitTimeWhenEnduranceIsZero * gameSpeed) {
                waitEndurance++;
            } else {
                endurance += enduranceRegeneration;
            }
        }
        if (endurance < minEndurance) {
            waitEndurance = 0;
            endurance = minEndurance;
        } else if (endurance > maxEndurance) {
            endurance = maxEndurance;
        }
    }

    private void attackNPCs() {
        List<NPC> NPCs = getObjectsInRange(attackRange, NPC.class);
        NPCs.removeIf(npc -> !(npc instanceof Attackable));
        if (NPCs.size() > 0) {
            if (NPCs.get(0) instanceof Attackable) {
                Attackable attackable = (Attackable) NPCs.get(0);
                attack(attackable, damage);
                if (attackable.getLife() < 0) {
                    level++;
                }
            }
        }
    }

    private void regenerateLife() {
        if (life < maxLife) {
            life += lifeGeneration;
        }
    }

    private void printCoords() {
        World w = getWorld();
        if (w instanceof OpenWorld) {
            int x = ((OpenWorld) w).getTotalXMovement();
            int y = ((OpenWorld) w).getTotalYMovement();
            print(x + "\n" + y);
        }
    }

    private void showSkillWindow() {
        if (fps.getFrame() - lastFrameSkillWindowOpened > waitTimeOpenSkillWindow) {
            if (skillScreenShown) {
                skillWindow.deleteButtons();
                getWorld().removeObject(skillWindow);
            } else {
                getWorld().addObject(skillWindow, 500, 400);
            }
            skillScreenShown = !skillScreenShown;
            lastFrameSkillWindowOpened = fps.getFrame();
        }
    }

    private void updateKeys() {
        Object obj = null;

        try {
            obj = parser.parse(new FileReader("src/Settings.json"));
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = (JSONObject) obj;
        assert jsonObject != null;
        JSONObject keys = (JSONObject) jsonObject.get("keys");
        keyMoveLeft = keys.get("moveLeft").toString();
        keyMoveDown = keys.get("moveDown").toString();
        keyMoveRight = keys.get("moveRight").toString();
        keyMoveUp = keys.get("moveUp").toString();
        keySprint = keys.get("sprint").toString();
        keyAttack = keys.get("attack").toString();
        keyOpenInventar = keys.get("openInventar").toString();
        keyOpenSettings = keys.get("openSettingWindow").toString();
        keyOpenSkillWindow = keys.get("openSkillWindow").toString();
        keyShootArrow = keys.get("shootArrow").toString();
        keyPick = keys.get("pick").toString();
    }

    private void testKeys() {
        if (Greenfoot.isKeyDown(keyPick)) {
            pick();
        }
        if (Greenfoot.isKeyDown(keyAttack)) {
            attackNPCs();
        }
        if (Greenfoot.isKeyDown(keyOpenSkillWindow)) {
            showSkillWindow();
        }
        if (Greenfoot.isKeyDown(keyOpenSettings)) {
            showSettingsWindow();
        }
        if (Greenfoot.isKeyDown(keyShootArrow)) {
            getWorld().addObject(new Arrow(2, 20, 2, this), this.getX(), this.getY());
        }
        performMovement();
    }

    private void showSettingsWindow() {
        if (fps.getFrame() - lastFrameSettingsWindowOpened > waitTimeOpenSettingsWindow) {
            if (getWorld().getObjects(SettingsWindow.class).size() == 0) {
                this.getWorld().addObject(settingsWindow, 500, 500);
            } else {
                settingsWindow.deleteButtons();
                this.getWorld().removeObject(settingsWindow);
            }
            lastFrameSettingsWindowOpened = fps.getFrame();
        }
    }

    public void act() {
        updateKeys();
        useInventory();
        calculateEndurance();
        super.act();
        testKeys();
        printCoords();
        regenerateLife();
        if (this.life < minLife) {
            Greenfoot.setWorld(new DeathScreen());
            Greenfoot.stop();
        }
    }

    // inventory related methods
    private void pick() {
        List<Item> objs = getWorld().getObjectsAt(getX(), getY(), Item.class);
        Iterator<Item> objsIt = objs.iterator();
        if (!objsIt.hasNext()) {
            return;
        }
        Item Item = objs.get(0);
        if (Item instanceof Pickable) {
            Pickable currentItem = (Pickable) Item;
            switch (currentItem.getItemType()) {
                case "Weapon":
                    if (weaponsPicked < 30) {
                        addItemToInventory(currentItem, weaponsArray);
                        weaponsPicked++;
                    }
                    break;
                case "Armor":
                    if (armorPicked < 30) {
                        addItemToInventory(currentItem, armorArray);
                        armorPicked++;
                    }
                    break;
                case "Consumable":
                    if (itemsPicked < 30) {
                        addItemToInventory(currentItem, itemsArray);
                        itemsPicked++;
                    }
                    break;
            }
        }
    }

    public void addItemToInventory(Pickable currentItem, Pickable[] inventoryArray) {
        if (!anyItemsInArray(inventoryArray)) {
            // old part of if condition inventoryArray != null && java.util.Arrays.asList(inventoryArray).isEmpty()
            currentItem.pick(inventoryArray);
        } else {
            for (Pickable item : inventoryArray) {
                if (item != null) {
                    if (currentItem.compareIDWith(item, inventoryArray)) {
                        break;
                    }
                }
            }
        }
    }

    public void useInventory() {
        String key = Greenfoot.getKey();
        if ((keyOpenInventar.equals(key) && isIActive)) {
            inventoryInstance.deleteButtons();
            getWorld().removeObject(inventoryInstance);
            setIActive(false);
        } else if (keyOpenInventar.equals(key) && !isIActive()) {
            getWorld().addObject(inventoryInstance, getWorld().getWidth() / 2, getWorld().getHeight() / 2);
            setIActive(true);
        }
    }

    public boolean anyItemsInArray(Pickable[] arrayTocheck) {
        for (int i = 0; i < arrayTocheck.length; i++) {
            if (arrayTocheck[i] != null) {
                return true;
            }
        }
        return false;
    }

    //Getters and Setters
    public int getSpeed() {
        return currentSpeed;
    }

    public void setSpeed(int currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public int getMaxLife() {
        return maxLife;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public double getEndurance() {
        return endurance;
    }

    public int getLevel() {
        return level;
    }

    public void setNormalSpeed(int normalSpeed) {
        this.normalSpeed = normalSpeed;
    }

    public void setSprintSpeed(int sprintSpeed) {
        this.sprintSpeed = sprintSpeed;
    }

    public int getNormalSpeed() {
        return normalSpeed;
    }

    public int getSprintSpeed() {
        return sprintSpeed;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getMaxEndurance() {
        return maxEndurance;
    }

    public void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }

    public void setMaxEndurance(int maxEndurance) {
        this.maxEndurance = maxEndurance;
    }

    public boolean isIActive() {
        return isIActive;
    }

    public void setIActive(boolean IActive) {
        isIActive = IActive;
    }

    public Pickable[] getEquippedItems() {
        return equippedItems;
    }

    public void setEquippedItems(Pickable[] equippedItems) {
        this.equippedItems = equippedItems;
    }

    public JSONParser getParser() {
        return parser;
    }

    public void setParser(JSONParser parser) {
        this.parser = parser;
    }

    public int getWeaponsPicked() {
        return weaponsPicked;
    }

    public void setWeaponsPicked(int weaponsPicked) {
        this.weaponsPicked = weaponsPicked;
    }

    public int getItemsPicked() {
        return itemsPicked;
    }

    public void setItemsPicked(int itemsPicked) {
        this.itemsPicked = itemsPicked;
    }

    public int getArmorPicked() {
        return armorPicked;
    }

    public void setArmorPicked(int armorPicked) {
        this.armorPicked = armorPicked;
    }

    public SettingsWindow getSettingsWindow() {
        return settingsWindow;
    }

    public void setSettingsWindow(SettingsWindow settingsWindow) {
        this.settingsWindow = settingsWindow;
    }

    public Pickable[] getWeaponsArray() {
        return weaponsArray;
    }

    public void setWeaponsArray(Pickable[] weaponsArray) {
        this.weaponsArray = weaponsArray;
    }

    public Pickable[] getArmorArray() {
        return armorArray;
    }

    public void setArmorArray(Pickable[] armorArray) {
        this.armorArray = armorArray;
    }

    public Pickable[] getItemsArray() {
        return itemsArray;
    }

    public void setItemsArray(Pickable[] itemsArray) {
        this.itemsArray = itemsArray;
    }
}