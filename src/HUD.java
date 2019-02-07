import greenfoot.GreenfootImage;

import java.awt.*;

public class HUD extends NotMoving {

    GreenfootImage Background = new GreenfootImage("StatBars.png");

    @Override
    public boolean isBlocking() {
        return false;
    }

    public HUD(){
        setImage(Background);
    }
    //TODO bars need do sync with actual values
    public void act(){
        Background.clear();
        Background = new GreenfootImage("StatBars.png");
        setImage(Background);
        if (getWorld().getObjects(Player.class).get(0)!= null) {
           Player p = getWorld().getObjects(Player.class).get(0);
           drawStatBars(p);
        }else{
            System.out.println("No Player found");
        }
    }

    private void drawStatBars(Player p){
        int health = p.getLife();
        Background.setColor(Color.RED);
        int healthBarWidth = (int) ((double) p.getLife()/ (double) p.getMaxLife() * 280);
        Background.fillRect(50,20, healthBarWidth,27);

        double endurance = p.getEndurance();
        endurance = endurance*0.18;
        Background.setColor(Color.GREEN);
        Background.fillRect(60,60, (int)endurance,27);
    }

    //shows Text on screen|not working|may be used for WeaponNameDisplay
    public void fontTest(Graphics itemName, String name){
        itemName.drawString(name, 100,100);
        Font equipedWeapon = new Font("Arial", Font.BOLD, 12);
        itemName.setColor(Color.BLACK);
        itemName.setFont(equipedWeapon);
    }
}
