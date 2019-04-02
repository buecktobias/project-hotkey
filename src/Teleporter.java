import greenfoot.GreenfootImage;

public class Teleporter extends Environment implements HasEffect {
    private Teleporter teleporter;
    private int transparency = 255;
    private boolean appears = false;
    private int makeAppearsFalseIn = -99;
    private GreenfootImage defaultImage = new GreenfootImage(Files.getENVIRONMENT_PATH() + "Teleporter.png");
    private GreenfootImage activatedImage = new GreenfootImage(Files.getENVIRONMENT_PATH() +"Teleporter_activated.png");
    public Teleporter() {
        setImage(defaultImage);
    }
    public void setTeleporter(Teleporter teleporter){
        this.teleporter = teleporter;
    }
    public void makeDisappear(MovingActor movingActor){
        if(this.transparency < 4){
            teleportMovingActor(movingActor);
        }else {
            movingActor.getImage().setTransparency(this.transparency);
            this.transparency -= 2;
        }

    }

    @Override
    public void act() {
        setImage(defaultImage);
        makeAppearsFalseIn--;
        if(makeAppearsFalseIn == 0){
            appears = false;
        }
    }

    public void makeAppear(MovingActor movingActor){
        if(this.transparency > 250){
            this.makeAppearsFalseIn = 10;
        }else {
            movingActor.getImage().setTransparency(this.transparency);
            this.transparency += 2;
        }

    }
    public void teleportMovingActor(MovingActor movingActor){
            movingActor.setLocation(this.teleporter.getX(), this.teleporter.getY());
            this.teleporter.transparency = 0;
            this.appears = false;
            this.transparency = 255;
            this.teleporter.appears = true;
            if(movingActor instanceof Player) {
                ((OpenWorld) this.getWorld()).resetPlayersPosition((Player)movingActor);
            }
    }


    @Override
    public void effects(MovingActor movingActor) {
        if(this.teleporter != null) {
            if (!(movingActor instanceof Player)) {
                getWorld().removeObject(movingActor);
                return;
            }
            setImage(activatedImage);
            if (appears) {
                makeAppear(movingActor);

            } else {
                makeDisappear(movingActor);
            }
            if (this.transparency < 200) {
                movingActor.setSpeed(0);
            }
        }
    }
}
