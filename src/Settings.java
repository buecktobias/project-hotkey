import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

public class Settings {
    private static Settings ourInstance = new Settings();

    private JSONParser parser = new JSONParser();
    private JSONObject jsonObject;
    private JSONObject keys;
    private String gameMode;
    private Object obj;
    // TODO turn sound off
    private final String JSON_FILE = "src/Settings.json";
    public Settings(){
            getSettings();
    }
    private void getSettings() {
        {
            try {
                obj = parser.parse(new FileReader(JSON_FILE));
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        }
        jsonObject = (JSONObject) obj;
        keys = (JSONObject) jsonObject.get("keys");
        gameMode = jsonObject.get("gameMode").toString();
    }
    private void writeSettings(){
        try {
            java.nio.file.Files.write(Paths.get(JSON_FILE),jsonObject.toJSONString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getKeys() {
        return keys;
    }
    public void setKeys(JSONObject keys){
        this.keys = keys;
        this.jsonObject.put("keys",keys);
        writeSettings();
    }

    public String getGameMode() {
        return gameMode;
    }
    public void setGameMode(String gameMode){
        this.gameMode = gameMode;
        this.jsonObject.put("gameMode",gameMode);
        writeSettings();
    }

    public static Settings getInstance() {
        return ourInstance;
    }
}
