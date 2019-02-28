import greenfoot.GreenfootImage;

public class Spider extends Hostile implements Blocking,Attackable {
    private final int defaultSpeed = 2;
    private int speed = defaultSpeed;
    private int life = 10;
    private int attackSpeed = 20;
    private int visualRange;
    private int lastFrameAttacked=0;
    private int attackRange;

    @Override
    int getAttackRange() {
        return attackRange;
    }

    @Override
    int getDamage() {
        return damage;
    }

    private final GreenfootImage angryImage = new GreenfootImage("images/Characters/Spider_RED_EYES.png");
    private final GreenfootImage defaultImage = new GreenfootImage("images/Characters/Spider.png");
    private final GreenfootImage move1 = new GreenfootImage("images/Characters/Spider_Move1.png");
    private final GreenfootImage attack1 = new GreenfootImage("images/Characters/Spider_Attack1.png");
    private int damage = 100;
    public Spider(){
        attack1.scale(64,32);
        move1.scale(64,32);
        defaultImage.scale(64,32);
        setImage(defaultImage);
        visualRange = this.getWidth() *3;
        attackRange = this.getWidth()+this.getHeight();
    }

    @Override
    public void act() {
        setSpeed(defaultSpeed);
        if(moveToPlayer(this.visualRange)){
            angryImage.scale(64,32);
            lastFrameAttacked = attack(lastFrameAttacked,attackSpeed);
            setImage(angryImage);
            moveAnimation(angryImage,move1);
        }else{
            defaultImage.scale(64,32);
            setImage(defaultImage);
            if(randomMove(200)){
                moveAnimation(defaultImage,move1);
            }
        }
        if(life <0){
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

    @Override
    public int getLife() {
        return life;
    }

    @Override
    public void setLife(int life) {
        this.life = life;
    }
}
