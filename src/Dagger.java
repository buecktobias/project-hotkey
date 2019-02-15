public class Dagger extends Weapon {

    private double damage;
    private int attackSpeed;
    private int attackRange;


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

}