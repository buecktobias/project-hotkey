import greenfoot.Actor;
import greenfoot.GreenfootImage;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Sector0_0 extends OpenWorld {
    private Enemy enemy = new Enemy();
    private Random r = new Random();

    public Sector0_0() {
        setBackground("cell_debug.png");
        Player player = new Player();
        addObject(player, getWidth() / 2, getHeight() / 2);
        addObject(enemy, 200, getHeight() / 2);
        HUD hud = new HUD();
        addObject(hud, getWidth() / 2, getHeight() / 2);
        setScrollingBackground(new GreenfootImage("cell_debug.png"));
        randomObjects(Tree.class, 20, 100, 800, 600, 2);
        randomObjects(Grass.class, 700, 600, 1000, 900, 6);
        randomObjects(Rock.class, 100, -500, 900, 300, 8);
        randomObjects(Water.class, -500, 200, 100, 6000, 1);
        randomObjects(Fire.class, 700, 600, 1000, 900, 5);
        setPaintOrder(HUD.class, MovingActor.class);
        boundingRocks(-2_000, -1_000, 2_000, 1_000);

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
        if (enemy.getWorld() == null) {
            enemy = new Enemy();
            addObject(enemy, 200, getHeight() / 2);
        }
    }
}
