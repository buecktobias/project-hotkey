import greenfoot.World;


public class Sector0_0 extends World {

    public Sector0_0() {
        super(1024, 736, 1,true);  //Konstruktor der super Klasse aufrufen mit den Parametern worldWidth, worldHeight, cellSize
        setPaintOrder(Player.class);  //Player is painted last
        setBackground("cell_debug.png");
        Player player = new Player();
        addObject(player, getWidth()/2, getHeight()/2);
        addObject(new Enemy(),100,100);
        HUD hud = new HUD();
        addObject(hud,getWidth()/2, getHeight()/2);

    }

}
