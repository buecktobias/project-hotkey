public abstract class Hostile extends NPC {
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
