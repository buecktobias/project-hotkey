import greenfoot.GreenfootImage;

public enum ItemManager {
    Staff(  "Weapon" , 0, "The Elder Wand",        new GreenfootImage("images/Wand.png")),
    Dagger( "Weapon" , 1, "Zahnstocher",           new GreenfootImage("images/plus.png")),
    Bow(    "Weapon" , 2, "Dragonslayer GreatBow", new GreenfootImage("images/plus.png"));

    private final String itemTYPE;
    private final int itemID;
    private final String itemNAME;
    private final GreenfootImage itemIMAGE;

    ItemManager(String itemType, int itemId, String itemName, GreenfootImage itemImage){
        itemTYPE = itemType;
        itemID = itemId;
        itemNAME = itemName;
        itemIMAGE = itemImage;
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
    public GreenfootImage getItemIMAGE() {
        return itemIMAGE;
    }
}
