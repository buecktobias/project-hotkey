public class Arrow extends Projectiles {

    private double damage;
    private int attackSpeed;
    private int attackRange;

    public Arrow(double damage, int attackSpeed, int attackRange) {
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.attackRange = attackRange;
    }
    public void act() {
        move(getDirection());
    }
}

