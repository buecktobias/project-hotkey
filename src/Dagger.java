import java.util.LinkedList;

public class Dagger extends Weapon implements Pickable{

    private double damage;
    private int attackSpeed;
    private int attackRange;

    // Pickable Atributes
    public int count;
    public final int id = 3;
    public String name = "Zahnstocher";

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getDamage() {
        return damage;
    }

    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
    }

    public Dagger(int damage, int attackRange, int attackSpeed) {
        this.damage = damage;
        this.attackRange = attackRange;
        this.attackSpeed = attackSpeed;

    }


    /*public void specialEffects() {
        if(poisonApplied) {
            damage = damage * poisonDamage;
        }
        if(fireApplied) {
            damage = damage * fireDamage;
        }
        if(iceApplied) {
            damage = damage * iceDamage;
        }
        if(lightningStrikeApplied) {
            damage = damage * lightningStrikeDamage ;
        }
    }
    */

    //Pickable Methods
    public void pick(Player p, LinkedList inventory){
        this.count = this.count + 1;
        inventory.add(this);
        getWorld().removeObject(this);
        System.out.println("Count: " + this.getCount() + "| Id: " + this.getId() + "| Name: " + this.getName());
    }
    public void compareIDs(Player p, LinkedList inventory, Pickable item) {
        if (item.getId() == this.getId()) {
            item.setCount(item.getCount() + 1);
            getWorld().removeObject(this);
            return;
        }else{
            inventory.add(this);
            getWorld().removeObject(this);
            return;
        }
    }

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