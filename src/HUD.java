import greenfoot.GreenfootImage;

import java.awt.*;

public class HUD extends GUI implements Fixed {

    private Player p;
    private Weapon secondaryWeapon;
    private GreenfootImage Background = new GreenfootImage("images/Hud_Menu_Images/Hud_V3.png");

    public HUD(Player p){
        setImage(Background);
        this.p = p;
    }

    public void act(){
        Background.clear();
        Background = new GreenfootImage("images/Hud_Menu_Images/Hud_V3.png");
        setImage(Background);
        drawBarAt(p.getLife(), p.getMaxLife(), 308, "#7f0000", "#990000", "#cc0000", 12, 12);
        drawBarAt(p.getEndurance(), p.getMaxEndurance(), 246, "#007f00", "#009900", "#00cc00",12,42 );
        drawBarAt((double)(p.getExp()), p.getLevelUpValue(p.getLevel()), 200, "#7f007f", "#990099", "#cc00cc", 704,12);
        drawEquippedWeapons();
    }
    private void drawBarAt(double width, int maxValue, int scale, String lightC, String medC, String darkC, int X, int Y){
        int scaleWidth = (int) (width / (double) maxValue * scale);
        Background.setColor(Color.decode(lightC));
        Background.fillRect(X,Y, scaleWidth,23);
        Background.setColor(Color.decode(medC));
        Background.fillRect(X,Y, scaleWidth,16);
        Background.setColor(Color.decode(darkC));
        Background.fillRect(X,Y, scaleWidth,9);
    }

    private void drawEquippedWeapons(){
        if(p.getPrimaryWeapon() != null){
            super.drawItemAt(Background, 184, 585, p.getPrimaryWeapon());
        }
        if(p.getSecondaryWeapon() != null){
            super.drawItemAt(Background, 44, 585, p.getSecondaryWeapon());
        }
    }

    //Getters and Setters
}
