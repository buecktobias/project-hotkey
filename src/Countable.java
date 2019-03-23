public interface Countable {
    void setItemCount(int itemCount);
    int getItemCount();

    void compareIDWith(Item item, Item[] inventoryArray);
    /* compareIDWith method implementation:
    public void compareIDWith(Item item, Item[] inventoryArray){
        if (item.getItemId() == this.getItemId()) {
            item.setItemCount(item.getItemCount() + 1);
            getWorld().removeObject(this);
        }else {
            pick(inventoryArray);
        }
    }
    */

}

