import greenfoot.GreenfootImage;

public class Fire extends Environment implements HasEffect {
    private GreenfootImage[] moveAnimationList = new GreenfootImage[4];
    private int damage = 2;
    public Fire(){

        GreenfootImage img = new GreenfootImage("images/Environment/fire.gif");
        img.scale(32,32);
        setImage(img);
        GreenfootImage aniImg;
        for(int i = 1;i<5;i++){
            aniImg = new GreenfootImage(environment+"fire" + i + ".png");
            aniImg.scale(32,32);
            moveAnimationList[i-1]=(aniImg);
        }
    }

    @Override
    public void effects(MovingActor movingActor) {
        if(movingActor instanceof Attackable){
            ((Attackable) movingActor).setLife(((Attackable) movingActor).getLife()-this.damage);
        }
    }

    @Override
    public void act() {
        animate(moveAnimationList);
    }
}
