import greenfoot.World;
import helper.Direction;

public class Arrow extends Projectiles{

    private int damage;
    private int attackRange;
    private int projectileSpeed;
    private Player player;

    public Arrow(int damage, int projectileSpeed, int attackRange,MovingActor movingActor) {
        super("images/Arrows/Arrow_left.png",damage,projectileSpeed,attackRange,movingActor);
    }

    @Override
    protected void addedToWorld(World world) {
        moveInDirectionOfMouse(projectileSpeed);
    }

    public void act() {
        super.act();
    }
    private void move(Direction d, int distance){
        super.moveDirection(d,distance);
    }
}



