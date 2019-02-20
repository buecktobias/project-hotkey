import greenfoot.Actor;
import greenfoot.GreenfootImage;

import java.util.Random;

public class Sector0_0 extends OpenWorld {
    private Enemy enemy = new Enemy();
    public Sector0_0() {
        setBackground("cell_debug.png");
        Player player = new Player();
        addObject(player, getWidth()/2, getHeight()/2);
        addObject(enemy,200,getHeight()/2);
        HUD hud = new HUD();
        addObject(hud,getWidth()/2, getHeight()/2);
        setScrollingBackground(new GreenfootImage("cell_debug.png"));
        Inventory inventory = new Inventory();
        addObject(inventory,getWidth()/2, getHeight()/2);
        try {
            randomObjects(Tree.class,20,100,500,300,50);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }
    public void randomObjects(Class a, int fromX, int fromY, int toX, int toY,int density) throws IllegalAccessException, InstantiationException {
        Random r = new Random();
        if(density < 32){
            density = 32;
        }
        for(;fromX<toX;fromX+=r.nextInt(density-32)+32){
            for(;fromY<toY;fromY+=r.nextInt(density-32)+32){
                addObject((NotMoving)a.newInstance(),fromX,fromY);
            }
        }

    }

    @Override
    public void act() {
        if (enemy.getWorld() == null){
            enemy = new Enemy();
            addObject(enemy,200,getHeight()/2);
        }
    }
}
