import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class SkillWindow extends Actor implements Fixed {
    public SkillWindow(){
        GreenfootImage bg = new GreenfootImage("images/SkillScreen.png");
        bg.setTransparency(100);
        setImage(bg);
    }
}
