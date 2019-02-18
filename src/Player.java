import greenfoot.Greenfoot;
import greenfoot.World;
import helper.Direction;

import java.util.List;


public class Player extends MovingActor implements Attackable,Blocking {
    private int currentSpeed;
    private int normalSpeed = 2;
    private int level = 1;
    private int lifeGeneration = 1;

    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }

    public void setNormalSpeed(int normalSpeed) {
        this.normalSpeed = normalSpeed;
    }

    public void setSprintSpeed(int sprintSpeed) {
        this.sprintSpeed = sprintSpeed;
    }

    private int sprintSpeed = 4;
    private int attackRange = 500;
    private int damage = 5;
    private int waitScreen = 0;
    private final int timewaitScreen = 30;
    private int last = 0;
    private SkillWindow skillWindow;
    private boolean skillScreenShown = false;

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
    private int maxLife = 1000;
    private int life = maxLife;
    private final int minLife = 0;
    private Item[] inventory = new Item[10];
    private int waitEndurance=0;
    private final int waitTimeWhenEnduranceIsZero = 5;
    private double enduranceRegeneration = 1;
    private int minEndurance = 0;
    public void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }
    public void setMaxEndurance(int maxEndurance) {
        this.maxEndurance = maxEndurance;
    }

    private int maxEndurance = 1000;
    private double endurance = maxEndurance;
    private final int gameSpeed = 50;

    Player(){
        Greenfoot.setSpeed(gameSpeed);
    }

    @Override
    protected void addedToWorld(World world) {
        skillWindow = new SkillWindow(world);
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
        if (NPCs.size() > 0) {
            attack(NPCs.get(0), damage);
            if(NPCs.get(0).getLife() < 0) {
                skillWindow.showSkills();
                level++;
            }
        }
    }
    public void act() {
        useInventory();
        if(life < maxLife) {
            life += lifeGeneration;
        }
        performMovement();
        calculateEndurance();
        //useInventory();
        if(Greenfoot.isKeyDown("E")){
            //pick();
        }
        if(Greenfoot.isKeyDown("H")){
            attackNPCs();
        }
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
    public void pick(){
        List<Item> objs = getWorld().getObjectsAt(getX(), getY(), Item.class);
        for (int i = 0; i < objs.size() ; i++) {
            if(objs.size()>0){
                for(int j = 0; j < inventory.length; j++){
                    if(inventory[i]==null){
                        Item obj = objs.get(0);
                        inventory[i] = obj;
                        getWorld().removeObject(obj);
                        return;
                    }
                }
            }
        }
    }
    public void useInventory() {
        List<Inventory> inventories = getWorld().getObjects(Inventory.class);
        Inventory i = inventories.get(0);
        String key = Greenfoot.getKey();
        if (("m".equals(key)&& i.isActive()) ){
            i.setActive(false);
            System.out.println("off");
        }else if("m".equals(key) && !i.isActive()) {
            i.setActive(true);
            System.out.println("on");
        }
    }

    //Getters and Setters
    public int getSpeed() {
        return currentSpeed;
    }
    public int getMaxLife() { return maxLife; }
    public int getLife() {
        return life;
    }
    public void setLife(int life) {
        this.life = life;
    }
    public double getEndurance() {
        return endurance;
    }
    public void setEndurance(double endurance) {
        this.endurance = endurance;
    }
    public Item[] getInventory() {
        return inventory;
    }
    public void setInventory(Item[] inventory) {
        this.inventory = inventory;
    }
}
