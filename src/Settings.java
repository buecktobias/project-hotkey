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
    private boolean sound;
    // TODO turn sound off
    private final String JSON_FILE = "src/Settings.json";
    public Settings(){
            getSettings();
    }

    public boolean isSound() {
        return sound;
    }
    public String getSoundString(){
        if(sound){
            return "true";
        }
        return "false";
    }

    public void setSound(boolean sound) {
        String sound_string;
        if(sound){
            sound_string = "true";
        }else {
            sound_string = "false";
        }
        jsonObject.put("sound",sound_string);
        writeSettings();
        this.sound = sound;
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
        String sound_string = jsonObject.get("sound").toString();
        sound = sound_string.equals("true");

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
    public String getMoveLeftKey(){
        return keys.get("moveLeft").toString();
    }
    public String getMoveRightKey(){
        return keys.get("moveRight").toString();
    }

    public String getMoveDownKey(){
        return keys.get("moveDown").toString();
    }

    public String getMoveUpKey(){
        return keys.get("moveUp").toString();
    }
    public String getSprintKey(){
        return keys.get("sprint").toString();
    }

    public String getAttackKey(){
        return keys.get("attack").toString();
    }

    public String getOpenInventarKey(){
        return keys.get("openInventar").toString();

    }public String getOpenSettingWindowKey(){
        return keys.get("openSettingWindow").toString();
    }

    public String getOpenSkillWindowKey(){
        return keys.get("openSkillWindow").toString();
    }

    public String getUseAcKey(){
        return keys.get("useAc").toString();
    }
    public String getUpdateAcKey(){
        return keys.get("updateAc").toString();

    }
    public String getOpenChestKey(){
        return keys.get("openChest").toString();
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
