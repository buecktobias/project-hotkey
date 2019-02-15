package projektHotkey;

public abstract class Weapon extends Item {

    private double poisonDamage = 1;  // sollen multiplier sein
    private double fireDamage = 1;
    private double iceDamage = 1;
    private double lightningStrikeDamage = 1;
    private boolean poisonApplied = false;
    private boolean fireApplied = false;
    private boolean iceApplied = false;
    private boolean lightningStrikeApplied = false;

    public double hit(int damage) {

        return damage;
    }
}
