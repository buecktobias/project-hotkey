import greenfoot.GreenfootImage;

import java.awt.*;

public class HUD extends Player {

    GreenfootImage Background = new GreenfootImage("images/EmptyHealthbar.png");

    public HUD(){
        setImage(Background);
    }
    public void act(){
        Background.clear();
        Background = new GreenfootImage("images/EmptyHealthbar.png");
        setImage(Background);
        healthBar();
    }

    public void healthBar(){

        int health = super.getLife();
        Background.setColor(Color.RED);
        Background.fillRect(50,-10, health,30);


    }


    //shows Text on screen|not working|maybe used for WeaponDisplay
    public void fontTest(Graphics itemName){
        itemName.drawString("Schwert", 100,100);
        Font equipedWeapon = new Font("Arial", Font.BOLD, 12);
        itemName.setColor(Color.BLACK);
        itemName.setFont(equipedWeapon);
    }
}
