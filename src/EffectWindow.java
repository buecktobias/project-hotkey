import greenfoot.GreenfootImage;
import greenfoot.World;

public class EffectWindow extends Window {
    private int numberOfEffects = 0;
    private GreenfootImage bg = new GreenfootImage(Files.getSCREENS_PATH() +"Transparent.png");
    private final int BG_WIDTH =  200;
    private final int BG_HEIGHT = 50;
    private final int FULL_TRANSPARENCY = 255;
    @Override
    protected void addedToWorld(World world) {
        bg.scale(BG_WIDTH,BG_HEIGHT);
        bg.setTransparency(FULL_TRANSPARENCY);
        setImage(bg);
    }

    @Override
    public void act() {
        numberOfEffects = 0;
        resetImage();
    }

    public void resetImage(){
        bg.scale(BG_WIDTH,BG_HEIGHT);
        setImage(bg);
    }
    public void addEffect(GreenfootImage effectImg){
        GreenfootImage img = new GreenfootImage(this.getImage());
        img.setTransparency(FULL_TRANSPARENCY);
        effectImg.scale(32,32);
        img.drawImage(effectImg, numberOfEffects * 40,5);
        setImage(img);
        numberOfEffects++;
    }
}
