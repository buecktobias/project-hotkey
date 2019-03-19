import greenfoot.GreenfootImage;

public abstract class Hostile extends NPC {
    abstract int getAttackRange();
    abstract int getDamage();
    private long lastFrameAttacked = 0;
    public void attackAnimation(GreenfootImage attack1){
        setImage(attack1);
    }
    public boolean attack(double attackSpeed,GreenfootImage attack1) {
        FPS fps = getWorld().getObjects(FPS.class).get(0);

        if (fps.getFrame() - lastFrameAttacked > attackSpeed) {
            if (attackPlayer(getAttackRange(), getDamage())) {
                attackAnimation(attack1);
                lastFrameAttacked = fps.getFrame(); // this is important !
                return true;
            }
        }
        return false;
    }
    public boolean attack(double attackSpeed) {
        FPS fps = getWorld().getObjects(FPS.class).get(0);

        if (fps.getFrame() - lastFrameAttacked > attackSpeed) {
            if (attackPlayer(getAttackRange(), getDamage())) {
                lastFrameAttacked = fps.getFrame();
                return true;
            }
        }
        return false;
    }
    public boolean moveToPlayer(int visualRange){
        Player player = getPlayer(visualRange);
        if(player != null) {
            moveInDirectionOf(player);
            return true;
        }else{
            return false;

        }
    }
    public boolean attackPlayer(int attackRange,int damage){
        Player player = getPlayer(attackRange);
        if(player!=null){
            attack(player,damage);
            return true;
        }
        return false;
    }

    @Override
    public void act() {
        super.act();
    }
}
