package model;

public class Consumer extends Thread{

    private Buffer<FoodItem> foodItemBuffer;

    public Consumer(Buffer<FoodItem> foodItemBuffer) {
        this.foodItemBuffer = foodItemBuffer;
    }

    public void run() {
        System.out.println("Consumer runs!");
        FoodItem foodItem;
        while(!Thread.interrupted()) {
            try {
                foodItem = foodItemBuffer.get();
            } catch (InterruptedException e) {
                break;
            }
        }
    }

}
