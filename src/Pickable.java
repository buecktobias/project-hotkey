import java.util.LinkedList;

public interface Pickable {
    int itemCount = 0;
    boolean pickedUp = false;
    int itemId = 0;
    String itemName = "TheBestNAME";

    int getItemId();
    int getItemCount();
    void setItemCount(int itemCount);
    String getItemName();
    void pick(Player p, LinkedList inventory);
    void compareIDs(Player p, LinkedList<Pickable> inventory, Pickable item);

    /*
    Item ID List:
    Staff   ID = 1
    Bow     ID = 2
    Dagger  ID = 3
     */
}

