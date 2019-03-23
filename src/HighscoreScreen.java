import greenfoot.GreenfootImage;
import greenfoot.World;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class HighscoreScreen extends World {
    private JSONParser parser = new JSONParser();
    private JSONObject jsonObject;
    private GreenfootImage defaultImage = new GreenfootImage("images/Screens/FPS_Window.png");
    public HighscoreScreen(){
        super(1024, 736, 1);
        defaultImage.scale(1024,736);
        this.setBackground(defaultImage);
        showHighscores();
    }
    public void showHighscores(){
        int i = 0;
        getSettings();
        JSONArray highscores = (JSONArray) jsonObject.get("highScores");
        Iterator iterator = highscores.iterator();
        GreenfootImage img = new GreenfootImage(getBackground());
        while(iterator.hasNext()){
            JSONObject highscore = (JSONObject) iterator.next();
            img.drawString(highscore.get("name").toString() + " : " + highscore.get("score").toString(),400,200+i*20);
            i++;
        }
        setBackground(img);
    }
    private void getSettings(){
        try {
            Object obj = parser.parse(new FileReader("src/Settings.json"));
            jsonObject = (JSONObject) obj;

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
