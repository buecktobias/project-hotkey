import greenfoot.GreenfootImage;

public class Pig extends Friendly implements Blocking,Attackable, FireSensitive {
    private int speed = 1;
    private final int defaultLife = 800;
    private double life = defaultLife;
    private double fireDamage = 0;
    private double fireDamageReduction = 0.99;
    private int framesShowDamageImage;
    private final int IMAGE_WIDTH = 64;
    private final int IMAGE_HEIGHT = 32;
    private final int RANDOM_MOVE_RANGE = 200;

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

    private GreenfootImage defaultImage;
    private GreenfootImage damageImage;
    @Override
    public double getLife() {
        return life;
    }

    @Override
    public void setLife(double life) {
        if(life < this.life && framesShowDamageImage < -20) {
            framesShowDamageImage = 3;
        }
        if(life < 0){
            getWorld().removeObject(this);
        }
        this.life = life;
    }

    @Override
    void movingAnimation() {
    }

    public Pig(){
        resetPigImages();
        setImage(defaultImage);
    }
    private void resetPigImages(){
        defaultImage = new GreenfootImage(Files.getCHARACTERS_PATH() + "lilpig.png");
        damageImage = new GreenfootImage(Files.getCHARACTERS_PATH() + "lilpig_damage.png");
        defaultImage.scale(IMAGE_WIDTH,IMAGE_HEIGHT);
        damageImage.scale(IMAGE_WIDTH,IMAGE_HEIGHT);
    }

    @Override
    public void act() {
        resetPigImages();
        setImage(defaultImage);
        subtractFireDamageFromLife();
        getEffects();
        if(framesShowDamageImage > 0){
            setImage(damageImage);
        }
        framesShowDamageImage--;
        if(fireDamage > 0.1){
            drawFireImage();
        }
        randomMove(RANDOM_MOVE_RANGE);
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
