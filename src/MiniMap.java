import greenfoot.GreenfootImage;

import java.util.List;

public class MiniMap extends Window {
    private int updateFrames = 5;
    private long lastFrameUpdatet = -99999;
    private final int sizeMap = 2000;
    private final int width = 200;
    private final int height = 200;
    private GreenfootImage defaultImage = new GreenfootImage("images/Screens/background_grass.png");
    public MiniMap(){
        defaultImage.scale(width,height);
        setImage(defaultImage);
    }

    @Override
    public void act() {
        if(FPS.getFrame() - lastFrameUpdatet > updateFrames){
            lastFrameUpdatet = FPS.getFrame();
            Player player = Player.getInstance();
            final int playerX = player.getX();
            final int playerY = player.getY();
            List<Entity> entityList = getWorld().getObjects(Entity.class);
            entityList.removeIf(entity -> Math.abs(entity.getX() - playerX) > sizeMap|| Math.abs(entity.getY() - playerY) > sizeMap);
            GreenfootImage copyOfImage = new GreenfootImage(defaultImage);
            for(Entity entity:entityList){
                if(entity instanceof Player){
                    continue;
                }
                int xdifference =  entity.getX() - playerX ;
                int ydifference = entity.getY() - playerY;
                int scaledX = xdifference / (sizeMap/width) + width / 2;
                int scaledY = ydifference / (sizeMap/height) + height / 2;
                GreenfootImage entitysImage = new GreenfootImage(entity.getImage());
                entitysImage.scale(Math.round(entitysImage.getWidth() / (sizeMap/width)) + 1, Math.round(entitysImage.getHeight() / (sizeMap/height)) + 1);
                copyOfImage.drawImage(entitysImage,scaledX,scaledY);
            }
            GreenfootImage playerImage = new GreenfootImage(player.getImage());
            playerImage.scale(Math.round(playerImage.getWidth() / (sizeMap/width)) + 1, Math.round(playerImage.getHeight() / (sizeMap/height)) + 1);
            copyOfImage.drawImage(playerImage,width / 2,height / 2);
            setImage(copyOfImage);
        }


    }
}
