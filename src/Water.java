import greenfoot.GreenfootImage;

public class Water extends Environment implements HasEffect {
    private int waterDamage = 1;
    public Water(){
        GreenfootImage img = new GreenfootImage("images/Environment/water.jpg");
        img.scale(32,32);
        setImage(img);
    }

    @Override
    public void effects(MovingActor movingActor) {
        if(movingActor instanceof CanSwim) {
            if (movingActor.getSpeed() > 1) {
                movingActor.setSpeed((int) Math.round(movingActor.getSpeed() / 2));
            }
        }else{
            if(movingActor instanceof Attackable) {
                ((Attackable) movingActor).setLife(((Attackable) movingActor).getLife() - waterDamage);
            }
        }
    }
}
