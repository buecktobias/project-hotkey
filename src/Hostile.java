public abstract class Hostile extends NPC {
    public void moveToPlayer(int visualRange){
        Player player = getPlayer(visualRange);
        if(player != null) {
            moveInDirectionOf(player);
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
}
