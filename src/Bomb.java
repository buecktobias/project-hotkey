import greenfoot.Actor;
import greenfoot.GreenfootImage;
import greenfoot.World;

import java.util.List;

public class Bomb extends Environment implements Blocking,ExplodingBehaviour {
    private int attackRange = 100;
    private int damage = 200;
    private GreenfootImage defaultImage = new GreenfootImage("images/Environment/bomb.png");
    public void explode(final int radius){
        List<Environment> environmentList = getObjectsInRange(radius,Environment.class);
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
