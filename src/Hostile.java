public abstract class Hostile extends NPC {
    public void attack(Attackable actor,int damage){
        actor.setLife(actor.getLife()-damage);
    }
}
