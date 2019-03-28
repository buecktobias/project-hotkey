import greenfoot.GreenfootImage;
import greenfoot.World;

public class EffectWindow extends Window {
    private GreenfootImage bg = new GreenfootImage(Files.getSCREENS_PATH() +"Transparent.png");

    @Override
    protected void addedToWorld(World world) {
        bg.scale(100,50);
        bg.setTransparency(0);
        setImage(bg);
    }

    @Override
    public void act() {
        resetImage();
    }

    public void resetImage(){
        bg.scale(100,50);
        setImage(bg);
    }
    public void addEffect(GreenfootImage effectImg){
        GreenfootImage img = new GreenfootImage(bg);
        img.setTransparency(255);
        effectImg.scale(32,32);
        img.drawImage(effectImg,20,5);
        setImage(img);
    }
}
