package projektHotkey;

import projektHotkey.Interfaces.Attackable;
import projektHotkey.Interfaces.Blocking;

public class Enemy extends Hostile implements Attackable, Blocking {
    private int visualRange = 500;
    private int attackRange = 150;
    private int damage = 5;
    private int speed = 1;
    private int life = 100;
    private int hitboxRadius=getWidth();
    @Override
    public int getLife() {
        return life;
    }

    @Override
    public void setLife(int life) {
        this.life = life;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void act() {
        moveToPlayer(this.visualRange);
        attackPlayer(this.attackRange, this.damage);
    }

}
