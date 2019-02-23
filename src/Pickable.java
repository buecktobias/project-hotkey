import java.util.LinkedList;

public interface Pickable {
    int count = 0;
    boolean pickedUp = false;
    int id = 0;
    String name = "TheBestNAME";

    int getId();
    int getCount();
    void setCount(int count);
    String getName();
    void pick(Player p, LinkedList inventory);
    void compareIDs(Player p, LinkedList<Pickable> inventory, Pickable item);

    /*
    Item ID List:
    Staff ID = 1
    Bow ID = 2
    Dagger = 3
     */
}

