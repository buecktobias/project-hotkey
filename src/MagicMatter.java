import greenfoot.GreenfootImage;
import greenfoot.GreenfootSound;

public class MagicMatter extends Projectile {
    private ItemManager itemManager = ItemManager.WoodenArrow;
    private final int itemSlotId = itemManager.getItemSLOTID();
    private final int itemId = itemManager.getItemID();
    private final String itemType = itemManager.getItemTYPE();
    private String itemName = itemManager.getItemNAME();
    private GreenfootImage itemImage = itemManager.getItemIMAGE();
    private boolean IEquipped = false;
    private GreenfootImage defaultImage =  new GreenfootImage(Files.getITEM_IMAGES_PATH() + "magicMatter.png");
    private GreenfootSound magicSound = new GreenfootSound("sounds/magicMatter2.wav");

    @Override
    public void setIEquipped(boolean IEquipped) {
        this.IEquipped = IEquipped;
    }

    @Override
    public GreenfootImage getDefaultImage() {
        return defaultImage;
    }

    @Override
    public void makeShootingSound() {
        magicSound.play();
    }

    public ItemManager getItemManager() {
        return itemManager;
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

    @Override
    public GreenfootImage getItemImage() {
        return itemImage;
    }

    @Override
    public boolean isIEquipped() {
        return IEquipped;
    }

    public MagicMatter(int damage, double speed, double drag, int scatter){
        super(damage, speed, drag, scatter);
        setImage(defaultImage);
    }
    public void act() {
        updatePosition();
    }
}
