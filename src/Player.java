import greenfoot.Greenfoot;
import helper.Direction;


public class Player extends MovingActor implements Attackable,Blocking {
    private int currentSpeed;
    private final int normalSpeed = 2;
    private final int sprintSpeed = 4;


    private final int maxLife = 1000;
    private int life = maxLife;
    private final int minLife = 0;

    private Item[] inventory = new Item[9];

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
        performMovement();
        if(this.life < minLife){
            Greenfoot.setWorld(new DeathScreen());
            Greenfoot.stop();
        }
    }
    

}
