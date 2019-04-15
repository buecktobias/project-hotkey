import greenfoot.Actor;
import greenfoot.GreenfootImage;
import greenfoot.GreenfootSound;
import org.json.simple.JSONObject;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Sector0_0 extends OpenWorld implements playsSound {
    private Random r = new Random();
    private final GreenfootImage bg = new GreenfootImage(Files.getSCREENS_PATH() + "background_grass.png");
    private final GreenfootSound bgSound = new GreenfootSound("sounds/backgroundMusic.wav");
    private JSONObject jsonObject;
    private String stringGameMode;
    private GameMode gameMode;
    private final int BorderX1 = -1_000;
    private final int BorderY1 = -1_000;
    private final int BorderX2 = 1_000;
    private final int BorderY2 = 1_000;
    private final int WORLD_WIDTH = 1024;
    private final int WORLD_HEIGHT = 736;

    public int getWORLD_WIDTH() {
        return WORLD_WIDTH;
    }

    public int getWORLD_HEIGHT() {
        return WORLD_HEIGHT;
    }

    public Sector0_0() {

        super(1900, 950);
        setScrollingBackground(new GreenfootImage(bg));
        bgSound.setVolume(10);
        bg.scale(128, 128);
        getSettings();
        setPaintOrder(Button.class,SettingsWindow.class,MiniMap.class,ItemInfoScreen.class, Inventory.class, HUD.class,MovingActor.class,Window.class,Teleporter.class);
        setBackground(bg);
        addObjectTopLeftCorner(new MiniMap(),WORLD_WIDTH - 10,WORLD_HEIGHT - 10);
        addObjectTopLeftCorner(FPS.getInstance(), 1000, WORLD_HEIGHT - 250);
        Player player = new Player();
        addObject(player, 0, 0);
        HUD hud = new HUD(player);
        addObject(hud, getWidth() / 2, getHeight() / 2);
        Bow bow = new Bow(200, 20);
        addObject(bow, 300, 50);
        Bomb bomb = new Bomb();
        //addObject(bomb, -420, -220);
        Companion companion = new Companion(player);
        // addObject(companion, 150, 0);

        HealthPotion healthPotion = new HealthPotion(3);
        addObject(healthPotion, 150, 50);
        HealthPotion healthPotion1 = new HealthPotion(5);
        addObject(healthPotion1, 150, 100);

        WoodenArrow woodenArrow = new WoodenArrow();
        woodenArrow.setItemCount(40);

        addObject(woodenArrow,0,70);
        addObject(new WoodenArrow(),0,80);




        Teleporter teleporter2 = new Teleporter();
        Teleporter teleporter = new Teleporter();
        addObject(teleporter,200,430);
        addObject(teleporter2,650,750);
        teleporter2.setTeleporter(teleporter);
        teleporter.setTeleporter(teleporter2);

        Teleporter teleporter3 = new Teleporter();
        Teleporter teleporter4 = new Teleporter();
        addObject(teleporter3,260,130);
        addObject(teleporter4,-800,-550);
        teleporter3.setTeleporter(teleporter4);
        teleporter4.setTeleporter(teleporter3);

/*
        Archer archer = new Archer();
        addObject(archer, 200, 100);
        Archer archer2 = new Archer();
        addObject(archer2, 350, 100);

        XBow xbow = new XBow();
        addObject(xbow, -200, -200);

        Staff staff = new Staff();
        addObject(staff, -350, 210);

        Dagger dagger = new Dagger();
        addObject(dagger, 250, 50);
*/

        randomObjects(Cobweb.class, 200, -600, 800, 400, 10);
        //randomObjects(Grass.class, -500, -300, 400, 800, 2);
        randomObjects(Tree.class, 20, 100, 800, 600, 2);
        // randomObjects(Grass.class, 700, 600, 900, 900, 6);
        //randomObjects(Rock.class, 500, -500, 900, 300, 8);
        randomObjects(Water.class, -600, 200, -100, 800, 1);
        randomObjects(Fire.class, 700, 600, 900, 900, 5);
        randomObjects(Fire.class, -900, -900, -100, -200, 10);
        // randomObjects(Grass.class, BorderX1+250, BorderY1+250, BorderX2-250, BorderY2-250, 12);
        boundingRocks(BorderX1, BorderY1, BorderX2, BorderY2);
        this.resetPlayersPosition(player);
    }

    private void getSettings() {
            stringGameMode = Settings.getInstance().getGameMode();
            for (GameMode value : GameMode.values()) {
                if (value.name.equals(stringGameMode)) {
                    gameMode = value;
                }
            }
    }

    private void boundingRocks(final int smallX, final int smallY, final int maxX, final int maxY) {
        int rockSize = 128;
        for (int x = smallX; x < maxX; x += rockSize) {
            addObject(new Rock(rockSize,rockSize), x, smallY);
        }
        for (int x = smallX; x < maxX; x += rockSize) {
            addObject(new Rock(rockSize,rockSize), x, maxY);
        }
        for (int y = smallY; y < maxY; y += rockSize) {
            addObject(new Rock(rockSize,rockSize), smallX, y);
        }
        for (int y = smallY; y < maxY; y += rockSize) {
            addObject(new Rock(rockSize,rockSize), maxX, y);
        }
    }

    public void randomSpawn(Class c) {
        int borderx2 = BorderX2 + super.getTotalXMovement();
        int borderx1  = BorderX1 + super.getTotalXMovement();
        int bordery1 = BorderY1 + super.getTotalYMovement();
        int bordery2 = BorderY2 + super.getTotalYMovement();
        int x = r.nextInt(Math.abs(borderx2 - borderx1)) + borderx1;
        int y = r.nextInt(Math.abs(bordery2 - bordery1)) + bordery1;
        try {
            Entity actor = (Entity) c.newInstance();
            addObject(actor, x, y);
            List<Entity> entityList = actor.getIntersectingObjects(Entity.class);
            entityList.removeIf(general -> !(general instanceof Blocking));
            if (entityList.size() > 0) {
                removeObject(actor);
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void randomObjects(Class a, final int fromX, final int fromY, final int toX, final int toY, double density) {
        int width;
        int height;
        if (density < 1) {
            density = 1;
        }
        try {
            Actor justToGetTheImage = (Actor) a.newInstance();
            width = justToGetTheImage.getImage().getWidth();
            height = justToGetTheImage.getImage().getHeight();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return;
        }
        Actor actor;
        int widthRandomBound = (int) Math.round(width * density - width);
        int heightRandomBound = (int) Math.round(height * density - height);
        List<Integer> list = new LinkedList<Integer>();
        for (int x = fromX; x < toX; ) {
            if (list.size() > 0) {
                x += max(list);
            }
            list = new LinkedList<Integer>();
            for (int y = fromY; y < toY; ) {
                int RandomX;
                int RandomY;
                if (widthRandomBound < 1) {
                    RandomX = width;
                } else {
                    RandomX = r.nextInt(widthRandomBound) + width;
                }
                if (heightRandomBound < 1) {
                    RandomY = height;
                } else {
                    RandomY = r.nextInt(heightRandomBound) + height;
                }
                list.add(RandomX);
                try {
                    actor = (Actor) a.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                    return;
                }
                y += RandomY;
                addObject(actor, x + RandomX, y);
            }
        }

    }

    public int max(List<Integer> list) {
        int max = Integer.MIN_VALUE;
        for (int i : list) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }

    @Override
    public void act() {
        getSettings();
        play(bgSound);
        stopIfSoundIsOff(bgSound);
        long currentFrame = FPS.getFrame();
        if (gameMode != null) {
            if (currentFrame != 0) {
                if (currentFrame % 100 == 0) {
                    randomSpawn(Pig.class);
                }
                if (currentFrame % gameMode.eachFrameEnemySpawns == 0) {
                    randomSpawn(Bomb.class);
                }
                if (currentFrame % gameMode.eachFrameEnemySpawns == 0) {
                    randomSpawn(WalkingBomb.class);
                }
                if (currentFrame % gameMode.eachFrameEnemySpawns == 0) {
                    randomSpawn(Spider.class);
                }
                if (currentFrame % gameMode.eachFrameEnemySpawns == 0) {
                    randomSpawn(FireEnemy.class);
                }
                if (currentFrame % gameMode.eachFrameEnemySpawns == 0) {
                    randomSpawn(Archer.class);
                }
            }

        }
    }
}
