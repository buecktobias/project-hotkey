public class Staff extends Weapon implements Pickable {

    private double damage;
    private int attackSpeed;
    private int attackRange;

    //Pickable Atributes
    public int count = 0;
    public final int id = 1;
    public String name = "The Elder Wand";


    public Staff(int attackRange) {
        this.attackRange = attackRange;
        this.attackSpeed = attackSpeed;
        this.damage = damage;
    }
    public void act() {

    }


    //Pickable Methods
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
