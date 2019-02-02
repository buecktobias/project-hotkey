import greenfoot.*;


public class Sector0_0 extends OpenWorld {

    public Sector0_0() {
        super(1024, 736, 1,2_000,2_000);  //Konstruktor der super Klasse aufrufen mit den Parametern worldWidth, worldHeight, cellSize
        setPaintOrder(Player.class);  //Player is painted last
        setBackground("cell_debug.png");
        Player player = new Player();
        addObject(player, getWidth()/2, getHeight()/2);
        setScrollingBackground(new GreenfootImage("cell_debug.png"));


    }

}
