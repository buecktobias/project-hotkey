public enum ItemManager {
    Staff(  "Weapon" , 0, "The Elder Wand"),
    Dagger( "Weapon" , 1, "Zahnstocher"),
    Bow(    "Weapon" , 2, "Dragonslayer GreatBow");

    private final String itemTYPE;
    private final int itemID;
    private final String itemNAME;

    ItemManager(String itemType, int itemId, String itemName){
        itemTYPE = itemType;
        itemID = itemId;
        itemNAME = itemName;
    }


    //Getters and Setters
    public String getItemTYPE() {
        return itemTYPE;
    }
    public int getItemID() {
        return itemID;
    }

    public String getItemNAME() {
        return itemNAME;
    }
}
