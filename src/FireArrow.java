import greenfoot.GreenfootImage;
import greenfoot.GreenfootSound;

public class FireArrow extends Projectile{
    private ItemManager itemManager = ItemManager.WoodenArrow;
    private final int itemSlotId = itemManager.getItemSLOTID();
    private final int itemId = itemManager.getItemID();
    private final String itemType = itemManager.getItemTYPE();
    private String itemName = itemManager.getItemNAME();
    private GreenfootImage itemImage = itemManager.getItemIMAGE();
    private boolean IEquipped = false;

    @Override
    public void setIEquipped(boolean IEquipped) {
        this.IEquipped = IEquipped;
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

    private GreenfootImage defaultImage = new GreenfootImage(Files.getITEM_IMAGES_PATH() + "FireArrow.png");
    private GreenfootSound arrowSound = new GreenfootSound("sounds/arrow2.wav");
    private double fireDamage = 5;
    public GreenfootImage getDefaultImage() {
        return defaultImage;
    }

    public FireArrow(int damage, double speed, double drag, int scatter,double fireDamage){
        super(damage, speed, drag, scatter);
        this.fireDamage = fireDamage;
        setImage(defaultImage);
    }

    public void hits(Attackable attackable){
        if(attackable instanceof FireSensitive){
            ((FireSensitive) attackable).setFireDamage(fireDamage);
        }
    }

    public void makeShootingSound() {
        arrowSound.play();
    }

    public void act() {
        updatePosition();
    }
}
