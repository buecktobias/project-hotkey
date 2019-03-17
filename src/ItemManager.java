import greenfoot.GreenfootImage;

public enum ItemManager {

    //Weapons
    Staff(  "Weapon",0,"The Elder Wand",       5, new GreenfootImage("images/ItemImages/Wand.png")),
    Dagger( "Weapon",1,"Zahnstocher",          6, new GreenfootImage("images/ItemImages/Dagger.png")),
    Bow(    "Weapon",2,"Dragonslayer GreatBow",5, new GreenfootImage("images/ItemImages/Key.png"));

    private final int itemID;
    private final int itemSLOTID;
    private final String itemTYPE;
    private final String itemNAME;
    private final GreenfootImage itemIMAGE;

    ItemManager(String itemType, int itemId, String itemName, int itemSlotId, GreenfootImage itemImage){
        itemTYPE = itemType;    // current Types: "SecondaryWeapon", "PrimaryWeapon", "Armor", || if new Type is needed message Julian
        itemID = itemId;
        itemNAME = itemName;
        itemSLOTID = itemSlotId;
        itemIMAGE = itemImage;
    }
    // all possible itemSlotId´s
    // Items which can not be equipped = -1
    // Helmet  0
    // Chest   1
    // Legs    2
    // Boots   3
    // Pet     4
    // Primary 5
    // Secondary 6

    //Getters and Setters
    public int getItemID() {
        return itemID;
    }
    public int getItemSLOTID(){return  itemSLOTID;}
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
