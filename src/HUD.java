import greenfoot.GreenfootImage;

import java.awt.*;

public class HUD extends NotMoving {

    GreenfootImage Background = new GreenfootImage("StatBars.png");


    public HUD (){
        setImage(Background);
    }
    //TODO bars need do sync with actual values
    public void act(){
        Background.clear();
        Background = new GreenfootImage("StatBars.png");
        setImage(Background);
        if (getWorld().getObjects(Player.class).get(0)!= null) {
           Player p = getWorld().getObjects(Player.class).get(0);
           healthBar(p);
           enduranceBar( p);
        }else{
            System.out.println("No Player found");
        }
    }

    private void healthBar(Player p){
        int health = p.getLife();
        Background.setColor(Color.RED);
        Background.fillRect(50,20, health,27);

    }
    private void enduranceBar(Player p){
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
