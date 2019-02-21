import greenfoot.GreenfootImage;
import greenfoot.World;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;

public class SkillWindow extends General implements Fixed{
    private World world;
    private final  GreenfootImage bg;
    private LinkedList<Button> buttonList;
    private HashMap<String, Integer> skills = new HashMap<String, Integer>(){
        {
            put("Strength",0);
            put("Speed",0);
            put("Health",0);
            put("Endurance",0);
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
        buttonList = new LinkedList<Button>();
        this.world = world;
        buttonList = new LinkedList<>();
        bg = new GreenfootImage("images/SkillScreen2.png");
        bg.setTransparency(150);
        }
    public void showSkills() {
        setImage(new GreenfootImage(bg));
        deleteButtons();
        GreenfootImage image = getImage();
        image.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 50));
        String[] keys = skills.keySet().toArray(new String[0]);
        Player player = world.getObjects(Player.class).get(0);
        int skillPoints = player.getLevel() - usedSkillPoints();
        String stringSkillPoints = String.valueOf(skillPoints);
        image.drawString("SkillPoints " + stringSkillPoints,100,60);
        image.setColor(Color.BLACK);
        image.fillRect(20,70,image.getWidth()-40,100);
        for (int i = 0; i < keys.length; i++){
            image.setColor(Color.BLACK);
            int ySkill = 150;
            int y = ySkill+ 20 + i * 60;
            image.setColor(Color.DARK_GRAY);
            image.fillRect(20,y-50,image.getWidth()-40,60);
            image.setColor(Color.BLACK);
            image.drawString(keys[i], 50, y);
            image.drawString(String.valueOf(skills.get(keys[i])), 400, y);
            setImage(image);
        }
        if(player.getLevel() - usedSkillPoints() > 0 && buttonList.size() == 0){
            createButtons();
        }
    }
    private void createButtons(){
        String[] keys = skills.keySet().toArray(new String[0]);
        int ySkill = 150;
        Button button;
        GreenfootImage buttonImgUnClicked;
        GreenfootImage buttonImgClicked;
        for (int i = 0; i < keys.length; i++) {
            int finalI = i;
            buttonImgUnClicked = new GreenfootImage("images/plus.png");
            buttonImgClicked = new GreenfootImage("images/plus_clicked.png");
            buttonImgUnClicked.scale(40, 40);
            buttonImgClicked.scale(35, 35);
            button = new Button(buttonImgUnClicked,buttonImgClicked) {
                @Override
                public void clicked() {
                    skills.put(keys[finalI], skills.get(keys[finalI]) + 1);
                    updatePlayersAttributes();
                }
            };
            buttonList.add(button);
            world.addObject(button, 200 + getX(), ySkill + i * 60 + getY() - getHeight() / 2);
        }
    }
    private void updatePlayersAttributes(){
            Player player = world.getObjects(Player.class).get(0);
            player.setMaxLife(player.getMaxLife() + skills.get("Health") * 100);
            player.setNormalSpeed(player.getNormalSpeed() + skills.get("Speed"));
            player.setSprintSpeed(player.getSprintSpeed() + skills.get("Speed"));
            player.setDamage(player.getDamage() + skills.get("Strength"));
            player.setMaxEndurance(player.getMaxEndurance() + skills.get("Endurance") * 10);
            showSkills();
    }
}
