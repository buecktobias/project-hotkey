import greenfoot.Actor;

public class Inventory extends Actor {

    private Actor[] items;
    private Actor[] itemImages;
    private int capacity;

    public Inventory(){
        this.capacity = 42;
    }


    public void act(){
            drawInventory();
    }
    public void drawInventory(){
        System.out.println("Inventory working");
    }
    //Getters and Setters
}
