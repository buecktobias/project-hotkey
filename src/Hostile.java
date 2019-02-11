public abstract class Hostile extends NPC {
    public void attack(Attackable actor,int damage){
        actor.setLife(actor.getLife()-damage);
    }
    public void moveToPlayer(int visualRange){
        Player player = getPlayer(visualRange);
        if(player != null) {
            moveInDirectionOf2(player);
        }
    }
    public void attackPlayer(int attackRange,int damage){
        Player player = getPlayer(attackRange);
        if(player!=null){
            attack(player,damage);
        }
    }
}
