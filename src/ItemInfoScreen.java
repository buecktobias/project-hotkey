import greenfoot.Color;
import greenfoot.Font;
import greenfoot.GreenfootImage;
import greenfoot.World;

public class ItemInfoScreen extends Inventory implements Fixed {


    // TODO fix Screen not appearing/ disappearing correctly
    // TODO get current Item
    private Item item = super.getItemForInfo();
    private GreenfootImage ItemInfoScreen = new GreenfootImage(Files.getHUD_MENU_IMAGES_PATH() + "ItemInfoScreenV1.png");

    protected void addedToWorld(World world) {
    }

    public ItemInfoScreen(Player p, World world){
        super(p, world);
    }

    public void act() {
        ItemInfoScreen.clear();
        ItemInfoScreen = new GreenfootImage(Files.getHUD_MENU_IMAGES_PATH() + "ItemInfoScreenV1.png");
        setImage(ItemInfoScreen);
        drawItemInfo(item);
    }

    public void drawItemInfo(Item item){
        ItemInfoScreen.setFont(new Font("SANS_SERIF",false, true, 40));
        ItemInfoScreen.setColor(new Color(255,150,0));
        //ItemInfoScreen.drawString(item.getItemName(), 300,300);
        ItemInfoScreen.drawString("Hier könnte ihre Werbung stehen!!!!", 200,300);
    }
}
