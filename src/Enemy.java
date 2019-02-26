import greenfoot.GreenfootImage;

public class Enemy extends Hostile implements Attackable,Blocking {
    private int visualRange = 500;
    private int attackRange;
    private int damage = 5;
    private int speed = 1;
    private int life = 100;
    private int hitboxRadius=getWidth();
    public Enemy(){
        GreenfootImage img = new GreenfootImage("images/Characters/Enemy.png");
        img.scale(128,128);
        setImage(img);
        attackRange =  img.getWidth();
    }
    @Override
    public int getLife() {
        return life;
    }

    @Override
    public void setLife(int life) {
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

    @Override
    public void act() {
        super.act();
        if(moveToPlayer(this.visualRange)){

        }else{
            randomMove(500);
        }
        if(attackPlayer(this.attackRange, this.damage)){
        }
        if(life <0){
            getWorld().removeObject(this);
        }
    }

}
