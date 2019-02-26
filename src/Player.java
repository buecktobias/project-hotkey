import greenfoot.Greenfoot;
import greenfoot.World;
import helper.Direction;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class Player extends MovingActor implements Attackable,Blocking {
    private int currentSpeed;
    private int normalSpeed = 2;
    private int level = 1;
    private int lifeGeneration = 1;
    private int sprintSpeed = 4;
    private int attackRange = 500;
    private int damage = 5;
    private int waitScreen = 0;
    private final int timewaitScreen = 30;
    private int last = 0;
    private SkillWindow skillWindow;
    private boolean skillScreenShown = false;
    private int maxLife = 1000;
    private int life = maxLife;
    private final int minLife = 0;
    private LinkedList<Pickable> inventory = new LinkedList<>();
    private int waitEndurance=0;
    private final int waitTimeWhenEnduranceIsZero = 5;
    private double enduranceRegeneration = 1;
    private int minEndurance = 0;
    private boolean isIActive = false;
    private Inventory inventoryInstance;
    private int maxEndurance = 1000;
    private double endurance = maxEndurance;
    private final int gameSpeed = 50;

    Player(){
        Greenfoot.setSpeed(gameSpeed);
    }

    @Override
    protected void addedToWorld(World world) {
        skillWindow = new SkillWindow(world);
        inventoryInstance = new Inventory(this, world);
    }
    private void move(Direction d, int distance){
        super.moveDirection(d,distance);
        if(getWorld() instanceof  OpenWorld){
            ((OpenWorld) getWorld()).resetPlayersPosition(this);
        }
    }
    private boolean testIfMoveKeys(){
        boolean iskey = false;
        if(Greenfoot.isKeyDown("W")) {
            iskey=true;
        }
        if(Greenfoot.isKeyDown("A")) {
            iskey=true;
        }
        if(Greenfoot.isKeyDown("S")) {
            iskey=true;
        }
        if(Greenfoot.isKeyDown("D")) {
            iskey=true;
        }
        return iskey;
    }
    private void performMovement() {

        if(Greenfoot.isKeyDown("W")) {
            move(Direction.UP,this.currentSpeed);
        }
        if(Greenfoot.isKeyDown("A")) {
            move(Direction.LEFT,this.currentSpeed);
        }
        if(Greenfoot.isKeyDown("S")) {
            move(Direction.DOWN,this.currentSpeed);
        }
        if(Greenfoot.isKeyDown("D")) {
            move(Direction.RIGHT,this.currentSpeed);
        }
    }
    public void calculateEndurance(){
        if(Greenfoot.isKeyDown("SHIFT") && testIfMoveKeys()){
            if(endurance > minEndurance) {
                this.currentSpeed = this.sprintSpeed;
            }else{
                this.currentSpeed = this.normalSpeed;
            }
        }else{
            this.currentSpeed = this.normalSpeed;
        }
        if(currentSpeed == this.sprintSpeed){
            endurance -= this.sprintSpeed;
        }else {
            if (endurance<=minEndurance && waitEndurance < waitTimeWhenEnduranceIsZero * gameSpeed) {
                waitEndurance++;
            }else {
                endurance += enduranceRegeneration;
            }
        }
        if(endurance < minEndurance){
            waitEndurance=0;
            endurance = minEndurance;
        }else if(endurance > maxEndurance){
            endurance = maxEndurance;
        }
    }
    public void attackNPCs() {
        List<NPC> NPCs = getObjectsInRange(attackRange, NPC.class);
        NPCs.removeIf(npc -> !(npc instanceof Attackable));
        if (NPCs.size() > 0) {
            if(NPCs.get(0) instanceof Attackable) {
                Attackable attackable = (Attackable) NPCs.get(0);
                attack(attackable, damage);
                if (attackable.getLife() < 0) {
                    level++;
                }
            }
        }
    }
    public void regenerateLife(){
        if(life < maxLife) {
            life += lifeGeneration;
        }
    }
    public void printCoords(){
        World w = getWorld();
        if(w instanceof OpenWorld){
            int x=((OpenWorld) w).getTotalXMovement();
            int y=((OpenWorld) w).getTotalYMovement();
            print(x +"\n"+y);
        }
    }
    public void act() {
        useInventory();
        performMovement();
        calculateEndurance();
        super.act();
        if(Greenfoot.isKeyDown("E")){
            pick();
        }
        if(Greenfoot.isKeyDown("H")){
            attackNPCs();
        }
        printCoords();
        waitScreen++;
        if(timewaitScreen < waitScreen) {
            if (Greenfoot.isKeyDown("R")) {
                if (skillScreenShown) {
                    skillWindow.deleteButtons();
                    getWorld().removeObject(skillWindow);
                } else {
                    getWorld().addObject(skillWindow, 500, 400);
                }
                skillScreenShown = !skillScreenShown;
                waitScreen = 0;
            }
        }
        if(this.life < minLife){
            Greenfoot.setWorld(new DeathScreen());
            Greenfoot.stop();
        }
    }
    public void pick() {
        List<Item> objects = getWorld().getObjectsAt(getX(), getY(), Item.class);
        Iterator<Item> objectsIt = objects.iterator();
        if (!objectsIt.hasNext()) {
            return;
        }
        Item currentItem = objects.get(0);
        if (currentItem instanceof Pickable) {
            if (inventory != null && inventory.isEmpty()) {
                ((Pickable) currentItem).pick(this, inventory);
                return;
            }else{
                for (Pickable item : inventory) {
                    ((Pickable) currentItem).compareIDs(this, inventory, item);
                }
            }
        }
    }
    public void useInventory() {
        String key = Greenfoot.getKey();
        if (("m".equals(key)&& isIActive) ){
            inventoryInstance.deleteButtons();
            getWorld().removeObject(inventoryInstance);
            setIActive(false);
        }else if("m".equals(key) && !isIActive()) {
            getWorld().addObject(inventoryInstance, getWorld().getWidth()/2, getWorld().getHeight()/2);
            setIActive(true);
        }
    }

    //Getters and Setters
    public int      getSpeed() {
        return currentSpeed;
    }
    public void     setSpeed(int currentSpeed) {
        this.currentSpeed = currentSpeed;
    }
    public int      getMaxLife() { return maxLife; }
    public int      getLife() {
        return life;
    }
    public void     setLife(int life) {
        this.life = life;
    }
    public double   getEndurance() {
        return endurance;
    }
    public int      getLevel() {
        return level;
    }
    public void     setNormalSpeed(int normalSpeed) {
        this.normalSpeed = normalSpeed;
    }
    public void     setSprintSpeed(int sprintSpeed) {
        this.sprintSpeed = sprintSpeed;
    }
    public int      getNormalSpeed() {
        return normalSpeed;
    }
    public int      getSprintSpeed() {
        return sprintSpeed;
    }
    public int      getDamage() {
        return damage;
    }
    public void     setDamage(int damage) {
        this.damage = damage;
    }
    public int      getMaxEndurance() {
        return maxEndurance;
    }
    public void     setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }
    public void     setMaxEndurance(int maxEndurance) {
        this.maxEndurance = maxEndurance;
    }
    public boolean  isIActive() {
        return isIActive;
    }
    public void     setIActive(boolean IActive) {
        isIActive = IActive;
    }
    public LinkedList<Pickable> getInventory() {
        return inventory;
    }
    public void setInventory(LinkedList<Pickable> inventory) {
        this.inventory = inventory;
    }
}