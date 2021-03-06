import greenfoot.Color;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.World;
import org.json.simple.JSONObject;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SettingsWindow extends Window {
    private Settings settings = Settings.getInstance();
    private final String[] possibleKeys = new String[]{"up","left","down","right","shift","control","backspace","tab","enter","F1","F2","F3","F4","F5","F6","F7","F8","F9","F10","F11","F12","0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    private JSONObject keys;
    private LinkedList<Button> buttonList = new LinkedList<>();
    private GreenfootImage bg = new GreenfootImage(Files.getSCREENS_PATH() + "Settings_test.png");
    private String gameMode;
    private List<String> possibleGameModes;
    @Override
    protected void addedToWorld(World world) {

        possibleGameModes = new LinkedList<>();
        for(GameMode gameMode:GameMode.values()){
            possibleGameModes.add(gameMode.name);
        }
        showKeys();

    }
    private void getSettings(){
            keys = settings.getKeys();
            gameMode = settings.getGameMode();
    }
    public void deleteButtons(){
        for(Button button:buttonList){
            getWorld().removeObject(button);
        }
        buttonList = new LinkedList<>();
    }


    // TODO buttons and text at the same position
    private void showKeys() {
        int buttonHeight = 36;
        int buttonWidth = 500;
        int buttonTransparency = 80;
        int buttonX = 750;
        int textX = 60;
        int text2X = 400;
        int textSize = 20;
        int marginTop = 50;
        //Font textFont = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
        getSettings();



        GreenfootImage img = new GreenfootImage(bg);
        int i = 0;

        img.setTransparency(255);
        img.setColor(Color.WHITE);
        //img.setFont(textFont);
        Button button;
        img.drawString("Sound",textX, i * (textSize * 2) + marginTop);
        GreenfootImage buttonImgG = new GreenfootImage(Files.getBUTTONS_PATH() + "test.png");
        //buttonImgG.setFont(textFont);
        buttonImgG.scale(buttonWidth,buttonHeight);
        buttonImgG.setTransparency(buttonTransparency);
        img.drawString(Settings.getInstance().getSoundString(),text2X,i * (textSize * 2) + marginTop);
        button = new Button(buttonImgG,buttonImgG){
            @Override
            void clicked() {
                if(Settings.getInstance().isSound()){
                    Settings.getInstance().setSound(false);
                }else{
                    Settings.getInstance().setSound(true);
                }
                showKeys();
            }
        };
        buttonList.add(button);
        ((OpenWorld)getWorld()).addObjectTopLeftCorner(button,buttonX,i * textSize * 2 + 110);
        i++;

        img.setTransparency(255);
        img.setColor(Color.WHITE);
        //img.setFont(textFont);
        Button button2;
        img.drawString("gameMode",textX, i * (textSize * 2) + marginTop);
        GreenfootImage buttonImgGameMode = new GreenfootImage(Files.getBUTTONS_PATH() + "test.png");
        //buttonImgGameMode.setFont(textFont);
        buttonImgGameMode.scale(buttonWidth,buttonHeight);
        buttonImgGameMode.setTransparency(buttonTransparency);
        img.drawString(gameMode,text2X,i * (textSize * 2) + marginTop);
        button2 = new Button(buttonImgGameMode,buttonImgGameMode){
            @Override
            void clicked() {
                String newGameMode = Greenfoot.ask("New GameMode? Peaceful,Easy,Normal,Hard");
                while( !(possibleGameModes.contains(newGameMode))){
                    newGameMode = Greenfoot.ask(newGameMode + " is not a valid gameMode");
                }
                settings.setGameMode(newGameMode);
                showKeys();
            }
        };
        buttonList.add(button2);
        ((OpenWorld)getWorld()).addObjectTopLeftCorner(button2,buttonX,i * textSize * 2 + 110);
        i++;
        for (Object key : keys.keySet()) {
            img.drawString(key.toString(), textX, i * (textSize * 2) + marginTop);
            GreenfootImage buttonImg = new GreenfootImage(Files.getBUTTONS_PATH() + "test.png");
            //buttonImg.setFont(textFont);
            buttonImg.scale(buttonWidth,buttonHeight);
            buttonImg.setTransparency(buttonTransparency);
            img.drawString(keys.get(key).toString(),text2X,i*(textSize * 2) + marginTop);
            button2 = new Button(buttonImg,buttonImg){
                @Override
                void clicked() {
                    String newKey;
                    newKey = Greenfoot.ask("Which Key do you want to use  for " + key.toString() + " instead of " + keys.get(key));
                    while(!(Arrays.asList(possibleKeys).contains(newKey)) || keys.containsValue(newKey)){
                        newKey = Greenfoot.ask(newKey + " is not a valid key please enter a valid key");
                    }

                    keys.put(key,newKey);
                    settings.setKeys(keys);
                    showKeys();
                }
            };
            buttonList.add(button2);
            ((OpenWorld)getWorld()).addObjectTopLeftCorner(button2,buttonX,i * textSize * 2 + 110);
            i++;
        }
        setImage(img);

    }
}

