import greenfoot.GreenfootImage;

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
    }

    @Override
    public void act() {
        if (enemy.getWorld() == null){
            enemy = new Enemy();
            addObject(enemy,200,getHeight()/2);
        }
    }
}
