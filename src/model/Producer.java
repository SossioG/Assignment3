package model;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Producer extends Thread{

    private Buffer<FoodItem> foodItemBuffer;
    private FoodItem[] foodItems;
    private Random random = new Random();
    private static boolean AxFood = true;
    private static boolean Scan = false;
    private static boolean Arla = false;
    Semaphore mutex;
    Semaphore semaphore;


    private Thread thread;

    public Producer(FoodItem[] foodList, Buffer<FoodItem> foodItemBuffer) {
        this.foodItemBuffer = foodItemBuffer;
        this.foodItems = foodList;
        start();
    }


    /// [flags]
    public static void stopAxFood(){
        AxFood = true;
    }
    public static void stopArla(){
        Arla = true;
    }
    public static void stopScan(){
        Scan = true;
    }
    public static void startAxFood(){
        AxFood = false;
    }
    public static void startArla(){
        Arla = false;
    }
    public static void startScan(){
        Scan = false;
    }

    @Override
    public void run() {
        while(!Thread.interrupted()) {
            try {
                Thread.sleep(1000);
                semaphore = new Semaphore(4);
                mutex = new Semaphore(1);
                semaphore.acquire();
                mutex.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switch (Thread.currentThread().getName()) {
                case "AxFood":
                    if (!AxFood) {
                        System.out.println(Thread.currentThread().getName() + " current thread");
                        produce(Thread.currentThread().getName());
                    }
                    break;
                case "Scan":
                    if (!Scan) {
                        System.out.println(Thread.currentThread().getName() + " current thread");
                        produce(Thread.currentThread().getName());
                    }
                    break;
                case "Arla":
                    if (!Arla) {
                        System.out.println(Thread.currentThread().getName() + " current thread");
                        produce(Thread.currentThread().getName());
                    }
                    break;
            }
            semaphore.release();
            mutex.release();
        }

    }

    private void produce(String producerName) {
        System.out.println("-------------------------\n" +producerName +" Producing...");
        try {
            int pickTarget = random.nextInt(10);
            System.out.println(producerName + " Picked product: " + foodItems[pickTarget].getName());
            foodItemBuffer.put(foodItems[pickTarget]);

            // display in gui
            System.out.println(producerName + " produced: " +foodItems[pickTarget].getName());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
