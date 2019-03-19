import greenfoot.GreenfootImage;

public class AttackingWeapon extends General implements Fixed {

    /*  Diese Klasse ist nur dafür da, die Animation von der Waffe in der Welt anzuzeigen.
     *  Deswegen wird auch nur ein Bild übergeben.
     *  Mithilfe dem übergebenen Bild wird ein neues Bild erstellt,
     *  weil sich sonst das Bild von der richtigen Waffe auch
     *  ändern würde.
     */

    private GreenfootImage image;
    private int frame = 0;
    private int speed = 10;
    private int stopAt = 180;

    public AttackingWeapon(GreenfootImage image) {
        setImage(image);
        this.image = new GreenfootImage(image);
    }

    public void act() {
        GreenfootImage newImg = new GreenfootImage(this.image.getWidth()+128, this.image.getHeight()+128);
        newImg.drawImage(this.image, 32, 32);
        newImg.rotate(this.frame*this.speed);
        setImage(newImg);
        this.frame++;
        if(this.frame >= this.stopAt/this.speed) {
            getWorld().removeObject(this);
        }
    }
}
