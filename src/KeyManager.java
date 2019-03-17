import greenfoot.Actor;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager extends Actor implements KeyListener {
    //TODO decide how we handle with keys..maybe central assignment location
    public boolean[] keys;
    public boolean up, down, left, right;

    public KeyManager(){
        keys = new boolean[256];
    }
    public void act(){
        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
;    }


    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()]= true;
        System.out.println("Pressed!");
    }
    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()]= false;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
}

