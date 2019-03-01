public abstract class Hostile extends NPC {
    abstract int getAttackRange();
    abstract int getDamage();
    private int lastFrameAttacked = 0;
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

    }
}
