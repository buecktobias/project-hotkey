import greenfoot.Actor;
import greenfoot.Greenfoot;

public abstract class Button extends Actor implements Fixed {
    @Override
    public void act() {
        if(Greenfoot.mouseClicked(this)){
            clicked();
        }

    }
    abstract void clicked();
    public void changeImage(String fileName){
        this.setImage(fileName);
    }
}
