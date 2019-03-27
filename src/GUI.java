import greenfoot.Actor;
import greenfoot.GreenfootImage;

import java.awt.*;

public abstract class GUI extends Actor {

    public  final Font GUILargeFont = new Font(Font.MONOSPACED, Font.BOLD, 19);

    public void drawItemAt(GreenfootImage g, int X, int Y, Item item){
        if(item != null){
            g.setColor(Color.WHITE);
            g.fillRect(X, Y, 55,55);
            g.setColor(Color.BLUE);
            g.drawRect(X, Y, 55, 55);
            g.drawRect(X + 1,Y +1, 54, 54);
            g.drawImage(item.getItemImage(), X + 10, Y + 10);
        }
    }
    public void drawBarAt(GreenfootImage g, double width, int maxValue, int scale, String lightC, String medC, String darkC, int X, int Y){
        int scaleWidth = (int) (width / (double) maxValue * scale);
        g.setColor(Color.decode(lightC));
        g.fillRect(X,Y, scaleWidth,23);
        g.setColor(Color.decode(medC));
        g.fillRect(X,Y, scaleWidth,16);
        g.setColor(Color.decode(darkC));
        g.fillRect(X,Y, scaleWidth,9);
    }
}
