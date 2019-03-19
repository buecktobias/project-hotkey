import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.World;
import helper.Direction;

public class Arrow extends MovingActor{

    private Direction direction;
    private int damage;
    private int attackRange;
    private int projectileSpeed=2;

    public int getSpeed() {
        return projectileSpeed;
    }

    public void setSpeed(int projectileSpeed) {
        this.projectileSpeed = projectileSpeed;
    }

    private Player player;
    private GreenfootImage defaultImage = new GreenfootImage("images/Arrows/Arrow_left.png");

    public Arrow(Player player) {
        setImage(defaultImage);
        this.player = player;
        int mouseX= Greenfoot.getMouseInfo().getX();
        int mouseY=Greenfoot.getMouseInfo().getY();
        direction = this.player.getDirection();
    }

    @Override
    GreenfootImage[] getMovingAnimationImages() {
        return new GreenfootImage[]{defaultImage};
    }

    @Override
    protected void addedToWorld(World world) {
    }
    public void act() {
        moveDirection(direction,10);
    }
}