import greenfoot.Color;
import greenfoot.GreenfootImage;

public class HUD extends GUI implements Fixed {
    //TODO align ammunition image
    private Player p;
    private double scale;
    private GreenfootImage Background = new GreenfootImage(Files.getHUD_MENU_IMAGES_PATH() + "Hud_V4.png");

    public HUD(Player p){
        setImage(Background);
        this.p = p;
    }

    public void act(){
        Background.clear();
        expScaling(p.getExp(),12.3);
        Background = new GreenfootImage(Files.getHUD_MENU_IMAGES_PATH() + "Hud_V4.png");
        setImage(Background);
        drawBarAt(Background, p.getLife(), p.getMaxLife(), 308, new Color(127,0,0), new Color(153,0,0), new Color(204,0,0), 12, 12);
        drawBarAt(Background,p.getEndurance(), p.getMaxEndurance(), 246, new Color(0,127,0), new Color(0,153,0), new Color(0,204,0),12,42 );
        drawBarAt(Background,(double)(p.getExp()), p.getLevelUpValue(p.getLevel()), ((int)(scale)), new Color(127,0,127), new Color(153,0,153), new Color(204,0,204), 704,12);
        drawEquippedWeapons();
        drawLevelFonts();
        drawActiveBeltItem();
        drawActiveAmmunition();
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
            drawItemCount(Background, ((Countable) p.getActiveConsumable()), 153, 670);
        }
    }
    private void drawActiveAmmunition(){
        if(p.getActiveAmmunition() != null){
            drawItemAt(Background, 113, 548, p.getActiveAmmunition());
            Background.setFont(GUILargeFont);
            drawItemCount(Background, ((Countable) p.getActiveAmmunition()), 153, 593);
        }
    }
    private void drawLevelFonts(){
        Background.setFont(GUILargeFont);
        //Background.setColor(Color.BLACK);
        Background.drawString("LVL:" + (p.getLevel()) + " "+ (p.getExp())  + "/" + p.getLevelUpValue(p.getLevel()), 803, 30);
    }

    //Getters and Setters
}
