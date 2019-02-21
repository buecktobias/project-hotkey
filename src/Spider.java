import greenfoot.GreenfootImage;

public class Spider extends Hostile implements Blocking,Attackable {
    private int speed = 2;
    private int life = 10;
    private int visualRange;
    private int attackRange;
    private int damage = 5;
    public Spider(){
        GreenfootImage img = new GreenfootImage("images/Spider.png");
        img.scale(64,32);
        setImage(img);
        visualRange = this.getWidth() *20;
        attackRange = this.getWidth();
    }

    @Override
    public void act() {
        setSpeed(2);
        super.act();
        moveToPlayer(this.visualRange);
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
