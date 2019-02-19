import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

public abstract class Button extends Actor implements Fixed {
    private GreenfootImage unClicked;
    private GreenfootImage Clicked;
    Button(GreenfootImage unClicked, GreenfootImage Clicked){
        this.unClicked = unClicked;
        this.Clicked = Clicked;
        setImage(unClicked);
    }
    @Override
    public void act() {
        if(Greenfoot.mousePressed(this)){
            setImage(Clicked);
        }
        else if(Greenfoot.mouseClicked(this)){
            setImage(unClicked);
            clicked();
        }else {
            if (Greenfoot.getMouseInfo().getActor() == this) {
                GreenfootImage img = new GreenfootImage(getImage());
                img.scale(50, 50);
                setImage(img);
                System.out.println("mouse");
            } else if(Greenfoot.getMouseInfo() != null) {
                setImage(unClicked);
                System.out.println("not mouse");
            }
        }

    }
    abstract void clicked();
}
