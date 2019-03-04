import greenfoot.World;
import helper.Direction;

public class Arrow extends Projectiles {

    private int damage;
    private int attackRange;
    private int projectileSpeed;
    private Player player;
    private final double velocity = 2;

    public Arrow(int damage, int projectileSpeed, int attackRange,Player player) {
        setImage("images/Arrows/Arrow_left.png");
        this.damage = damage;
        this.attackRange = attackRange;
        this.projectileSpeed = projectileSpeed;
        this.player = player;
    }

    @Override
    protected void addedToWorld(World world) {
        moveInDirectionOfMouse(velocity);
    }

    public void act() {
        /*for(int i = 0;i < attackRange; i++) {
            move(player.getDirection(),1);
        }
        */
        super.act();
    }
    private void move(Direction d, int distance){
        super.moveDirection(d,distance);
    }
}



