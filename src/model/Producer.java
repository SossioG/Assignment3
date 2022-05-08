package model;

import view.GUISemaphore;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Producer extends Thread{

    private Buffer<FoodItem> foodItemBuffer;
    private GUISemaphore view;
    private FoodItem[] foodItems;
    private Random random = new Random();
    private static boolean AxFood = true;
    private static boolean Scan = true;
    private static boolean Arla = true;
    Semaphore mutex;
    Semaphore semaphore;

    String producing = "Producing";
    String idle = "Idle";
    String stop = "Stop";


    private Thread thread;

    public Producer(FoodItem[] foodList, Buffer<FoodItem> foodItemBuffer, GUISemaphore view) {
        this.foodItemBuffer = foodItemBuffer;
        this.foodItems = foodList;
        this.view = view;
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
        while (true) {
        System.out.println(Thread.currentThread().getName() + " Thread.");
            try {
                Thread.sleep(1000);
                semaphore = new Semaphore(4);
                mutex = new Semaphore(1);
                semaphore.acquire();
                mutex.acquire();

                switch (Thread.currentThread().getName()) {
                    case "AxFood":
                        if (!AxFood) {
                            produce(Thread.currentThread().getName());
                            view.setLblStatusAxFood(producing);
                        } else {
                            view.setLblStatusAxFood(stop);
                        }
                        break;

                    case "Scan":
                        if (!Scan) {
                            produce(Thread.currentThread().getName());
                            view.setLblStatusScan(producing);
                        } else {
                            view.setLblStatusScan(stop);
                        }
                        break;

                    case "Arla":
                        if (!Arla) {
                            produce(Thread.currentThread().getName());
                            view.setLblStatusArla(producing);
                        } else {
                            view.setLblStatusArla(stop);
                        }
                        break;
                }


                semaphore.release();
                mutex.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    private void produce(String producerName) {
        System.out.println("-------------------------\n" +producerName +" Producing...");
        try {
            int pickTarget = random.nextInt(10);
            foodItemBuffer.put(foodItems[pickTarget]);
            System.out.println(producerName + " produced: " +foodItems[pickTarget].getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
