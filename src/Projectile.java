import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.MouseInfo;

import java.util.List;
import java.util.Random;

public class Projectile extends General {
    private double velocityX;
    private double velocityY;
    private double speed;
    private int damage;
    private int rotation;
    private double drag;
    private int scatter;
    private Random r = new Random();

    private GreenfootImage defaultImage;

    private boolean failed;

    public Projectile(int damage, double speed, double drag, GreenfootImage image, int scatter) {
        this.defaultImage = image;
        this.damage = damage;
        this.speed = speed;
        this.drag = drag;
        this.scatter = scatter;

        MouseInfo mouseInfo = Greenfoot.getMouseInfo();
        if(mouseInfo == null) {
            this.failed = true;
            return;
        }

    }
    public void shootFromTo(int fromX,int fromY,int toX,int toY){
        int destinationX = toX - fromX;
        int destinationY = toY - fromY;
        this.rotation = (int) Math.toDegrees(Math.atan2(destinationY, destinationX));
        GreenfootImage rotatedImage = new GreenfootImage(defaultImage);
        rotatedImage.rotate(this.rotation);
        setImage(rotatedImage);
        this.rotation = this.rotation + r.nextInt(2*scatter+1)-scatter;
        this.velocityX = Math.cos(Math.toRadians(this.rotation))*this.speed;
        this.velocityY = Math.sin(Math.toRadians(this.rotation))*this.speed;
    }

    public void act() {
        if(this.failed) {
            getWorld().removeObject(this);
            return;
        }
        updatePosition();
    }
    public void updatePosition(){

        int newX = getX() + (int) Math.round(velocityX);
        int newY = getY() + (int) Math.round(velocityY);

        List<General> intersecting = getIntersectingObjects(General.class);
        intersecting.removeIf(obj -> (obj instanceof Player) || !(obj instanceof Blocking));

        if(!intersecting.isEmpty()) {
            for (General general: intersecting) {
                if(general instanceof Attackable) {
                    ((Attackable) general).setLife(((Attackable) general).getLife() - damage);
                    break;
                }
            }

            getWorld().removeObject(this);
            return;
        }


        setLocation(newX, newY);

        if(speed <= 0) {
            getWorld().removeObject(this);
            return;
        }

        this.speed = this.speed - drag;
        this.velocityX = Math.cos(Math.toRadians(this.rotation))*this.speed;
        this.velocityY = Math.sin(Math.toRadians(this.rotation))*this.speed;
    }

}