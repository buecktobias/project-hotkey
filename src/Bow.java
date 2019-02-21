import greenfoot.Greenfoot;

public class Bow extends Weapon {

    private int attackSpeed;
    private Player player;

    public Bow(int attackSpeed, Player player) {
        this.attackSpeed = attackSpeed;
        this.player = player;
    }

    public void shoot() {
        Arrow arrow = new Arrow(42, 1,10120,player);
        getWorld().addObject(arrow,getX(),getY());
    }


    public void act() {
        if(Greenfoot.isKeyDown("V")) {
            shoot();
        }
    }
}
