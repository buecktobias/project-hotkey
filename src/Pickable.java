import java.util.LinkedList;

public interface Pickable {
    int itemCount = 0;
    int itemId = 0;
    String itemType = "noTypeAssigned";
    String itemName = "noNameAssigned";

    int getItemId();
    int getItemCount();
    void setItemCount(int itemCount);
    String getItemName();
    void pick(Player p, LinkedList inventory);
    void compareIDs(Player p, LinkedList<Pickable> inventory, Pickable item);
}

