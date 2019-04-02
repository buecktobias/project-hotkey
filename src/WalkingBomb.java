import greenfoot.GreenfootImage;

public class WalkingBomb extends Hostile implements Attackable, Blocking, FireSensitive {
    private int visualRange = 500;
    private int attackRange = 150;
    private int damage = 100;
    private int speed = 1;
    private double life = 100;
    private double fireDamage = 0;
    private GreenfootImage defaultImage = new GreenfootImage(Files.getCHARACTERS_PATH() + "WalkingBomb.png");
    private final int RANDOM_MOVE_RANGE = 500;
    private final double DRAW_FIRE_IMAGE = 0.1;  // to which amount of fire Damage a fire Image will be drawn
    private final int IMAGE_WIDTH = 64;
    private final int IMAGE_HEIGHT = 64;
    @Override
    public double getFireDamage() {
        return fireDamage;
    }

    @Override
    public void setFireDamage(double fireDamage) {
        this.fireDamage = fireDamage;
    }

    @Override
    public void movingAnimation() {
        animate(defaultImage);
    }

    @Override
    int getAttackRange() {
        return attackRange;
    }

    @Override
    int getDamage() {
        return damage;
    }

    public WalkingBomb(){
        defaultImage.scale(IMAGE_WIDTH,IMAGE_HEIGHT);
        setImage(defaultImage);
    }
    @Override
    public double getLife() {
        return life;
    }

    @Override
    public void setLife(double life) {
        if(life < 0){
            explode();
        }
        this.life = life;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
    }



    private void explode(){
        getWorld().addObject(new Bomb(this.attackRange,this.damage),this.getX(),this.getY());
        getWorld().removeObject(this);
    }

    @Override
    public boolean attackPlayer(int attackRange, int damage) {
        Player player = getPlayer(attackRange);
        if(player!=null){
            explode();
            return true;
        }
        return false;
    }

    @Override
    public void act() {
        subtractFireDamageFromLife();
        getEffects();
        if(fireDamage > DRAW_FIRE_IMAGE){
            drawFireImage();
        }
        if(moveToPlayer(this.visualRange)){
            attackPlayer(this.attackRange, this.damage);
        }else{
            randomMove(RANDOM_MOVE_RANGE);
        }
    }

}
