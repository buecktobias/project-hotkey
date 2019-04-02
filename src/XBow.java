import greenfoot.GreenfootImage;

public class XBow extends Environment implements Attackable,Explodes,Blocking {
    private double life = 200;
    private int damage = 2;
    private int speedProjectiles = 30;
    private Bow bow;
    private int visualRange = 500;
    private long lastFrameAttacked = 0;
    private int attackSpeed = 5;
    private final int IMAGE_WIDTH = 64;
    private final int IMAGE_HEIGHT = 64;
    private GreenfootImage defaultImage =new GreenfootImage(Files.getENVIRONMENT_PATH() + "XBow_test.jpg");
    public XBow(){
        defaultImage.scale(IMAGE_WIDTH,IMAGE_HEIGHT);
        setImage(defaultImage);
        bow = new Bow(damage,speedProjectiles);
    }
    @Override
    public double getLife() {
        return life;
    }

    @Override
    public void setLife(double life) {
        if(life < 0){
            getWorld().removeObject(this);
        }
        this.life = life;
    }
    public void attack(Entity g, double attackSpeed) {
        if(FPS.getFrame() - lastFrameAttacked > attackSpeed){
            lastFrameAttacked = FPS.getFrame();
            this.bow.shootFrom(this,g.getX(),g.getY(),new Arrow(this.damage,30,.1,this,0));
        }
    }
    @Override
    public void act() {
        if(getPlayer(visualRange) != null){
            attack((Entity)getPlayer(visualRange),this.attackSpeed);
        }

    }
}
