package projektHotkey.helper;

/**
 * @author SAE
 */
public enum Direction {

    LEFT(180),
    RIGHT(0),
    DOWN(90),
    UP(270);

    private int value = 0;

    private Direction(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static Direction fromValue(int value) {
        for (Direction dir : values()) {
            if (dir.getValue() == value) {
                return dir;
            }
        }
        return null;
    }
}
