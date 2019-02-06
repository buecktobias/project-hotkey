import greenfoot.GreenfootImage;


public class Sector0_0 extends OpenWorld {

    public Sector0_0() {
        super(1024, 736, 1,1_000,1_000);  //Konstruktor der super Klasse aufrufen mit den Parametern worldWidth, worldHeight, cellSize
        setPaintOrder(Player.class);  //Player is painted last
        setBackground("cell_debug.png");
        Player player = new Player();
        addObject(player, getWidth()/2, getHeight()/2);
        addObject(new Enemy(),100,100);
        setScrollingBackground(new GreenfootImage("cell_debug.png"));

        HUD hud = new HUD();
        addObject(hud,getWidth()/2, getHeight()/2);

    }

}
