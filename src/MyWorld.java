import greenfoot.World;

import java.io.File;

public class MyWorld extends World {
    public MyWorld(int width,int height,int cellSize){
        super(width, height, cellSize,false);
    }
    private final String IMAGES_PATH = "images";

    private final String ARROWS_PATH = IMAGES_PATH + File.separator + "Arrows";
    private final String BUTTONS_PATH = IMAGES_PATH + File.separator + "Buttons";
    private final String CHARACTERS_PATH = IMAGES_PATH + File.separator + "Characters";
    private final String ENVIRONMENT_PATH = IMAGES_PATH + File.separator + "Environment";
    private final String HUD_MENU_IMAGES_PATH = IMAGES_PATH +File.separator + "Hud_Menu_Images";
    private final String ITEM_IMAGES_PATH = IMAGES_PATH + File.separator + "ItemImages";
    private final String LOGOS_PATH = IMAGES_PATH +File.separator + "Logos";
    private final String PALETTE_PATH = IMAGES_PATH + File.separator + "palette";
    private final String SCREENS_PATH =  IMAGES_PATH + File.separator + "Screens";

    public String getIMAGES_PATH() {
        return IMAGES_PATH;
    }

    public String getARROWS_PATH() {
        return ARROWS_PATH;
    }

    public String getBUTTONS_PATH() {
        return BUTTONS_PATH;
    }

    public String getCHARACTERS_PATH() {
        return CHARACTERS_PATH;
    }

    public String getENVIRONMENT_PATH() {
        return ENVIRONMENT_PATH;
    }

    public String getHUD_MENU_IMAGES_PATH() {
        return HUD_MENU_IMAGES_PATH;
    }

    public String getITEM_IMAGES_PATH() {
        return ITEM_IMAGES_PATH;
    }

    public String getLogosPath() {
        return LOGOS_PATH;
    }

    public String getPALETTE_PATH() {
        return PALETTE_PATH;
    }

    public String getSCREENS_PATH() {
        return SCREENS_PATH;
    }
}
