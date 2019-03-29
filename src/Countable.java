public interface Countable {

    //TODO fix Item count
    void setItemCount(int itemCount);
    int getItemCount();

    void compareIDWith(Item[] inventoryArray);
    /* compareIDWith method implementation:
   public void compareIDWith(Item[] inventoryArray){
        for(Item item1: inventoryArray){
            if(item1 != null){
                if (item1.getItemId() == itemId) {
                    Countable cItem = (Countable) item1;
                    cItem.setItemCount(cItem.getItemCount() + this.itemCount);
                    getWorld().removeObject(this);
                    return;
                }
            }
        }
        pick(inventoryArray);
    }
    */

}

