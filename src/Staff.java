public class Staff extends Weapon implements Pickable {

    private double damage;
    private int attackSpeed;
    private int attackRange;

    public int count;
    public final int id = 1;


    public Staff(int attackRange) {
        this.attackRange = attackRange;
        this.attackSpeed = attackSpeed;
        this.damage = damage;
    }
    public void act() {

    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
