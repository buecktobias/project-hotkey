import greenfoot.Color;
import greenfoot.Font;
import greenfoot.GreenfootImage;
import greenfoot.World;

import java.util.HashMap;
import java.util.LinkedList;

public class SkillWindow extends Window{
    private World world;
    private final  GreenfootImage bg;
    private LinkedList<Button> buttonList;
    private final String STRENGTH_STRING = "Strength";
    private final int STRENGTH_START_VALUE = 0;
    private final String SPEED_STRING = "Speed";
    private final int SPEED_START_VALUE = 0;
    private final String HEALTH_STRING = "Health";
    private final int HEALTH_START_VALUE = 0;
    private final String ENDURANCE_STRING = "Endurance";
    private final int ENDURANCE_START_VALUE = 0;
    private final String LIFE_REGENERATION_STRING = "LifeRegeneration";
    private final int LIFE_REGENERATION_START_VALUE = 0;
    private final int IMAGE_TRANSPARENCY = 150;
    private final String SKILL_POINTS_STRING = "SkillPoints";
    private final Font TEXT_FONT = new Font("SANS_SERIF",false,true, 20);
    private HashMap<String, Integer> skills = new HashMap<String, Integer>(){
        {
            put(STRENGTH_STRING,STRENGTH_START_VALUE);
            put(SPEED_STRING,SPEED_START_VALUE);
            put(HEALTH_STRING,HEALTH_START_VALUE);
            put(ENDURANCE_STRING,ENDURANCE_START_VALUE);
            put(LIFE_REGENERATION_STRING,LIFE_REGENERATION_START_VALUE);
        }
    };
    public void deleteButtons(){
        for(Button button:buttonList){
            world.removeObject(button);
        }
        buttonList = new LinkedList<>();
    }

    @Override
    protected void addedToWorld(World world) {

        showSkills();
        Player player = world.getObjects(Player.class).get(0);
        if(player.getLevel() - usedSkillPoints() > 0) {
            createButtons();
        }
    }
    public int usedSkillPoints(){
        int skillPoints = 0;
        for(int value:skills.values()){
            skillPoints += value;
        }
        return skillPoints;
    }

    public SkillWindow(World world){
        buttonList = new LinkedList<>();
        this.world = world;
        buttonList = new LinkedList<>();
        bg = new GreenfootImage(Files.getHUD_MENU_IMAGES_PATH() + "SkillScreenV1.png");
        bg.setTransparency(IMAGE_TRANSPARENCY);
        }
    public void showSkills() {
        setImage(new GreenfootImage(bg));
        deleteButtons();
        GreenfootImage image = getImage();
        image.setFont(TEXT_FONT);
        String[] keys = skills.keySet().toArray(new String[0]);
        Player player = Player.getInstance();
        int skillPoints = player.getLevel() - usedSkillPoints();
        String stringSkillPoints = String.valueOf(skillPoints);
        image.setColor(Color.WHITE);
        image.drawString(SKILL_POINTS_STRING + stringSkillPoints,280,160);
        for (int i = 0; i < keys.length; i++){
            int ySkill = 200;
            int y = ySkill+ i * 60;
            image.setColor(Color.WHITE);
            image.drawString(keys[i], 240, y);
            image.drawString(String.valueOf(skills.get(keys[i])), 400, y);
            setImage(image);
        }
        if(player.getLevel() - usedSkillPoints() > 0 && buttonList.size() == 0){
            createButtons();
        }
    }
    private void createButtons(){
        String[] keys = skills.keySet().toArray(new String[0]);
        int ySkill = 195;
        Button button;
        GreenfootImage buttonImgUnClicked = new GreenfootImage(Files.getBUTTONS_PATH() + "plus.png");
        GreenfootImage buttonImgClicked = new GreenfootImage(Files.getBUTTONS_PATH() + "plus_clicked.png");
        buttonImgUnClicked.scale(40, 40);
        buttonImgClicked.scale(35, 35);
        for (int i = 0; i < keys.length; i++) {
            int finalI = i;
            button = new Button(buttonImgUnClicked,buttonImgClicked) {
                @Override
                public void clicked() {
                    skills.put(keys[finalI], skills.get(keys[finalI]) + 1);
                    updatePlayersAttributes();
                }
            };
            buttonList.add(button);
            world.addObject(button, 100 + getX(), ySkill + i * 60 + getY() - this.getImage().getHeight() / 2);
        }
    }
    private void updatePlayersAttributes(){
            Player player = world.getObjects(Player.class).get(0);
            player.setMaxLife(player.getMaxLife() + skills.get(HEALTH_STRING) * 100);
            player.setNormalSpeed(player.getNormalSpeed() + skills.get(SPEED_STRING));
            player.setSprintSpeed(player.getSprintSpeed() + skills.get(SPEED_STRING));
            player.setDamage(player.getDamage() + skills.get(STRENGTH_STRING));
            player.setMaxEndurance(player.getMaxEndurance() + skills.get(ENDURANCE_STRING) * 10);
            player.setLifeRegeneration(player.getLifeRegeneration() + skills.get(LIFE_REGENERATION_STRING) * player.getNORMAL_LIFE_REGENERATION());
            showSkills();
    }
}
