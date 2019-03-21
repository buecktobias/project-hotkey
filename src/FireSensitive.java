import greenfoot.Actor;
import greenfoot.GreenfootImage;

public interface FireSensitive extends Attackable {
    void setFireDamage(double fireDamage);
    double getFireDamage();
    default void subtractFireDamageFromLife(){
        setLife(getLife() - getFireDamage());
        setFireDamage(getFireDamage() * 0.99);
    }
    default void drawFireImage(){
        if(this instanceof Actor){
            GreenfootImage fireImage = new Fire().getImage();
            ((Actor) this).getImage().drawImage(fireImage,0,0);
        }
    }
}
