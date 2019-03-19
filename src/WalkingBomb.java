import greenfoot.Actor;
import greenfoot.GreenfootImage;

import java.util.List;

public class WalkingBomb extends Hostile implements Attackable, Blocking, FireSensitive {
    private int visualRange = 500;
    private int attackRange;
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
    GreenfootImage[] getMovingAnimationImages() {
        return new GreenfootImage[]{defaultImage};
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
        img.scale(128,128);
        setImage(img);
        defaultImage = img;
        attackRange =  img.getWidth();
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
        final int explodeRadius = attackRange;
        List<Environment> environmentList = getObjectsInRange(explodeRadius,Environment.class);
        getWorld().removeObjects(environmentList);
        List<Actor> actorList = getObjectsInRange(explodeRadius, Actor.class);
        actorList.removeIf(actor -> !(actor instanceof Attackable));
        actorList.forEach(attackable -> ((Attackable)attackable).setLife(((Attackable)attackable).getLife() - this.damage));
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
        if(life <0){
            explode();
        }
    }

}
