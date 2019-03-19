public abstract class Weapon extends Item implements Equippable {
    private int damage = 1;
    private int attackSpeed = 1;
    private int attackRange = 1;

    private int animationStartDegrees = 0;
    private int animationStopDegrees = 180;

    private double poisonDamage = 1;  // sollen multiplier sein
    private double fireDamage = 1;
    private double iceDamage = 1;
    private double lightningStrikeDamage = 1;
    private boolean poisonApplied = false;
    private boolean fireApplied = false;
    private boolean iceApplied = false;
    private boolean lightningStrikeApplied = false;

    //Getter & Settter
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
    public int getDamage() { return damage; }
    public void setDamage(int damage) { this.damage = damage; }
    public int getAttackRange() { return attackRange; }
    public void setAttackRange(int attackRange) { this.attackRange = attackRange; }
    public int getAttackSpeed() { return attackSpeed; }
    public void setAttackSpeed(int attackSpeed) { this.attackSpeed = attackSpeed; }

    public int getAnimationStartDegrees() { return animationStartDegrees; }
    public void setAnimationStartDegrees(int animationStartDegrees) { this.animationStartDegrees = animationStartDegrees; }
    public int getAnimationStopDegrees() { return animationStopDegrees; }
    public void setAnimationStopDegrees(int animationStopDegrees) { this.animationStopDegrees = animationStopDegrees; }
}
