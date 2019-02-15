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

    public double getPoisonDamage() {
        return poisonDamage;
    }

    public void setPoisonDamage(double poisonDamage) {
        this.poisonDamage = poisonDamage;
    }

    public double getFireDamage() {
        return fireDamage;
    }

    public void setFireDamage(double fireDamage) {
        this.fireDamage = fireDamage;
    }

    public double getIceDamage() {
        return iceDamage;
    }

    public void setIceDamage(double iceDamage) {
        this.iceDamage = iceDamage;
    }

    public double getLightningStrikeDamage() {
        return lightningStrikeDamage;
    }

    public void setLightningStrikeDamage(double lightningStrikeDamage) {
        this.lightningStrikeDamage = lightningStrikeDamage;
    }

    public boolean isPoisonApplied() {
        return poisonApplied;
    }

    public void setPoisonApplied(boolean poisonApplied) {
        this.poisonApplied = poisonApplied;
    }

    public boolean isFireApplied() {
        return fireApplied;
    }

    public void setFireApplied(boolean fireApplied) {
        this.fireApplied = fireApplied;
    }

    public boolean isIceApplied() {
        return iceApplied;
    }

    public void setIceApplied(boolean iceApplied) {
        this.iceApplied = iceApplied;
    }

    public boolean isLightningStrikeApplied() {
        return lightningStrikeApplied;
    }

    public void setLightningStrikeApplied(boolean lightningStrikeApplied) {
        this.lightningStrikeApplied = lightningStrikeApplied;
    }

    public double hit(int damage) {

        return damage;
    }
}
