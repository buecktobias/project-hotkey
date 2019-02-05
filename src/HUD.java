import greenfoot.GreenfootImage;

import java.awt.*;

public class HUD extends Player {

    GreenfootImage Background = new GreenfootImage("StatBars.png");

    public HUD(){
        setImage(Background);
    }
    //TODO bars need do sync with actual values
    public void act(){
        Background.clear();
        Background = new GreenfootImage("StatBars.png");
        setImage(Background);
        healthBar();
        enduranceBar();
    }

    private void healthBar(){
        int health = super.getLife();
        Background.setColor(Color.RED);
        Background.fillRect(75,21, health*2,44);

    }
    private void enduranceBar(){
        double doubleOfEndurance = super.getEndurance();
        //doubleOfEndurance = doubleOfEndurance*0.18;
        int intOfEndurance = (int) doubleOfEndurance;
        Background.setColor(Color.GREEN);
        Background.fillRect(70,85, intOfEndurance,48);


    }


    //shows Text on screen|not working|may be used for WeaponNameDisplay
    public void fontTest(Graphics itemName){
        itemName.drawString("Schwert", 100,100);
        Font equipedWeapon = new Font("Arial", Font.BOLD, 12);
        itemName.setColor(Color.BLACK);
        itemName.setFont(equipedWeapon);
    }
}
