import greenfoot.Actor;
import greenfoot.GreenfootImage;
import greenfoot.World;

import java.awt.*;

public class FPS extends Actor implements Fixed {
    private int frame;
    private int fps;
    private long startTime;
    private GreenfootImage bg ;
    private static FPS ourInstance = new FPS();

    public static FPS getInstance() {
        return ourInstance;
    }
    @Override
    protected void addedToWorld(World world) {
        frame = 0;
        fps = 0;
        startTime = System.currentTimeMillis();
        bg = new GreenfootImage("images/Screens/FPS_Window.png");
        bg.scale(128,64);
    }

    @Override
    public void act() {
        frame++;
        setImage(bg);
        GreenfootImage img = new GreenfootImage(bg);
        img.scale(128, 64);
        int update = 10;
        if(frame % update == 0) {
            long elapsedTime = System.currentTimeMillis() - startTime;
           // System.out.println(elapsedTime);
            startTime = System.currentTimeMillis();
            double secondselapsed = elapsedTime / 1000f;
            //System.out.println(secondselapsed);
            fps = (int) Math.round(update / secondselapsed);
        }
        img.setColor(Color.BLACK);
        img.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 30));
        img.drawString(String.valueOf(fps), 30, 40);
        setImage(img);
    }
    public int getFrame() {
        return frame;
    }
}
