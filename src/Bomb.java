import greenfoot.Actor;
import greenfoot.GreenfootImage;
import greenfoot.World;

import java.util.List;

public class Bomb extends Environment implements Blocking,ExplodingBehaviour {
    private int attackRange;
    private int damage;
    private GreenfootImage defaultImage = new GreenfootImage("images/Environment/bomb.png");
    public Bomb(){
        attackRange = 150;
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
        setImage(defaultImage);
    }

    @Override
    public void act() {
        List<Player> player = getObjectsInRange(attackRange,Player.class);
        if(player.size() > 0){
            explode(attackRange);
        }

    }
}
