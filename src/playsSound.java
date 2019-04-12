import greenfoot.GreenfootSound;

public interface playsSound {
    default void play(GreenfootSound sound){
        if(Settings.getInstance().isSound()){
            sound.play();
        }
    }
    default void stopIfSoundIsOff(GreenfootSound sound){
        if(!Settings.getInstance().isSound()){
            sound.stop();
        }
    }
}
