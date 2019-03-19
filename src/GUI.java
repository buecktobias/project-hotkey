import greenfoot.Actor;
import greenfoot.GreenfootImage;

import java.awt.*;

public abstract class GUI extends Actor {
    public void drawItemAt(GreenfootImage g, int X, int Y, Item item){
        if(item != null){
            g.setColor(Color.WHITE);
            g.fillRect(X, Y, 55,55);
            g.setColor(Color.BLUE);
            g.drawRect(X, Y, 55, 55);
            g.drawRect(X + 1,Y +1, 54, 54);
            g.drawImage(item.getItemImage(), X, Y);
        }
    }
}
