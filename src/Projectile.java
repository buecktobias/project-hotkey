import greenfoot.Actor;
import greenfoot.GreenfootImage;

import java.util.List;
import java.util.Random;

public abstract class Projectile extends Item implements CanMove {
    private double velocityX;
    private double velocityY;
    private double speed;
    private int damage;
    private int rotation;
    private double drag;
    private int scatter;
    public boolean shooted = false;
    private Random r = new Random();
    private Actor whoIsShooting;
    abstract public GreenfootImage getDefaultImage();
    public Projectile(int damage, double speed, double drag, int scatter) {
        this.damage = damage;
        this.speed = speed;
        this.drag = drag;
        this.scatter = scatter;
    }
    public void shootFromTo(Actor from,int fromX,int fromY,int toX,int toY){
        shooted = true;
        this.whoIsShooting = from;
        makeShootingSound();
        int destinationX = toX - fromX;
        int destinationY = toY - fromY;
        this.rotation = (int) Math.toDegrees(Math.atan2(destinationY, destinationX));
        GreenfootImage rotatedImage = new GreenfootImage(getDefaultImage());
        rotatedImage.rotate(this.rotation);
        this.setImage(rotatedImage);
        this.rotation = this.rotation + r.nextInt(2*scatter+1)-scatter;
        this.velocityX = Math.cos(Math.toRadians(this.rotation))*this.speed;
        this.velocityY = Math.sin(Math.toRadians(this.rotation))*this.speed;

    }
    public void makeShootingSound(){
        // child classes can override this method to make a shooting sound
    }


    public void hits(Attackable attackable){
        double newLife = attackable.getLife() - damage;
        attackable.setLife(newLife);
        if(newLife < 0) {
            if (whoIsShooting instanceof Player) {
                ((Player) this.whoIsShooting).killedEnemy(attackable);
            }
        }
    }
    public void updatePosition(){

        int newX = getX() + (int) Math.round(velocityX);
        int newY = getY() + (int) Math.round(velocityY);

        List<Entity> intersecting = getIntersectingObjects(Entity.class);
        intersecting.removeIf(obj -> (obj == whoIsShooting) || !(obj instanceof Blocking));

        if(!intersecting.isEmpty()) {
            for (Entity entity : intersecting) {
                if(entity instanceof Attackable) {
                    hits((Attackable) entity);
                    getWorld().removeObject(this);
                    break;
                }else{

                }
            }
            speed = 0;
            return;
        }


        setLocation(newX, newY);

        if(speed <= 0) {
            setShooted(false);
            return;
        }

        this.speed = this.speed - drag;
        this.velocityX = Math.cos(Math.toRadians(this.rotation)) * this.speed;
        this.velocityY = Math.sin(Math.toRadians(this.rotation)) * this.speed;
    }

    public boolean isShooted() {
        return shooted;
    }

    public void setShooted(boolean shooted) {
        this.shooted = shooted;
    }
}