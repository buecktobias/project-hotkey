import greenfoot.GreenfootImage;

public class Fire extends Environment implements HasEffect {
    private int framesInWhichItDisappears = 999999999;
    private GreenfootImage[] moveAnimationList = new GreenfootImage[4];
    private int damage = 2;
    private int duration = 200;
    public void getImages(){
        GreenfootImage img = new GreenfootImage(Files.getENVIRONMENT_PATH() + "fire1.png");
        img.scale(32,32);
        setImage(img);
        GreenfootImage aniImg;
        for(int i = 1;i<5;i++){
            aniImg = new GreenfootImage(environment+"fire" + i + ".png");
            aniImg.scale(32,32);
            moveAnimationList[i-1]=(aniImg);
        }
    }
    public Fire(){
        getImages();
    }
    public Fire(int framesInWhichItDisappears){
        getImages();
        this.framesInWhichItDisappears = framesInWhichItDisappears;
    }

    @Override
    public void effects(MovingActor movingActor) {
        if(movingActor instanceof FireSensitive){
            GreenfootImage copyMovingActorImage = new GreenfootImage(movingActor.getImage());
             copyMovingActorImage.drawImage(this.getImage(),0,0);
            ((FireSensitive) movingActor).setLife(((FireSensitive) movingActor).getLife()-this.damage);
            ((FireSensitive) movingActor).setFireDamage(this.damage);
        }
    }

    @Override
    public void act() {
        framesInWhichItDisappears--;
        animate(4,moveAnimationList);
        if(framesInWhichItDisappears < 0){
            getWorld().removeObject(this);
        }

    }
}
