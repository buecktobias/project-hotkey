import greenfoot.GreenfootImage;

public class Spider extends Hostile implements Blocking,Attackable {
    private final int defaultSpeed = 2;
    private int speed = defaultSpeed;
    private int life = 10;
    private int visualRange;
    private int attackRange;
    private final GreenfootImage angryImage = new GreenfootImage("images/Characters/Spider_RED_EYES.png");
    private final GreenfootImage defaultImage = new GreenfootImage("images/Characters/Spider.png");
    private int damage = 5;
    public Spider(){
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
            setImage(angryImage);
        }else{
            defaultImage.scale(64,32);
            setImage(defaultImage);
            randomMove(200);
        }
        if(attackPlayer(this.attackRange, this.damage)){
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
