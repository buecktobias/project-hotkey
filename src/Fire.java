import greenfoot.GreenfootImage;

public class Fire extends Environment implements HasEffect {
    private int damage = 2;
    public Fire(){

        GreenfootImage img = new GreenfootImage("images/fire.gif");
        img.scale(32,32);
        setImage(img);
    }

    @Override
    public void effects(MovingActor movingActor) {
        System.out.println("fire");
        if(movingActor instanceof Attackable){
            ((Attackable) movingActor).setLife(((Attackable) movingActor).getLife()-this.damage);
        }
    }
}
