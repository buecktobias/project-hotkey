import greenfoot.GreenfootImage;

public class Sector0_0 extends OpenWorld {

    public Sector0_0() {
        setBackground("cell_debug.png");
        Player player = new Player();
        addObject(player, getWidth()/2, getHeight()/2);
        addObject(new Enemy(),200,getHeight()/2);
        HUD hud = new HUD();
        addObject(hud,getWidth()/2, getHeight()/2);
        setScrollingBackground(new GreenfootImage("cell_debug.png"));

        Staff staff = new Staff(42);
        addObject(staff,getWidth()/2, getHeight()/2);



    }

}
