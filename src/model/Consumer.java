package model;

import java.util.concurrent.Semaphore;

public class Consumer extends Thread{

    private Buffer<FoodItem> foodItemBuffer;
    private String cunsumername;
    Semaphore mutex;
    Semaphore semaphore;

    public Consumer(Buffer<FoodItem> foodItemBuffer, String consumername) {
        this.foodItemBuffer = foodItemBuffer;
        this.cunsumername = consumername;
    }

    public void run() {
        System.out.println("-------------------------\n" +cunsumername + " Taking...");
        FoodItem foodItem;
        while(!Thread.interrupted()) {

            try {
                semaphore = new Semaphore(4);
                mutex = new Semaphore(1);
                semaphore.acquire();
                mutex.acquire();
                Thread.sleep(1000);
                foodItem = foodItemBuffer.get();

                semaphore.release();
                mutex.release();
                // display to gui
                System.out.println(cunsumername + " took: " +foodItem.getName());
            } catch (InterruptedException e) {
                break;
            }
        }
    }

}
