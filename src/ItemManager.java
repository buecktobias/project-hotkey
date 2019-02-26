import greenfoot.GreenfootImage;

public enum ItemManager {
    Staff(  "Weapon" , 0, "The Elder Wand",        new GreenfootImage("images/ItemImages/Wand.png")),
    Dagger( "Weapon" , 1, "Zahnstocher",           new GreenfootImage("images/ItemImages/Dagger.png")),
    Bow(    "Weapon" , 2, "Dragonslayer GreatBow", new GreenfootImage("images/ItemImages/Key.png"));

    private final int itemID;
    private final String itemTYPE;
    private final String itemNAME;
    private final GreenfootImage itemIMAGE;

    ItemManager(String itemType, int itemId, String itemName, GreenfootImage itemImage){
        itemTYPE = itemType;
        itemID = itemId;
        itemNAME = itemName;
        itemIMAGE = itemImage;
    }

    //Getters and Setters
    public int getItemID() {
        return itemID;
    }
    public String getItemTYPE() {
        return itemTYPE;
    }
    public String getItemNAME() {
        return itemNAME;
    }
    public GreenfootImage getItemIMAGE() {
        return itemIMAGE;
    }
}
