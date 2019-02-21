import helper.Direction;

public class Arrow extends Projectiles {

    private int damage;
    private int attackRange;
    private int projectileSpeed;
    private Player player;

    public Arrow(int damage, int projectileSpeed, int attackRange,Player player) {
        setImage("arrow.png");
        this.damage = damage;
        this.attackRange = attackRange;
        this.projectileSpeed = projectileSpeed;
        this.player = player;
    }

    public void act() {
        for(int i = 0;i < attackRange; i++) {
            move(getDirection(),1);
        }
    }
    private void move(Direction d, int distance){
        super.moveDirection(d,distance);
    }
}



