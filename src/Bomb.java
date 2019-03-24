import greenfoot.Actor;
import greenfoot.GreenfootImage;
import greenfoot.GreenfootSound;
import greenfoot.World;

import java.util.List;

public class Bomb extends Environment implements Blocking,ExplodingBehaviour {
    private int attackRange;
    private int damage;
    private boolean triggered = false;
    private int framesInWhichItExplodes = 100;
    private double sizeImage=32;
    private GreenfootImage defaultImage = new GreenfootImage("images/Environment/bomb.png");
    private GreenfootSound explosionSound = new GreenfootSound("sounds/explosion.wav");
    private GreenfootImage triggeredImage1;
    private GreenfootImage triggeredImage2;
    private int triggerRange = 200;
    public Bomb(){
        attackRange = 300;
        damage = 50_000;
    }
    public Bomb(int attackRange,int damage){
        this.attackRange = attackRange;
        this.damage = damage;
    }
    public void resetImages(){
        triggeredImage1 = new GreenfootImage("images/Environment/bomb_triggered1.png");
        triggeredImage2 = new GreenfootImage("images/Environment/bomb_triggered2.png");
    }
    public double distanceBetween(Actor a1,Actor a2){
        return Math.abs(a1.getX() - a2.getX()) + Math.abs(a1.getY() - a2.getY());
    }
    public void explode(final int radius){
        explosionSound.play();
        List<Environment> environmentList = getObjectsInRange(radius,Environment.class);
        environmentList.removeIf(environment -> !(environment instanceof Explodes));
        getWorld().removeObjects(environmentList);
        List<Actor> actorList = getObjectsInRange(radius, Actor.class);
        actorList.removeIf(actor -> !(actor instanceof Attackable));
        actorList.forEach(attackable -> ((Attackable)attackable).setLife(((Attackable) attackable).getLife()- (this.damage / distanceBetween(this,attackable))));
        ((OpenWorld)getWorld()).resetPlayersPosition(Player.getInstance());
        getWorld().removeObject(this);
    }

    @Override
    protected void addedToWorld(World world) {
        resetImages();
        defaultImage.scale(32,32);
        triggeredImage1.scale(32,32);
        triggeredImage2.scale(32,32);
        setImage(defaultImage);
    }

    @Override
    public void act() {
        List<Player> player = getObjectsInRange(attackRange,Player.class);
        if(player.size() > 0){
            this.triggered = true;
        }
        if(triggered){
            resetImages();
            framesInWhichItExplodes--;
            sizeImage *= 1.01;
            triggeredImage1.scale((int)Math.round(sizeImage),(int)Math.round(sizeImage));
            triggeredImage2.scale((int)Math.round(sizeImage),(int)Math.round(sizeImage));
            animate((int)1 + framesInWhichItExplodes / 2,triggeredImage1,triggeredImage2);
        }
        if(framesInWhichItExplodes <= 0){
            explode(this.attackRange);
        }

    }
}
