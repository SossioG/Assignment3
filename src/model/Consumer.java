package model;

public class Consumer extends Thread{

    private Buffer<FoodItem> foodItemBuffer;
    private String cunsumername;

    public Consumer(Buffer<FoodItem> foodItemBuffer, String consumername) {
        this.foodItemBuffer = foodItemBuffer;
        this.cunsumername = consumername;
    }

    public void run() {
        System.out.println("-------------------------\n" +cunsumername + " Taking...");
        FoodItem foodItem;
        while(!Thread.interrupted()) {
            try {
                Thread.sleep(1000);
                foodItem = foodItemBuffer.get();
                // display to gui
                System.out.println(cunsumername + " took: " +foodItem.getName());
            } catch (InterruptedException e) {
                break;
            }
        }
    }

}
