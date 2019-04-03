import greenfoot.GreenfootImage;

public class Cobweb extends Environment implements HasEffect,Explodes {
    private GreenfootImage defaultImage = new GreenfootImage(Files.getENVIRONMENT_PATH() +  "cobweb.png");
    private int durability = 20;
    public Cobweb(){
        defaultImage.scale(32,32);
        setImage(defaultImage);
    }

    @Override
    public void effects(MovingActor movingActor) {
        movingActor.setSpeed(0);
        durability--;
        if(durability < 0){
            getWorld().removeObject(this);
        }
        if(movingActor instanceof Player){
            ((Player) movingActor).getEffectWindow().addEffect(this.defaultImage);
        }
    }
}
