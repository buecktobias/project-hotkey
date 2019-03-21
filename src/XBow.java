import greenfoot.GreenfootImage;

public class XBow extends Environment implements Attackable,Explodes,Blocking {
    private double life = 200;
    private int damage = 2;
    private int speedProjectiles = 30;
    private Bow bow = new Bow(damage,speedProjectiles);
    private int visualRange = 500;
    private long lastFrameAttacked = 0;
    private int attackSpeed = 5;
    private GreenfootImage defaultImage =new GreenfootImage("images/Environment/XBow_test.jpg");
    public XBow(){
        defaultImage.scale(64,64);
        setImage(defaultImage);
    }
    @Override
    public double getLife() {
        return life;
    }

    @Override
    public void setLife(double life) {
        this.life = life;
    }
    public void attack(General g,double attackSpeed) {
        if(FPS.getFrame() - lastFrameAttacked > attackSpeed){
            lastFrameAttacked = FPS.getFrame();
            this.bow.shootFrom(this,g.getX(),g.getY());
        }
    }
    @Override
    public void act() {
        if(getPlayer(visualRange) != null){
            attack((General)getPlayer(visualRange),this.attackSpeed);
        }
        if(life < 0){
            getWorld().removeObject(this);
        }
    }
}
