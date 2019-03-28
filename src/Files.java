import java.io.File;

public class Files {
    private static final String IMAGES_PATH = "images" + File.separator;

    private static final String ARROWS_PATH = IMAGES_PATH + "Arrows";
    private static final String BUTTONS_PATH = IMAGES_PATH  + "Buttons"+ File.separator;
    private static final String CHARACTERS_PATH = IMAGES_PATH  + "Characters"+ File.separator;
    private static final String ENVIRONMENT_PATH = IMAGES_PATH  + "Environment"+ File.separator;
    private static final String HUD_MENU_IMAGES_PATH = IMAGES_PATH + "Hud_Menu_Images"+File.separator ;
    private static final String ITEM_IMAGES_PATH = IMAGES_PATH  + "ItemImages"+ File.separator;
    private static final String LOGOS_PATH = IMAGES_PATH  + "Logos"+File.separator;
    private static final String SCREENS_PATH =  IMAGES_PATH + "Screens"+ File.separator ;

    private static final String PLAYER_PATH =  CHARACTERS_PATH + "Player"+ File.separator ;

    public static String getPlayerPath() {
        return PLAYER_PATH;
    }

    public static String getIMAGES_PATH() {
        return IMAGES_PATH;
    }

    public static String getARROWS_PATH() {
        return ARROWS_PATH;
    }

    public static String getBUTTONS_PATH() {
        return BUTTONS_PATH;
    }

    public static String getCHARACTERS_PATH() {
        return CHARACTERS_PATH;
    }

    public static String getENVIRONMENT_PATH() {
        return ENVIRONMENT_PATH;
    }

    public static String getHUD_MENU_IMAGES_PATH() {
        return HUD_MENU_IMAGES_PATH;
    }

    public static String getITEM_IMAGES_PATH() {
        return ITEM_IMAGES_PATH;
    }

    public static String getLogosPath() {
        return LOGOS_PATH;
    }
    public static String getSCREENS_PATH() {
        return SCREENS_PATH;
    }
}
