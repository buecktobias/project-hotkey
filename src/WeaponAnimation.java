import greenfoot.GreenfootImage;

public class WeaponAnimation extends Entity implements Fixed {

    /*  Diese Klasse ist nur dafür da, die Animation von der Waffe in der Welt anzuzeigen.
     *  Deswegen wird auch nur ein Bild übergeben.
     *  Mithilfe dem übergebenen Bild wird ein neues Bild erstellt,
     *  weil sich sonst das Bild von der richtigen Waffe auch
     *  ändern würde.
     */

    private GreenfootImage image;
    private int speed = 10;
    private int rotated = 0;
    private int startAt;
    private int stopAt;

    public WeaponAnimation(GreenfootImage image, int speed, int startDegrees, int stopDegrees) {
        setImage(image);
        this.image = new GreenfootImage(image);
        this.speed = speed;
        this.startAt = startDegrees;
        this.stopAt = stopDegrees;
    }

    public void act() {

        GreenfootImage newImg = new GreenfootImage(this.image.getWidth()+128, this.image.getHeight()+128);
        newImg.drawImage(this.image, 32, 32);
        newImg.rotate(startAt + rotated);
        rotated = rotated + speed;
        setImage(newImg);
        if(rotated >= stopAt-startAt) {
            getWorld().removeObject(this);
        }

    }
}
