import greenfoot.GreenfootImage;

public class Cobweb extends Environment implements HasEffect {
    private GreenfootImage defaultImage = new GreenfootImage("images/Environment/cobweb.png");
    private int health = 20;
    public Cobweb(){
        defaultImage.scale(32,32);
    }

    @Override
    public void effects(MovingActor movingActor) {

        movingActor.setSpeed(0);
        health--;
        if(movingActor instanceof Player){
            ((Player) movingActor).getEffectWindow().addEffect(this.defaultImage);
        }
    }

    @Override
    public void act() {
        setImage(defaultImage);
        if(health < 0){
            getWorld().removeObject(this);
        }
    }
}
