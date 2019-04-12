import greenfoot.GreenfootImage;
import greenfoot.GreenfootSound;

public class WoodenArrow extends Projectile implements playsSound,Countable,Equippable{
    private ItemManager itemManager = ItemManager.WoodenArrow;
    private final int itemSlotId = itemManager.getItemSLOTID();
    private final int itemId = itemManager.getItemID();
    private final String itemType = itemManager.getItemTYPE();
    private String itemName = itemManager.getItemNAME();
    private GreenfootImage itemImage = itemManager.getItemIMAGE();
    private boolean IEquipped = false;
    private int itemCount;
    private GreenfootImage defaultImage = new GreenfootImage(Files.getITEM_IMAGES_PATH() + "Arrow.png");
    private GreenfootSound arrowSound = new GreenfootSound("sounds/arrow2.wav");
    @Override
    public GreenfootImage getDefaultImage() {
        return defaultImage;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public void setItemManager(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    @Override
    public int getItemSlotId() {
        return itemSlotId;
    }

    @Override
    public int getItemId() {
        return itemId;
    }

    @Override
    public String getItemType() {
        return itemType;
    }

    @Override
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public GreenfootImage getItemImage() {
        return itemImage;
    }

    public void setItemImage(GreenfootImage itemImage) {
        this.itemImage = itemImage;
    }

    @Override
    public boolean isIEquipped() {
        return IEquipped;
    }

    @Override
    public void setIEquipped(boolean IEquipped) {
        this.IEquipped = IEquipped;
    }

    public void makeShootingSound() {
        play(arrowSound);
    }

    public WoodenArrow(){
        super(10,40,0.1,0);
        defaultImage.scale(32,32);
        this.itemCount = 1;
        setImage(defaultImage);
    }
    public WoodenArrow(int itemCount){
        super(10,40,0.1,0);
        defaultImage.scale(32,32);
        this.itemCount = itemCount;
        setImage(defaultImage);

    }

    public WoodenArrow(int damage, double speed, double drag, int scatter){
        super(damage, speed, drag, scatter);
        defaultImage.scale(32,32);
        setImage(defaultImage);
    }
    public void act() {
        //updatePosition();
    }
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
    public int getItemCount() {
        return itemCount;
    }
    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }
}