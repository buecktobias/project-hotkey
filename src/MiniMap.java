import greenfoot.GreenfootImage;

import java.util.List;

public class MiniMap extends Window {
    private int updateFrames = 20;
    private long lastFrameUpdatet = -99999;
    private GreenfootImage defaultImage = new GreenfootImage("images/Screens/background_grass.png");
    public MiniMap(){
        defaultImage.scale(200,200);
        setImage(defaultImage);
    }

    @Override
    public void act() {
        if(FPS.getFrame() - lastFrameUpdatet > updateFrames){
            lastFrameUpdatet = FPS.getFrame();
            System.out.println("lol");
            List<Entity> entityList = getWorld().getObjects(Entity.class);
            entityList.removeIf(entity -> entity.getX() > 1000 || entity.getX() < -1000 || entity.getY() > 1000 || entity.getY() < -1000);
            GreenfootImage copyOfImage = new GreenfootImage(defaultImage);
            for(Entity entity:entityList){
                int x = entity.getX();
                int y = entity.getY();
                int scaledX = x / 10 + 100;
                int scaledY = y / 10 + 100;
                GreenfootImage entitysImage = new GreenfootImage(entity.getImage());
                entitysImage.scale(Math.round(entitysImage.getWidth() / 10) + 1, Math.round(entitysImage.getHeight() / 10) + 1);
                copyOfImage.drawImage(entitysImage,scaledX,scaledY);
            }
            setImage(copyOfImage);
        }


    }
}
