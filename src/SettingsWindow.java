import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.World;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;

public class SettingsWindow extends Window {
    private JSONObject jsonObject;
    private final String[] possibleKeys = new String[]{"up","left","down","right","shift","control","backspace","tab","enter","F1","F2","F3","F4","F5","F6","F7","F8","F9","F10","F11","F12","0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    private JSONParser parser = new JSONParser();
    private LinkedList<Button> buttonList = new LinkedList<>();
    private GreenfootImage bg = new GreenfootImage("images/Screens/Settings_test.png");
    private String gameMode;
    @Override
    protected void addedToWorld(World world) {
        try {
            Object obj = parser.parse(new FileReader("src/Settings.json"));
            jsonObject = (JSONObject) obj;
            JSONObject keys = (JSONObject) jsonObject.get("keys");
            gameMode = jsonObject.get("gameMode").toString();
            showKeys(keys);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
    public void deleteButtons(){
        for(Button button:buttonList){
            getWorld().removeObject(button);
        }
        buttonList = new LinkedList<>();
    }

    private void showKeys(JSONObject keys) {
        GreenfootImage img = new GreenfootImage(bg);
        int i = 0;
        img.setTransparency(160);
        img.setColor(Color.WHITE);
        img.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        Button button;
        for (Object key : keys.keySet()) {
            img.drawString(key.toString(), 50, i * 40 + 50);
            GreenfootImage buttonImg = new GreenfootImage("images/Buttons/test.png");
            buttonImg.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
            buttonImg.scale(500,36);
            buttonImg.setTransparency(80);
            img.drawString(keys.get(key).toString(),450,i*40+50);
            button = new Button(buttonImg,buttonImg){
                @Override
                void clicked() {
                    String newKey;
                    newKey = Greenfoot.ask("Which Key do you want to use  for " + key.toString() + " instead of " + keys.get(key));
                    while(!(Arrays.asList(possibleKeys).contains(newKey)) || keys.containsValue(newKey)){
                        newKey = Greenfoot.ask(newKey + " is not a valid key please enter a valid key");
                    }
                    keys.put(key,newKey);
                    showKeys(keys);
                    jsonObject.put("keys",keys);
                    try {
                        Files.write(Paths.get("src/Settings.json"),jsonObject.toJSONString().getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            buttonList.add(button);
            ((OpenWorld)getWorld()).addObjectTopLeftCorner(button,720,i * 40 + 160);
            i++;
        }
        setImage(img);

    }
}

