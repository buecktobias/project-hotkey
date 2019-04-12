import greenfoot.GreenfootImage;
import greenfoot.World;

import java.util.List;

public class MiniMap extends Window {
    private int updateFrames = 5;
    private long lastFrameUpdatet = -99999;

    private int showedMapWidth = 2000;
    private int showedMapHeight = 2000;

    private int mapWidth = 200;
    private int mapHeight = 200;
    private final int padding = 5;
    private int scaleX;
    private int scaleY;
    private GreenfootImage backgroundImage = new GreenfootImage(Files.getSCREENS_PATH() + "FPS_Window.png");
    private GreenfootImage defaultImage = new GreenfootImage(Files.getSCREENS_PATH() + "background_grass.png");
    private GreenfootImage transparent = new GreenfootImage(Files.getSCREENS_PATH()+"Transparent.png");
    private MiniMap(int mapWidth, int mapHeight,int showedMapWidth,int showedMapHeight){
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.showedMapWidth = showedMapWidth;
        this.showedMapHeight = showedMapHeight;

        scaleImages();
    }
    public MiniMap(){
        scaleImages();
    }
    private void scaleImages(){
        // TODO grass background
        backgroundImage.scale(mapWidth + padding * 2,mapHeight + padding * 2);
        transparent.scale(mapWidth + padding * 2,mapHeight + padding * 2);
        setImage(backgroundImage);
        defaultImage.scale(mapWidth, mapHeight);
        //defaultImage.setColor(Color.decode("#072105"));
        defaultImage.fillRect(0,0,defaultImage.getWidth(),defaultImage.getHeight());
        scaleX = (showedMapWidth / mapWidth);
        scaleY = (showedMapHeight / mapHeight);
    }

    @Override
    protected void addedToWorld(World world) {
        Button b = new Button(transparent,transparent) {
            @Override
            void clicked() {
                if (mapWidth < 800) {
                    world.removeObject(MiniMap.this);
                    ((OpenWorld) world).addObject(new MiniMap(800, 600,4000,4000), 450, 350);
                    world.removeObject(this);
                } else {
                    world.removeObject(MiniMap.this);
                    ((OpenWorld) world).addObjectTopLeftCorner(new MiniMap(), ((Sector0_0) world).getWORLD_WIDTH(), ((Sector0_0) world).getWORLD_HEIGHT());
                    world.removeObject(this);
                }
            }
        };
        world.addObject(b, this.getX(), this.getY());
    }

            @Override
            public void act(){
                if (FPS.getFrame() - lastFrameUpdatet > updateFrames) {
                    lastFrameUpdatet = FPS.getFrame();
                    Player player = Player.getInstance();
                    final int playerX = player.getX();
                    final int playerY = player.getY();
                    List<Entity> entityList = getWorld().getObjects(Entity.class);
                    entityList.removeIf(entity -> Math.abs(entity.getX() - playerX) > showedMapWidth / 2 || Math.abs(entity.getY() - playerY) > showedMapHeight / 2);
                    GreenfootImage copyOfImage = new GreenfootImage(defaultImage);
                    for (Entity entity : entityList) {
                        if (entity instanceof Player) {
                            continue;
                        }
                        int xdifference = entity.getX() - playerX;
                        int ydifference = entity.getY() - playerY;
                        int scaledX = xdifference / scaleX + mapWidth / 2;
                        int scaledY = ydifference / scaleY + mapHeight / 2;
                        GreenfootImage entitysImage = new GreenfootImage(entity.getImage());
                        int newWidth = Math.round(entitysImage.getWidth() / scaleX);
                        int newHeight = Math.round(entitysImage.getHeight() / scaleY);
                        if(newWidth > 0 || newHeight > 0){
                            entitysImage.scale(newWidth,newHeight);
                            copyOfImage.drawImage(entitysImage, scaledX, scaledY);
                        }
                    }
                    GreenfootImage playerImage = new GreenfootImage(player.getImage());
                    playerImage.scale(Math.round(playerImage.getWidth() / scaleX), Math.round(playerImage.getHeight() / scaleY));
                    copyOfImage.drawImage(playerImage, mapWidth / 2, mapHeight / 2);
                    backgroundImage.drawImage(copyOfImage, padding, padding);
                }


            }
        }
