import greenfoot.Actor;
import greenfoot.GreenfootImage;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Sector0_0 extends OpenWorld {
    private int time = 0;
    private Enemy enemy = new Enemy();
    private Random r = new Random();
    private final int BorderX1 = -1_000;
    private final int BorderY1 = -1_000;
    private final int BorderX2 = 1_000;
    private final int BorderY2 = 1_000;

    public Sector0_0() {
        setPaintOrder(Button.class, Inventory.class, HUD.class,FPS.class, MovingActor.class);
        setBackground("cell_debug.png");
        Player player = new Player();
        addObject(player, 0, 0);
        //addObject(enemy, 200, 200);
        HUD hud = new HUD();
        addObject(hud, getWidth() / 2, getHeight() / 2);
        setScrollingBackground(new GreenfootImage("cell_debug.png"));
        Staff staff = new Staff(42);
        addObject(staff, 100, 100);
        addObject(new FPS(),1000,32);

        randomObjects(Tree.class, 20, 100, 800, 600, 2);
        randomObjects(Grass.class, 700, 600, 1000, 900, 6);
        randomObjects(Rock.class, 500, -500, 900, 300, 8);
        randomObjects(Water.class, -600, 200, -100, 1_000, 1);
        randomObjects(Fire.class, 700, 600, 1000, 900, 5);
        randomObjects(Fire.class, -1000, -1000, -100, -200, 10);
        randomObjects(Grass.class, BorderX1, BorderY1, BorderX2, BorderY2, 12);

        //addObject(new Spider(), -400, -500);
        addObject(new Pig(), -200, 200);
        boundingRocks(BorderX1, BorderY1, BorderX2, BorderY2);

    }

    private void boundingRocks(final int smallX, final int smallY, final int maxX, final int maxY) {
        for (int x = smallX; x < maxX; x += 32) {
            addObject(new Rock(), x, smallY);
        }
        for (int x = smallX; x < maxX; x += 32) {
            addObject(new Rock(), x, maxY);
        }
        for (int y = smallY; y < maxY; y += 32) {
            addObject(new Rock(), smallX, y);
        }
        for (int y = smallY; y < maxY; y += 32) {
            addObject(new Rock(), maxX, y);
        }
    }


    public void randomSpawn(Class c) {
        int x = r.nextInt(Math.abs(BorderX2 - BorderX1)) + BorderX1;
        int y = r.nextInt(Math.abs(BorderY2 - BorderY1)) + BorderY1;
        if (x > BorderX2 || x < BorderX1 || y > BorderY2 || y < BorderY1) {
            return;
        }
        try {
            General actor = (General) c.newInstance();
            addObject(actor, x, y);
            List<General> generalList = actor.getIntersectingObjects(General.class);
            generalList.removeIf(general -> !(general instanceof Blocking));
            if (generalList.size() > 0) {
                removeObject(actor);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
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
        } catch (InstantiationException e) {
            e.printStackTrace();
            return;
        } catch (IllegalAccessException e) {
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
                } catch (InstantiationException e) {
                    e.printStackTrace();
                    return;
                } catch (IllegalAccessException e) {
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
        if (time % 100 == 0) {
            randomSpawn(Pig.class);

        }
        if (time % 200 == 0) {
            // TODO spawn spiders at locations where they are not annoying while testing
            //randomSpawn(Spider.class);
        }
        time++;
    }
}
