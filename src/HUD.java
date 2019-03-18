import greenfoot.Actor;
import greenfoot.GreenfootImage;

import java.awt.*;

public class HUD extends Actor implements Fixed {

    private Player p;
    private GreenfootImage Background = new GreenfootImage("images/Hud_Menu_Images/Hud_V2.png");

    public HUD(Player p){
        setImage(Background);
        this.p = p;
    }


    public void act(){
        Background.clear();
        Background = new GreenfootImage("images/Hud_Menu_Images/Hud_V2.png");
        setImage(Background);
        drawHealthBar();
        drawEnduranceBar();
    }
    private void drawHealthBar(){
        double health = p.getLife();
        int healthBarWidth = (int) (health / (double) p.getMaxLife() * 280);
        Background.setColor(Color.decode("#7f0000"));
        Background.fillRect(12,12, healthBarWidth,23);
        Background.setColor(Color.decode("#990000"));
        Background.fillRect(12,12, healthBarWidth,16);
        Background.setColor(Color.decode("#cc0000"));
        Background.fillRect(12,12, healthBarWidth,9);
    }
    private void drawEnduranceBar(){
        double endurance = p.getEndurance();
        int enduranceBarWidth = (int) (endurance / (double) p.getMaxEndurance() * 180);
        Background.setColor(Color.decode("#007f00"));
        Background.fillRect(12,42, enduranceBarWidth,23);
        Background.setColor(Color.decode("#009900"));
        Background.fillRect(12,42, enduranceBarWidth,16);
        Background.setColor(Color.decode("#00cc00"));
        Background.fillRect(12,42, enduranceBarWidth,9);
    }

    //Getters and Setters
}
