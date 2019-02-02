import greenfoot.*;


public class Sector0_0 extends World {

    public Sector0_0() {
        super(1024, 736, 1);  //Konstruktor der super Klasse aufrufen mit den Parametern worldWidth, worldHeight, cellSize
        setBackground("background_debug.png"); //Felder auf den Hintergrund malen
        setPaintOrder(Player.class);  //Player is painted last

        Player player = new Player();
        addObject(player, 0, 0);


    }

}
