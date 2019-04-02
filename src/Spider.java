import greenfoot.GreenfootImage;

public class Spider extends Hostile implements Blocking,Attackable,FireSensitive {
    private final int defaultSpeed = 2;
    private int speed = defaultSpeed;
    private double life = 10;
    private int attackSpeed = 20;
    private int visualRange;
    private int attackRange;
    private double fireDamage = 0;
    private final int IMAGE_WIDTH = 64;
    private final int IMAGE_HEIGHT = 32;
    private final int RANDOM_MOVE_RANGE = 200;
    @Override
    public double getFireDamage() {
        return fireDamage;
    }

    @Override
    public void setFireDamage(double fireDamage) {
        this.fireDamage = fireDamage;
    }

    @Override
    int getAttackRange() {
        return attackRange;
    }

    @Override
    int getDamage() {
        return damage;
    }

    public GreenfootImage getAngryImage() {
        return angryImage;
    }

    private final GreenfootImage angryImage = new GreenfootImage(Files.getCHARACTERS_PATH() + "Spider_RED_EYES.png");
    private final GreenfootImage defaultImage = new GreenfootImage(Files.getCHARACTERS_PATH() + "Spider.png");
    private final GreenfootImage move1 = new GreenfootImage(Files.getCHARACTERS_PATH() + "Spider_Move1.png");
    private final GreenfootImage attack1 = new GreenfootImage(Files.getCHARACTERS_PATH() +"Spider_Attack1.png");
    private final GreenfootImage moveAngry1 = new GreenfootImage(Files.getCHARACTERS_PATH() +"Spider_Angry_Move1.png");

    private int damage = 100;


    public GreenfootImage getDefaultImage() {
        return defaultImage;
    }
    public void movingAnimation() {
        if(getPlayer(visualRange) != null){
            animate(4,angryImage,moveAngry1);
        }
        animate(4,defaultImage, move1);
    }
    public boolean moveToPlayer(int visualRange){
        Player player = getPlayer(visualRange);
        this.setImage(getAngryImage());
        if(player != null) {
            moveInDirectionOf(player);
            return true;
        }else{
            return false;

        }
    }

    public Spider(){
        attack1.scale(IMAGE_WIDTH,IMAGE_HEIGHT);
        move1.scale(IMAGE_WIDTH,IMAGE_HEIGHT);
        defaultImage.scale(IMAGE_WIDTH,IMAGE_HEIGHT);
        angryImage.scale(IMAGE_WIDTH,IMAGE_HEIGHT);
        moveAngry1.scale(IMAGE_WIDTH,IMAGE_HEIGHT);
        setImage(defaultImage);
        visualRange = this.getWidth() * 3;
        attackRange = this.getWidth() + this.getHeight();
    }


    @Override
    public void act() {
        subtractFireDamageFromLife();
        setSpeed(defaultSpeed);
        getEffects();
        if(moveToPlayer(this.visualRange)){
            angryImage.scale(IMAGE_WIDTH,IMAGE_HEIGHT);
            attack(attackSpeed,attack1);
        }else{
            defaultImage.scale(IMAGE_WIDTH,IMAGE_HEIGHT);
            setImage(defaultImage);
            randomMove(this.RANDOM_MOVE_RANGE);
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

    @Override
    public double getLife() {
        return life;
    }

    @Override
    public void setLife(double life) {
        this.life = life;
        if(life < 0){
            getWorld().removeObject(this);
        }
    }
}
