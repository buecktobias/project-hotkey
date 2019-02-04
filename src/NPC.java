import java.util.Random;

public abstract class NPC extends MovingActor {
    private int speed = 1;
    private static final Random r = new Random();
    public void randomMove(){
        switch(r.nextInt(4)){
            case 0:
                moveDown(speed);
                break;
            case 1:
                moveLeft(speed);
                break;
            case 2:
                moveRight(speed);
                break;
            case 3:
                moveUp(speed);
                break;
        }
    }
}
