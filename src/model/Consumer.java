package model;

import view.GUISemaphore;

import java.util.concurrent.Semaphore;

public class Consumer extends Thread {

    private Buffer<FoodItem> foodItemBuffer;
    private GUISemaphore view;
    Semaphore mutex;
    Semaphore semaphore;
    FoodItem foodItem;

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
        start();
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

    @Override
    public void run() {
        while(!Thread.interrupted()) {
            try {
                Thread.sleep(1000);
                semaphore = new Semaphore(4);
                mutex = new Semaphore(1);
                semaphore.acquire();
                mutex.acquire();
                System.out.println(Thread.currentThread().getName() + " Customer.");
            } catch (InterruptedException e) {
                break;
            }
            switch(Thread.currentThread().getName()) {
                case "ICA":
                    if(!Ica) {
                        FoodItem foodItem = null;
                        try {
                            foodItem = foodItemBuffer.get();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        System.out.println("Ica run");
                        displayInfoICA(foodItem, Thread.currentThread().getName());
                    } else {
                        view.setLblIcaStatus(idle);
                    }
                    break;

                case "COOP":
                    if(!Coop) {
                        FoodItem foodItem = null;
                        try {
                            foodItem = foodItemBuffer.get();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Coop run");
                        displayInfoCOOP(foodItem, Thread.currentThread().getName());

                    } else {
                        view.setLblCoopStatus(idle);
                    }
                    break;

                case "CITY GROSS":
                    if(!CG) {
                        FoodItem foodItem = null;
                        try {
                            foodItem = foodItemBuffer.get();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("CG run");
                        displayInfoCG(foodItem, Thread.currentThread().getName());

                    } else {
                        view.setLblCGStatus(idle);
                    }
                    break;
            }

            semaphore.release();
            mutex.release();
        }
    }

    private void displayInfoCOOP(FoodItem foodItem, String name) {
        System.out.println("-------------------------\n" + name + " Consuming...");

        view.setLblCoopStatus(consuming);
        view.setLstCoop(foodItem.getName());

        //view.setLblCoopItems();
        counterCoopWeight = counterCoopWeight + foodItem.getWeight();
        view.setLblCoopWeight(Double.toString(counterCoopWeight));

        counterCoopVolume = counterCoopVolume + foodItem.getVolume();
        view.setLblCoopVolume(Double.toString(counterCoopVolume));

        System.out.println(name + " consumed: ");

    }
    private void displayInfoICA(FoodItem foodItem, String name) {
        System.out.println("-------------------------\n" + name + " Consuming...");

        view.setLblIcaStatus(consuming);

        view.setLstIca(foodItem.getName());

        counterIcaWeight = counterIcaWeight + foodItem.getWeight();
        view.setLblIcaWeight(Double.toString(counterIcaWeight));

        counterIcaVolume = counterIcaVolume + foodItem.getVolume();
        view.setLblIcaVolume(Double.toString(counterIcaVolume));

        System.out.println(name + " consumed: ");

    }
    private void displayInfoCG(FoodItem foodItem, String name) {
        System.out.println("-------------------------\n" + name + " Consuming...");

        view.setLblCGStatus(consuming);
        view.setLstCG(foodItem.getName());

        counterCGWeight = counterCGWeight + foodItem.getWeight();
        view.setLblCGWeight(Double.toString(counterCGWeight));

        counterCGVolume = counterCGVolume + foodItem.getVolume();
        view.setLblCGVolume(Double.toString(counterCGVolume));

        System.out.println(name + " consumed: ");

    }


}
