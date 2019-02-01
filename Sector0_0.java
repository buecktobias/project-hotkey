import greenfoot.*;


/**
 * First level of our game
 *
 * @author SAE
 */
public class Sector0_0 extends World {

    /**
     * Constructor for objects of class Level1.
     */
    public Sector0_0() {
        super(1280, 720, 1);  //Konstruktor der super Klasse aufrufen mit den Parametern worldWidth, worldHeight, cellSize
        setBackground("background.png"); //Felder auf den Hintergrund malen
        setPaintOrder(Player.class);  //Player is painted last

        Player player = new Player();
        addObject(player, 0, 0);


    }

}
