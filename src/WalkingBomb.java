import greenfoot.GreenfootImage;

public class WalkingBomb extends Hostile implements Attackable, Blocking, FireSensitive {
    private int visualRange = 500;
    private int attackRange = 200;
    private int damage = 200;
    private int speed = 1;
    private double life = 100;
    private int hitboxRadius = getWidth();
    private GreenfootImage defaultImage = new GreenfootImage("src/images/Characters/lilpig.png");

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
        GreenfootImage img = new GreenfootImage("images/Characters/Enemy.png");
        img.scale(64,64);
        setImage(img);
        defaultImage = img;
    }
    @Override
    public double getLife() {
        return life;
    }

    @Override
    public void setLife(double life) {
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

    GreenfootImage getAngryImage() {
        return defaultImage;
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
        if(moveToPlayer(this.visualRange)){
            attackPlayer(this.attackRange, this.damage);
        }else{
            randomMove(500);
        }
        if(life < 0){
            explode();
        }
    }

}
