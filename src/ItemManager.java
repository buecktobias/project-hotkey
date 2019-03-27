import greenfoot.GreenfootImage;

public enum ItemManager {

    //Weapons
    Staff(        "Weapon",    0,"The Elder Wand",       4, new GreenfootImage("images/496_RPG_icons(1)/W_Staff04.png")),
    Dagger(       "Weapon",    1,"Zahnstocher",          5, new GreenfootImage("images/496_RPG_icons(1)/W_Dagger006.png")),
    Bow(          "Weapon",    2,"Dragonslayer GreatBow",4, new GreenfootImage("images/496_RPG_icons(1)/W_Bow03.png")),
    HealthPotion( "Consumable",3,"HealthPotion",         7, new GreenfootImage("images/496_RPG_icons(1)/P_Red01.png")),
    WoodenArrow(  "Consumable",4,"wooden Arrow",         6, new GreenfootImage("images/496_RPG_icons(1)/W_Bow17.png"));

    private final int itemID;
    private final int itemSLOTID;
    private final String itemTYPE;
    private final String itemNAME;
    private final GreenfootImage itemIMAGE;

    ItemManager(String itemType, int itemId, String itemName, int itemSlotId, GreenfootImage itemImage){
        itemTYPE = itemType;    // current Types: "Weapon", "Weapon", "Armor", "Consumable" || if new Type is needed message Julian
        itemID = itemId;
        itemNAME = itemName;
        itemSLOTID = itemSlotId;
        itemIMAGE = itemImage;
    }
    // all possible itemSlotIdīs
    // Items which can not be equipped = -1
    // Helmet  0
    // Chest   1
    // Legs    2
    // Boots   3
    // Primary 4
    // Secondary 5

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
