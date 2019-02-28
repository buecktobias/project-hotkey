import greenfoot.GreenfootImage;
import greenfoot.World;

public class ItemInfoScreen extends Inventory implements Fixed {


    //TODO create ItemInfoScreenV1.png
    private Pickable item = super.getItemForInfo();
    private GreenfootImage ItemInfoScreen = new GreenfootImage("images/Hud_Menu_Images/ItemInfoScreenV1.png");

    protected void addedToWorld(World world) {
        System.out.println("2");
    }

    public ItemInfoScreen(Player p, World world){
        super(p, world);
        System.out.println("1");
    }


    public void act() {
        System.out.println("3");
        setImage(ItemInfoScreen);
        drawItemInfo(item);

    }

    public void drawItemInfo(Pickable item){

    }
}
