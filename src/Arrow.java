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

    }

    public void act() {
        if(getWorld().getObjects(Hostile.class).size()>0) {
            Hostile hostile = getWorld().getObjects(Hostile.class).get(0);
            moveInDirectionOf(hostile);
        } else{
            System.out.println("no Enemy");

        }
    }
    private void move(Direction d, int distance){
        super.moveDirection(d,distance);
    }
}



