public class Familiar extends Friendly {
    private int lifePoints = 100;

    @Override
    void movingAnimation() { }

    @Override
    int getSpeed() {
        return 0;
    }

    @Override
    void setSpeed(int n) {

    }

    public void act() {
        setImage("Rabbit.png");
        setRotation(getPlayer().getRotation());
        move(1);
        if (isTouching(Hostile.class)) {
            lifePoints = lifePoints - 10;
        }
        if (lifePoints < 0) {
            removeTouching(Friendly.class);
        }
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

}
