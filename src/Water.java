import greenfoot.GreenfootImage;

public class Water extends Environment implements HasEffect {
    public Water(){
        GreenfootImage img = new GreenfootImage("images/Environment/water.jpg");
        img.scale(32,32);
        setImage(img);
    }

    @Override
    public void effects(MovingActor movingActor) {
        if(movingActor.getSpeed()>1) {
            movingActor.setSpeed((int) Math.round(movingActor.getSpeed() / 2));
        }
    }
}
