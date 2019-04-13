import greenfoot.GreenfootImage;

public class Water extends Environment implements HasEffect {
    private final int WATER_DAMAGE = 1;
    public Water(){
        GreenfootImage img = new GreenfootImage(Files.getENVIRONMENT_PATH() + "Water.png");
        img.scale(32,32);
        setImage(img);
    }

    @Override
    public void effects(MovingActor movingActor) {
        if(movingActor instanceof Player){
            ((Player) movingActor).getEffectWindow().addEffect(this.getImage());
        }
        if(movingActor instanceof FireSensitive){
            ((FireSensitive) movingActor).setFireDamage(0);
        }
        if(movingActor instanceof CanSwim) {
            if (movingActor.getSpeed() > 1) {
                movingActor.setSpeed((int) Math.round(movingActor.getSpeed() / 2));
            }
        }else{
            if(movingActor instanceof Attackable) {
                ((Attackable) movingActor).setLife(((Attackable) movingActor).getLife() - WATER_DAMAGE);
            }
        }
    }
}
