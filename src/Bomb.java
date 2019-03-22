import greenfoot.Actor;
import greenfoot.GreenfootImage;
import greenfoot.World;

import java.util.List;

public class Bomb extends Environment implements Blocking,ExplodingBehaviour {
    private int attackRange;
    private int damage;
    private boolean triggered = false;
    private int framesInWhichItExplodes = 100;
    private GreenfootImage defaultImage = new GreenfootImage("images/Environment/bomb.png");
    private GreenfootImage triggeredImage1 = new GreenfootImage("images/Environment/bomb_triggered1.png");
    private GreenfootImage triggeredImage2 = new GreenfootImage("images/Environment/bomb_triggered2.png");

    public Bomb(){
        attackRange = 250;
        damage = 100;
    }
    public Bomb(int attackRange,int damage){
        this.attackRange = attackRange;
        this.damage = damage;
    }
    public void explode(final int radius){
        List<Environment> environmentList = getObjectsInRange(radius,Environment.class);
        environmentList.removeIf(environment -> !(environment instanceof Explodes));
        getWorld().removeObjects(environmentList);
        List<Actor> actorList = getObjectsInRange(radius, Actor.class);
        actorList.removeIf(actor -> !(actor instanceof Attackable));
        actorList.forEach(attackable -> ((Attackable)attackable).setLife(((Attackable)attackable).getLife() - this.damage));
        getWorld().removeObject(this);
    }

    @Override
    protected void addedToWorld(World world) {
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
            framesInWhichItExplodes--;
            animate(20,triggeredImage1,triggeredImage2);
        }
        if(framesInWhichItExplodes <= 0){
            explode(this.attackRange);
        }

    }
}
