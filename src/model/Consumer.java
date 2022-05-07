package model;

import view.GUISemaphore;

import java.util.concurrent.Semaphore;

public class Consumer extends Thread {

    private Buffer<FoodItem> foodItemBuffer;
    private GUISemaphore view;
    Semaphore mutex;
    Semaphore semaphore;

    String consuming = "Consuming";
    String idle = "Idle";
    String stop = "Stop";

    double counterIcaWeight = 0.0;
    double counterIcaVolume = 0.0;

    double counterCoopWeight = 0.0;
    double counterCoopVolume = 0.0;

    double counterCGWeight = 0.0;
    double counterCGVolume = 0.0;

    private static boolean Ica = true;
    private static boolean Coop = true;
    private static boolean CG = true;

    public Consumer(Buffer<FoodItem> foodItemBuffer, GUISemaphore view) {
        this.foodItemBuffer = foodItemBuffer;
        this.view = view;
    }

    public static void startIca() {
        Ica = false;
    }
    public static void stopIca() {
        Ica = true;
    }

    public static void startCoop() {
        Coop = false;
    }
    public static void stopCoop() {
        Coop = true;
    }

    public static void startCG() {
        CG = false;
    }
    public static void stopCG() {
        CG = true;
    }

    public void run() {
        System.out.println("-------------------------\n" + Thread.currentThread().getName() + " Taking...");
        FoodItem foodItem;
        while(!Thread.interrupted()) {

            try {
                Thread.sleep(1000);
                semaphore = new Semaphore(4);
                mutex = new Semaphore(1);
                semaphore.acquire();
                mutex.acquire();

                foodItem = foodItemBuffer.get();

                // display to gui
                System.out.println(Thread.currentThread().getName() + " took: " +foodItem.getName());

            } catch (InterruptedException e) {
                break;
            }

            switch(Thread.currentThread().getName()) {
                case "ICA":
                    if(!Ica) {
                        System.out.println(Thread.currentThread().getName() + " current thread");
                        consume(Thread.currentThread().getName());
                        view.setLblIcaStatus(consuming);

                        //view.setLblIcaItems();
                        counterIcaWeight = counterIcaWeight + foodItem.getWeight();
                        view.setLblIcaWeight(Double.toString(counterIcaWeight));

                        counterIcaVolume = counterIcaVolume + foodItem.getVolume();
                        view.setLblIcaVolume(Double.toString(counterIcaVolume));

                    } else {
                        view.setLblIcaStatus(idle);

                        //view.setLblIcaWeight("-");
                        //view.setLblIcaVolume("-");
                    }
                break;

                case "COOP":
                    if(!Coop) {
                        System.out.println(Thread.currentThread().getName() + " current thread");
                        consume(Thread.currentThread().getName());
                        view.setLblCoopStatus(consuming);

                        //view.setLblCoopItems();
                        counterCoopWeight = counterCoopWeight + foodItem.getWeight();
                        view.setLblCoopWeight(Double.toString(counterCoopWeight));

                        counterCoopVolume = counterCoopVolume + foodItem.getVolume();
                        view.setLblCoopVolume(Double.toString(counterCoopVolume));
                    } else {
                        view.setLblCoopStatus(idle);

                        //view.setLblCoopWeight("-");
                        //view.setLblCoopVolume("-");
                    }
                break;

                case "CITY GROSS":
                    if(!CG) {
                        System.out.println(Thread.currentThread().getName() + " current thread");
                        consume(Thread.currentThread().getName());
                        view.setLblCGStatus(consuming);

                        //view.setLblCoopItems();
                        counterCGWeight = counterCGWeight + foodItem.getWeight();
                        view.setLblCGWeight(Double.toString(counterCGWeight));

                        counterCGVolume = counterCGVolume + foodItem.getVolume();
                        view.setLblCGVolume(Double.toString(counterCGVolume));
                    } else {
                        view.setLblCGStatus(idle);

                        //view.setLblCGWeight("-");
                        //view.setLblCGVolume("-");
                    }
                break;
            }
            semaphore.release();
            mutex.release();
        }
    }

    private void consume(String consumerName) {
        System.out.println("-------------------------\n" + consumerName + " Consuming...");
        try {
            foodItemBuffer.get();

            // display in gui
            System.out.println(consumerName + " consumed: ");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
