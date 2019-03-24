public interface Countable {

    //TODO fix Item count
    void setItemCount(int itemCount);
    int getItemCount();

    void compareIDWith(Item item, Item[] inventoryArray);
    /* compareIDWith method implementation:
   public void compareIDWith(Item item, Item[] inventoryArray){
        Countable cItem = (Countable) item;
        if (item.getItemId() == this.getItemId()) {
            getWorld().removeObject(this);

        }else {
            pick(inventoryArray);
        }
        cItem.setItemCount(this.itemCount + cItem.getItemCount());
    }
    */

}

