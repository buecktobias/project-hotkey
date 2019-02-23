public interface Pickable {
    int count = 0;
    boolean pickedUp = false;
    int id = 0;
    String name = "TheBestNAME";

    int getId();
    int getCount();
    void setCount(int count);
    String getName();

    /*
    Item ID List:
    Staff ID = 1
    Bow ID = 2
    Dagger = 3
    Projectiles = 0
     */
}

