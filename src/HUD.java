import greenfoot.Actor;
import greenfoot.GreenfootImage;

import java.awt.*;

public class HUD extends Actor implements Fixed {

    GreenfootImage Background = new GreenfootImage("images/Hud_Menu_Images/StatBars.png");

    public HUD(){
        setImage(Background);
    }

    public void act(){
        Background.clear();
        Background = new GreenfootImage("images/Hud_Menu_Images/StatBars.png");
        setImage(Background);
        if (getWorld().getObjects(Player.class).get(0)!= null) {
            Player p = getWorld().getObjects(Player.class).get(0);
            drawStatBars(p);
        }else{
            System.out.println("No Player found");
        }
    }

    private void drawStatBars(Player p){
        double health = p.getLife();
        Background.setColor(Color.RED);
        int healthBarWidth = (int) ((double) p.getLife()/ (double) p.getMaxLife() * 280);
        Background.fillRect(50,20, healthBarWidth,27);
        double endurance = p.getEndurance() /p.getMaxEndurance() * 180;
        Background.setColor(Color.GREEN);
        Background.fillRect(60,60, (int)endurance,27);
    }

    //Getters and Setters
}
