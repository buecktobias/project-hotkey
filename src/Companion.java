public class Companion extends Familiar {
    private Player player;
    private boolean alreadyHealed;
    private int visualRange;
    private int test;
    public Companion(Player p) {
        this.player = p;
        this.alreadyHealed = false;
        this.visualRange = 1000000000;
        setImage("images/ItemImages/Shoe.png");
        this.test = 0;
    }
    public void act(){
        //escortPlayer();
    }
    /*public void escortPlayer(){

        if(moveToPlayer(this.visualRange)){

        }else{
            randomMove(500);
        }

        if (player.getLife()<50) {
            if(alreadyHealed==false) {
                player.setLife(200);
                alreadyHealed = true;
            }
        }
    }
    */

}
