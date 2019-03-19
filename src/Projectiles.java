import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.World;
import helper.Direction;

public abstract class Projectiles extends NPC{
    private double PositionX;
    private double PositionY;
    private double xSpeed;
    private double ySpeed;
    private double xAcceleration;
    private double yAcceleration;
    private double damage;
    private double attackRange;
    private double projectileSpeed;
    private MovingActor movingActor;


    public double getPositionX() {
        return PositionX;
    }

    public void setPositionX(double positionX) {
        this.PositionX = positionX;
    }


    public double getPositionY() {
        return PositionY;
    }

    public void setPositionY(double positionY) {
        this.PositionY = positionY;
    }
    private GreenfootImage defaultImage;
    public Projectiles(int damage, int projectileSpeed, int attackRange, MovingActor movingActor) {
        this.damage = damage;
        this.attackRange = attackRange;
        this.projectileSpeed = projectileSpeed;
        this.movingActor = movingActor;
    }
    @Override
    protected void addedToWorld(World world) {
        PositionX = movingActor.getX();
        PositionY = movingActor.getY();
        setLocation((int) PositionX,(int) PositionY);
    }

    @Override
    int getSpeed() {
        return 0;
    }

    @Override
    void setSpeed(int n) {

    }

    public void moveInDirectionOfMouse(){
        int mouseX=Greenfoot.getMouseInfo().getX();
        int mouseY=Greenfoot.getMouseInfo().getY();
        if(mouseX > getX()){
            addToMovingList(Direction.LEFT,10000);
        }else {
            addToMovingList(Direction.RIGHT,10000);
        }
    }
}