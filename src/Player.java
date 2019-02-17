import greenfoot.Greenfoot;
import helper.Direction;
import images.SkillScreen;

import java.util.List;


public class Player extends MovingActor implements Attackable,Blocking {
    private int currentSpeed;
    private final int normalSpeed = 2;
    private final int sprintSpeed = 4;
    private int attackRange = 500;
    private int damage = 5;
    private SkillScreen skillScreen = new SkillScreen();
    private boolean skillScreenShown = false;
    private final int maxLife = 1000;
    private int life = maxLife;
    private final int minLife = 0;


    private boolean isInventoryActive = false;
    private Item[] inventory = new Item[10];

    private int waitEndurance=0;
    private final int waitTimeWhenEnduranceIsZero = 5;
    private double enduranceRegeneration = 1;
    private int minEndurance = 0;
    private final int maxEndurance = 1000;
    private double endurance = maxEndurance;
    private final int gameSpeed = 50;

    Player(){

        Greenfoot.setSpeed(gameSpeed);
    }
    private void move(Direction d,int distance){
        super.moveDirection(d,distance);
        calculateEndurance();

        if(getWorld() instanceof  OpenWorld){
            ((OpenWorld) getWorld()).resetPlayersPosition(this);
        }
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
        if(Greenfoot.isKeyDown("SHIFT")){
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
            if (endurance==minEndurance && waitEndurance < waitTimeWhenEnduranceIsZero * gameSpeed) {
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
    public void act() {
        useInventory();
        if(Greenfoot.isKeyDown("E")){
            pick();
        }
        if(Greenfoot.isKeyDown("H")){
            List<NPC> NPCs = getObjectsInRange(attackRange,NPC.class);
            if(NPCs.size()>0){
                attack(NPCs.get(0),damage);
            }
        }
        if(Greenfoot.isKeyDown("R")){
            if(skillScreenShown){
                getWorld().removeObject(skillScreen);
            }else {
                getWorld().addObject(skillScreen, 100, 100);
            }
            skillScreenShown = !skillScreenShown;
        }
        performMovement();
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
        Inventory inventory = new Inventory();
        String key = Greenfoot.getKey();
        if("m".equals(key) && !isInventoryActive){
            System.out.println("on");
            isInventoryActive = true;
            //getWorld().addObject(inventory, 100,100);
        }else if (("m".equals(key)) && isInventoryActive){
            System.out.println("off");
            isInventoryActive = false;
            //getWorld().removeObject(inventory);
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
