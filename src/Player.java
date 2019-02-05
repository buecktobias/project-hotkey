import greenfoot.Greenfoot;
import helper.Direction;


public class Player extends MovingActor {
    private int speed;
    private int life = 100;

    private Item[] inventory = new Item[9];


    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
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
    public void setEndurance(double endurance) {
        this.endurance = endurance;
    }

    private double endurance = 1000;
    private double enduranceRegeneration = 1;
    private int minEndurance = 0;
    private int maxEndurance = 1000;
    Player(){
        speed = (int)Math.round(endurance / 200);
    }
    private void move(Direction d,int distance){
        endurance -= speed;
        super.moveDirection(d,speed);
        if(getWorld() instanceof  OpenWorld){
            ((OpenWorld) getWorld()).resetPlayersPosition(this);
        }
        speed = (int)Math.round(endurance / 200);
    }
    private void performMovement() {
        if(Greenfoot.isKeyDown("W")) {
            move(Direction.UP,speed);
        }
        if(Greenfoot.isKeyDown("A")) {
            move(Direction.LEFT,speed);
        }
        if(Greenfoot.isKeyDown("S")) {
            move(Direction.DOWN,speed);
        }
        if(Greenfoot.isKeyDown("D")) {
            move(Direction.RIGHT,speed);
        }
    }



    public void act() {
        performMovement();
        endurance += enduranceRegeneration;
        if(endurance < minEndurance){
            endurance = minEndurance;
        }else if(endurance > maxEndurance){
            endurance = maxEndurance;
        }
        print(endurance);
    }
    

}
