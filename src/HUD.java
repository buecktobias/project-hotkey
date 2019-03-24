import greenfoot.GreenfootImage;

import java.awt.*;

public class HUD extends GUI implements Fixed {

    private Player p;
    private double scale;
    private GreenfootImage Background = new GreenfootImage("images/Hud_Menu_Images/Hud_V4.png");

    public HUD(Player p){
        setImage(Background);
        this.p = p;
    }

    public void act(){
        Background.clear();
        expScaling(p.getExp(),12.3);
        Background = new GreenfootImage("images/Hud_Menu_Images/Hud_V4.png");
        setImage(Background);
        drawBarAt(Background, p.getLife(), p.getMaxLife(), 308, "#7f0000", "#990000", "#cc0000", 12, 12);
        drawBarAt(Background,p.getEndurance(), p.getMaxEndurance(), 246, "#007f00", "#009900", "#00cc00",12,42 );
        drawBarAt(Background,(double)(p.getExp()), p.getLevelUpValue(p.getLevel()), ((int)(scale)), "#7f007f", "#990099", "#cc00cc", 704,12);
        drawEquippedWeapons();
        drawLevelFonts();
        drawActiveBeltItem();
    }
    // TODO fix scaling issues
    private double expScaling(double exp, double startScale){
        scale = p.getExp() * 12.3f;
        return scale;
    }


    private void drawEquippedWeapons(){
        if(p.getPrimaryWeapon() != null){
            super.drawItemAt(Background, 184, 585, p.getPrimaryWeapon());
        }
        if(p.getSecondaryWeapon() != null){
            super.drawItemAt(Background, 42, 585, p.getSecondaryWeapon());
        }
    }
    private void drawActiveBeltItem(){
        if(p.getActiveConsumable() != null){
            drawItemAt(Background, 113, 625, p.getActiveConsumable());
            Background.setFont(GUILargeFont);
            Background.setColor(Color.BLACK);
            if(p.getActiveConsumable() != null){
                Background.drawString(String.valueOf(((Countable) p.getActiveConsumable()).getItemCount()), 155, 660);
            }
        }

    }
    private void drawLevelFonts(){
        Background.setFont(GUILargeFont);
        Background.setColor(Color.BLACK);
        Background.drawString("LVL:" + (p.getLevel()) + " "+ (p.getExp())  + "/" + p.getLevelUpValue(p.getLevel()), 780, 30);
    }

    //Getters and Setters

    public GreenfootImage getBackground() {
        return Background;
    }
}
