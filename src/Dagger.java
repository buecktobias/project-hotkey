import java.util.LinkedList;

public class Dagger extends Weapon implements Pickable{

    private double damage;
    private int attackSpeed;
    private int attackRange;

    // Pickable Atributes
    public int itemCount;
    public final int itemId = 3;
    public String itemName = "Zahnstocher";

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
        this.itemCount = this.itemCount + 1;
        inventory.add(this);
        getWorld().removeObject(this);
        System.out.println("Count: " + this.getItemCount() + "| Id: " + this.getItemId() + "| Name: " + this.getItemName());
    }
    public void compareIDs(Player p, LinkedList inventory, Pickable item) {
        if (item.getItemId() == this.getItemId()) {
            item.setItemCount(item.getItemCount() + 1);
            getWorld().removeObject(this);
            return;
        }else{
            inventory.add(this);
            getWorld().removeObject(this);
            return;
        }
    }

    public int getItemCount() {
        return itemCount;
    }
    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }
    public int getItemId() {
        return itemId;
    }
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String name) {
        this.itemName = name;
    }
}