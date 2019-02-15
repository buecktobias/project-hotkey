package projektHotkey;

import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.World;
import projektHotkey.Interfaces.Attackable;
import projektHotkey.Interfaces.Blocking;
import projektHotkey.Screens.DeathScreen;
import projektHotkey.helper.Direction;


public class Player extends MovingActor implements Attackable, Blocking {
    private int currentSpeed;
    private final int normalSpeed = 2;
    private final int sprintSpeed = 4;


    private final int maxLife = 1000;
    private int life = maxLife;
    private final int minLife = 0;

    private Actor[] inventory = new Actor[9];
    private InventoryDisplayAdapter inventoryAdapter;
    private int waitEndurance=0;
    private final int waitTimeWhenEnduranceIsZero = 5;
    private double enduranceRegeneration = 1;
    private int minEndurance = 0;
    private final int maxEndurance = 1000;
    private double endurance = maxEndurance;
    private final int gameSpeed = 50;
    public Player(){
        Greenfoot.setSpeed(gameSpeed);
    }
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
    private void move(Direction d,int distance){
        super.moveDirection(d,distance);
        calculateEndurance();
        /*
        if(getWorld() instanceof  projektHotkey.Worlds.OpenWorld){
            ((projektHotkey.Worlds.OpenWorld) getWorld()).resetPlayersPosition(this);
        }
        */
    }
    public void addedToWorld(World world){
        //TODO: 2) Erstellen Sie ein neues Objekt vom Typ InventoryDisplayAdapter mit der passenden Größe.
        //         Speichern Sie das Objekt im passenden Attribut.
        //TODO: 3) InventoryDisplayAdapter in der Welt platzieren (0, Höhe der Welt -1) => unten links
        inventoryAdapter = new InventoryDisplayAdapter(inventory.length);
        getWorld().addObject(inventoryAdapter,0,0);
        inventoryAdapter.linkItems(inventory);

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
        performMovement();
        if(this.life < minLife){
            Greenfoot.setWorld(new DeathScreen());
            Greenfoot.stop();
        }
    }
    

}
