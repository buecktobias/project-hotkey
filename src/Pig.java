import greenfoot.GreenfootImage;

public class Pig extends Friendly implements Blocking,Attackable, FireSensitive {
    private int speed = 1;
    private final int defaultLife = 20;
    private double life = defaultLife;
    private double fireDamage = 0;
    private double fireDamageReduction = 0.99;

    public double getFireDamageReduction() {
        return fireDamageReduction;
    }

    public void setFireDamageReduction(double fireDamageReduction) {
        this.fireDamageReduction = fireDamageReduction;
    }

    @Override
    public double getFireDamage() {
        return fireDamage;
    }

    @Override
    public void setFireDamage(double fireDamage) {
        this.fireDamage = fireDamage;
    }

    private GreenfootImage defaultImage = new GreenfootImage("images/Characters/lilpig.png");
    @Override
    public double getLife() {
        return life;
    }

    @Override
    public void setLife(double life) {
        this.life = life;
    }

    @Override
    void movingAnimation() {
    }

    public Pig(){
        defaultImage.scale(64,32);
        setImage(defaultImage);
    }

    @Override
    public void act() {
        subtractFireDamageFromLife();
        getEffects();
        randomMove(200);
        if(life<0){
            getWorld().removeObject(this);
        }
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
