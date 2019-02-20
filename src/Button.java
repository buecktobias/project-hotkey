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
            if(Greenfoot.getMouseInfo() != null) {
                int mouseX = Greenfoot.getMouseInfo().getX();
                int mouseY = Greenfoot.getMouseInfo().getY();
                int width = this.getImage().getWidth();
                int height = this.getImage().getHeight();
                if (mouseX > getX() - width / 2 && mouseX < getX() + width / 2 && mouseY < getY() + height / 2 && mouseY > getY() - height / 2) {
                    GreenfootImage img = new GreenfootImage(getImage());
                    img.scale(50, 50);
                    setImage(img);
                } else {
                    setImage(unClicked);
                }
            }
        }

    }
    abstract void clicked();
}
