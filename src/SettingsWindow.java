import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.World;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class SettingsWindow extends Actor implements Fixed {
    private JSONParser parser = new JSONParser();
    private LinkedList<Button> buttonList = new LinkedList<>();
    private GreenfootImage bg = new GreenfootImage("images/Screens/Settings_test.png");

    @Override
    protected void addedToWorld(World world) {
        try {
            Object obj = parser.parse(new FileReader("src/Settings.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject keys = (JSONObject) jsonObject.get("keys");
            showKeys(keys);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void showKeys(JSONObject keys) {
        GreenfootImage img = new GreenfootImage(bg);
        int i = 0;
        img.setColor(Color.WHITE);
        img.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        Button button;
        GreenfootImage buttonImg = new GreenfootImage("images/Buttons/test_Button.png");
        buttonImg.scale(30,30);
        for (Object key : keys.keySet()) {
            img.drawString(key.toString(), 50, i * 40 + 50);
            img.drawString(keys.get(key).toString(),250,i*40+50);
            button = new Button(buttonImg,buttonImg){
                @Override
                void clicked() {
                    String newKey = Greenfoot.ask("Which Key do you want to use  for " + key.toString() + "instead of" + keys.get(key));
                    keys.put(key,newKey);
                    showKeys(keys);
                }
            };
            buttonList.add(button);
            getWorld().addObject(button,550,i * 40 + 150);
            i++;
        }
        setImage(img);

    }
}

