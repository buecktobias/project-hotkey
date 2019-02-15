package projektHotkey;

public class Bow extends Weapon {

    private int attackSpeed;

    public Bow(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public void shoot() {
        getWorld().addObject(new Arrow(1,1,1),getX(),getY());
    }
}
