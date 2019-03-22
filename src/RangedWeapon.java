import greenfoot.Actor;

public abstract class RangedWeapon extends Weapon {
    abstract void shootFrom(Actor a, int toX, int toY,Projectile projectile);
}
