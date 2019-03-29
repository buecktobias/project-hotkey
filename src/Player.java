import greenfoot.*;
import helper.Direction;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;


public class Player extends MovingActor implements Attackable, Blocking, FireSensitive, CanSwim {
    private static Player ourInstance;

    public static Player getInstance() {
        return ourInstance;
    }

    private JSONParser parser = new JSONParser();
    private SettingsWindow settingsWindow = new SettingsWindow();

    private EffectWindow effectWindow = new EffectWindow();

    public EffectWindow getEffectWindow() {
        return effectWindow;
    }

    private int rightMouseButtonClickedFramesLong = 0;
    private int weaponsPicked = 0;
    private int itemsPicked = 0;
    private int armorPicked = 0;
    private int currentSpeed;
    private int normalSpeed = 2;
    private int level = 0;
    private int exp = 0;
    private int sprintSpeed = 4;
    private int maxLife = 1000;
    private int waitEndurance = 0;
    private int minEndurance = 0;
    private int maxEndurance = 1000;
    private int interactingRange = 128;
    private int damage = 5;
    private int indexOfAC;
    private int damageSoundWaitFrames = 20;
    private final int waitTimeOpenSkillWindow = 10;
    private final int waitTimeOpenSettingsWindow = 10;
    private final int minLife = 0;
    private final int waitTimeWhenEnduranceIsZero = 5;
    private final int gameSpeed = 50;
    private long lastFrameSettingsWindowOpened = 0;
    private long lastFrameSkillWindowOpened = 0;
    private long lastTimeDamageSoundPlayed = 0;
    private final double NORMAL_LIFE_REGENERATION = 0.1;
    private double fireDamageReduction = 0.99;
    private double life = maxLife;
    private double lifeRegeneration = NORMAL_LIFE_REGENERATION;
    private double endurance = maxEndurance;
    private double enduranceRegeneration = 30;
    private double fireDamage = 0;
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
    private String keyOpenSkillWindow;
    private String keyOpenInventar;
    private String keyOpenSettings;
    private String keyUseAc;
    private String keyUpdateAc;
    private String usePrimaryWeapon;
    private String keyOpenChest;
    private Inventory inventoryInstance;
    private SkillWindow skillWindow;
    private Item activeConsumable;
    private Item activeAmmunition;
    private Item[] beltItems = new Item[4];
    private Item[] ammunition = new Item[4];
    private Item[] equippedItems = new Item[6];
    private Item[] weaponsArray = new Item[20];
    private Item[] armorArray = new Item[20];
    private Item[] itemsArray = new Item[20];
    private int[] levelUps = new int[]{25,50,100,150,300,600,1200,1800,3000,5000,8000,15000,30000,50000};
    private GreenfootImage defaultImage;
    private GreenfootImage imageWalking1;
    private GreenfootImage imageWalking2;
    private GreenfootImage imageWalking3;
    private GreenfootImage imageWalking4;

    private GreenfootImage fireEffect = new GreenfootImage(Files.getEffectImagesPath() +"S_Buff08.png");
    private GreenfootImage sprintEffect = new GreenfootImage(Files.getEffectImagesPath() +"S_Buff11.png");
    private GreenfootSound walkingSound = new GreenfootSound("sounds/walkingSound.wav");
    private GreenfootSound gameOverSound = new GreenfootSound("sounds/gameOver.wav");
    private GreenfootSound damageSound = new GreenfootSound("sounds/gotDamage.wav");

    public void movingAnimation(){
        animate(4,imageWalking1,imageWalking2,imageWalking3,imageWalking4);
    }

    private void setPlayerImagesDefault(){
        defaultImage = new GreenfootImage(Files.getPlayerPath() + "player_standing.png");
        imageWalking1 = new GreenfootImage(Files.getPlayerPath() +"player_walking1.png");
        imageWalking2 = new GreenfootImage(Files.getPlayerPath() +"player_walking2.png");
        imageWalking3 = new GreenfootImage(Files.getPlayerPath() +"player_walking3.png");
        imageWalking4 = new GreenfootImage(Files.getPlayerPath() +"player_walking4.png");
    }
    public Player() {
        walkingSound.setVolume(60);
        if(ourInstance == null){
            ourInstance = this;
        }
        setPlayerImagesDefault();
        Greenfoot.setSpeed(gameSpeed);
        updateKeys();
    }

    @Override
    protected void addedToWorld(World world) {
        skillWindow = new SkillWindow(world);
        inventoryInstance = new Inventory(this, world);
        this.setImage(defaultImage);
        getWorld().addObject(effectWindow,450,20);
         indexOfAC = java.util.Arrays.asList(beltItems).indexOf(activeConsumable);
    }

    private void move(Direction d, int distance) {
        super.moveDirection(d, distance);
        walkingSound.play();
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

    private void regenerateLife() {
        if (life < maxLife) {
            life += lifeRegeneration;
        }
    }

    private void showSkillWindow() {
        if (FPS.getFrame() - lastFrameSkillWindowOpened > waitTimeOpenSkillWindow) {
            if (skillScreenShown) {
                skillWindow.deleteButtons();
                getWorld().removeObject(skillWindow);
            } else {
                getWorld().addObject(skillWindow, 500, 400);
            }
            skillScreenShown = !skillScreenShown;
            lastFrameSkillWindowOpened = FPS.getFrame();
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
        keyUseAc = keys.get("useAc").toString();
        keyUpdateAc = keys.get("updateAc").toString();
        keyOpenChest = keys.get("openChest").toString();
    }

    private void testKeys() {
        if(Greenfoot.isKeyDown(keyUpdateAc)){
            updateAC();
        }
        if(Greenfoot.isKeyDown(keyUseAc)){
            useAC();
        }
        if (Greenfoot.isKeyDown(keyOpenSkillWindow)) {
            showSkillWindow();
        }
        if (Greenfoot.isKeyDown(keyOpenSettings)) {
            showSettingsWindow();
        }
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse != null) {
            if (mouse.getButton() == 1) {
                usePrimaryWeapon();
            }
        }
        if(Greenfoot.isKeyDown(keyOpenChest)) {
            openChest();
        }
        performMovement();
    }
    public void killedEnemy(Attackable actor){
        exp += 50;
    }
    public void updateLevel(){
        if(exp > levelUps[level]){
            level++;
        }
    }
    public void attackNPCAt(int x,int y,Weapon weapon){
        int attackRange;
        int damage;
        if(weapon == null){
            attackRange = 100;
            damage = this.damage;
        }else{
            attackRange = weapon.getAttackRange();
            damage = weapon.getDamage();
        }

        List<NPC> npcs = getObjectsInRange(attackRange, NPC.class);
        npcs.removeIf(npc -> !(npc instanceof Attackable));
        npcs.removeIf(npc -> !(Math.abs(npc.getX() - x) < npc.getWidth() && Math.abs(npc.getY() - y) < npc.getHeight()));
        if (!npcs.isEmpty()) {
            Attackable attackable = (Attackable) npcs.get(0);
            attack(attackable, damage);
        }

    }
    private void usePrimaryWeapon() {
        Weapon weapon;
        if(!getWorld().getObjects(WeaponAnimation.class).isEmpty()){
            return;
        }
        if(getPrimaryWeapon() == null) {
            if(getSecondaryWeapon() != null) {
                weapon = getSecondaryWeapon();
            }else{
                MouseInfo mouseInfo = Greenfoot.getMouseInfo();
                attackNPCAt(mouseInfo.getX(),mouseInfo.getY(),null);
                return;
            }
        }else{
            weapon = getPrimaryWeapon();
        }

        getWorld().addObject(
                new WeaponAnimation(weapon.getItemImage(),
                        weapon.getAttackSpeed(),
                        weapon.getAnimationStartDegrees(),
                        weapon.getAnimationStopDegrees()),
                getWorld().getWidth()/2+10,
                getWorld().getHeight()/2+30
        );

        if(weapon instanceof RangedWeapon) {
            MouseInfo mouseInfo = Greenfoot.getMouseInfo();
            if(mouseInfo != null) {
                ((RangedWeapon) weapon).shootFrom(this,mouseInfo.getX(),mouseInfo.getY(),new Arrow(200,10,.1,this,0));
            }
        }else {
            MouseInfo mouseInfo = Greenfoot.getMouseInfo();
            attackNPCAt(mouseInfo.getX(), mouseInfo.getY(),weapon);

        }
    }

    private void showSettingsWindow() {
        if (FPS.getFrame() - lastFrameSettingsWindowOpened > waitTimeOpenSettingsWindow) {
            if (getWorld().getObjects(SettingsWindow.class).size() == 0) {
                this.getWorld().addObject(settingsWindow, 500, 500);
            } else {
                settingsWindow.deleteButtons();
                this.getWorld().removeObject(settingsWindow);
            }
            lastFrameSettingsWindowOpened = FPS.getFrame();
        }
    }

    public void openChest() {
        List<Chest> chests = getObjectsInRange(interactingRange, Chest.class);
        if(!chests.isEmpty()) {
            chests.get(0).open();
        }

    }

    public void act() {
        updateLevel();
        setAC();
        setPlayerImagesDefault();
        updateKeys();
        pick();
        useInventory();
        calculateEndurance();
        getEffects();
        testKeys();
        subtractFireDamageFromLife();
        if(fireDamage > lifeRegeneration){
            effectWindow.addEffect(fireEffect);
            drawFireImage();
        }
        if(isSprinting()){
            effectWindow.addEffect(sprintEffect);
        }
        regenerateLife();
    }

    // inventory related methods
    private void pick() {
        List<Item> objs = getWorld().getObjectsAt(getX(), getY(), Item.class);
        Iterator<Item> objsIt = objs.iterator();
        if (!objsIt.hasNext()) {
            return;
        }
        Item currentItem = objs.get(0);
        switch (currentItem.getItemType()) {
            case "Weapon":
                if (weaponsPicked < 30) {
                    currentItem.pick(weaponsArray);
                    weaponsPicked++;
                }
                break;
            case "Armor":
                if (armorPicked < 30) {
                    currentItem.pick(armorArray);
                    armorPicked++;
                }
                break;
            case "Consumable":
                if (itemsPicked < 30) {
                    if(currentItem instanceof Countable){
                        for (Item item : itemsArray) {
                            if (item != null) {
                                ((Countable) currentItem).compareIDWith(item, itemsArray);
                                itemsPicked++;
                                return;
                            }
                        }
                        currentItem.pick(itemsArray);
                        itemsPicked++;
                    }
                }
                break;
        }
    }
    private void useInventory() {
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

    private void updateAC(){
        // TODO only execute once
        if(indexOfAC == 3){
            indexOfAC = 0;
        }else {
            indexOfAC++;
        }
        activeConsumable = beltItems[indexOfAC];
    }
    private void useAC(){
        if(activeConsumable instanceof  Usable){
            ((Usable) activeConsumable).use(this);
        }
    }
    private void setAC(){
        if(activeConsumable == null){
            for (int i = 0; i < beltItems.length; i++) {
                if(beltItems[i] != null){
                    activeConsumable = beltItems[i];
                    return;
                }
            }
        }
    }

    //Getters and Setters
    public int getSpeed() {
        return currentSpeed;
    }
    public void setSpeed(int currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public double getLife() {
        return life;
    }
    public void setLife(double life) {
        if(life + this.getLifeRegeneration() < this.life && FPS.getFrame() - lastTimeDamageSoundPlayed > damageSoundWaitFrames){
            damageSound.play();
            lastTimeDamageSoundPlayed = FPS.getFrame();
        }
        this.life = life;
        if (this.life < minLife) {
            gameOverSound.play();
            Greenfoot.setWorld(new DeathScreen());
        }
    }

    public boolean isSprinting(){
        return currentSpeed == sprintSpeed;
    }

    public double getEndurance() {
        return endurance;
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

    public int getMaxLife() {
        return maxLife;
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

    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Item[] getEquippedItems() {
        return equippedItems;
    }
    public void setEquippedItems(Item[] equippedItems) {
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

    public Item[] getWeaponsArray() {
        return weaponsArray;
    }
    public void setWeaponsArray(Item[] weaponsArray) {
        this.weaponsArray = weaponsArray;
    }
    public Item[] getArmorArray() {
        return armorArray;
    }
    public void setArmorArray(Item[] armorArray) {
        this.armorArray = armorArray;
    }
    public Item[] getItemsArray() {
        return itemsArray;
    }
    public void setItemsArray(Item[] itemsArray) {
        this.itemsArray = itemsArray;
    }

    public Item[] getBeltItems() {
        return beltItems;
    }
    public void setBeltItems(Item[] beltItems) {
        this.beltItems = beltItems;
    }
    public Item[] getAmmunition() {
        return ammunition;
    }
    public void setAmmunition(Item[] ammunition) {
        this.ammunition = ammunition;
    }

    public Weapon getPrimaryWeapon(){
        return (Weapon)equippedItems[4];
    }
    public Weapon getSecondaryWeapon(){
        return (Weapon)equippedItems[5];
    }

    public double getFireDamageReduction() {
        return fireDamageReduction;
    }
    public void setFireDamageReduction(double fireDamageReduction) {
        this.fireDamageReduction = fireDamageReduction;
    }
    public double getFireDamage() {
        return fireDamage;
    }
    public void setFireDamage(double fireDamage) {
        this.fireDamage = fireDamage;
    }
    public double getNORMAL_LIFE_REGENERATION() {
        return NORMAL_LIFE_REGENERATION;
    }
    public double getLifeRegeneration() {
        return lifeRegeneration;
    }
    public void setLifeRegeneration(double lifeRegeneration) {
        this.lifeRegeneration = lifeRegeneration;
    }

    public int getLevelUpValue(int index) {
        return levelUps[index];
    }
    public int getLevel() {
        return level;
    }
    public int getExp() {
        return exp;
    }

    public Item getActiveConsumable() {
        return activeConsumable;
    }
    public void setActiveConsumable(Item activeConsumable) {
        this.activeConsumable = activeConsumable;
    }
    public Item getActiveAmmunition() {
        return activeAmmunition;
    }
    public void setActiveAmmunition(Item activeAmmunition) {
        this.activeAmmunition = activeAmmunition;
    }
}