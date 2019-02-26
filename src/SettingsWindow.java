import greenfoot.Actor;
import greenfoot.GreenfootImage;
import greenfoot.World;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class SettingsWindow extends Actor implements Fixed {
    private HashMap Settings= new HashMap();
    private JSONParser parser = new JSONParser();
    private GreenfootImage bg = new GreenfootImage("images/Settings_test.png");

    @Override
    protected void addedToWorld(World world) {
        System.out.println("settings");
        try {
            System.out.println("no error");
            Object obj = parser.parse(new FileReader("src/Settings.json"));
            JSONObject jsonObject = (JSONObject)obj;
            JSONObject keys = (JSONObject)jsonObject.get("keys");
            int i = 0;
            bg.setColor(Color.WHITE);
            bg.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
            for(Object key :keys.keySet()){
                bg.drawString(key.toString() + ":" + keys.get(key),50,i*20+50);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        setImage(bg);

    }
    private void showKeys(HashMap<String,Character> Keys){

    }
}
