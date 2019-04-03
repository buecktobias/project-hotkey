import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

public abstract class Button extends Actor implements Fixed {
    private GreenfootImage unClicked;
    private GreenfootImage clicked;

    Button(GreenfootImage unClicked){
        this.unClicked = unClicked;
        this.clicked = unClicked;
    }
    Button(GreenfootImage unClicked, GreenfootImage clicked) {
        this.unClicked = unClicked;
        this.clicked = clicked;
        setImage(unClicked);
    }

    @Override
    public void act() {
        if (Greenfoot.mousePressed(this)) {
            setImage(clicked);
        } else if (Greenfoot.mouseClicked(this)) {
            setImage(unClicked);
            clicked();
        } else {
            if (Greenfoot.getMouseInfo() != null) {
                int mouseX = Greenfoot.getMouseInfo().getX();
                int mouseY = Greenfoot.getMouseInfo().getY();
                int width = this.getImage().getWidth();
                int height = this.getImage().getHeight();
                if (mouseX > getX() - width / 2 && mouseX < getX() + width / 2 && mouseY < getY() + height / 2 && mouseY > getY() - height / 2) {
                    hover();
                } else {
                    setImage(unClicked);
                }
            }
        }
    }
    private void hover(){
        GreenfootImage img = new GreenfootImage(unClicked);
        double scale = 1.2;
        img.scale((int) Math.round(img.getWidth() * scale), (int) Math.round(img.getHeight() * scale));
        setImage(img);
    }

    abstract void clicked();
}
