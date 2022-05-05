package model;

public class Producer extends Thread{

    private Buffer<FoodItem> foodItemBuffer;
    private Buffer<FoodProducer> foodProducerBuffer;

    private Thread thread;

    public Producer(Buffer<FoodProducer> foodProducerBuffer, Buffer<FoodItem> foodItemBuffer) {
        this.foodItemBuffer = foodItemBuffer;
        this.foodProducerBuffer = foodProducerBuffer;

    }

    public void startProducer() {
        System.out.println("Starting... ");
        start();
    }

    @Override
    public void run() {
        System.out.println("Producer runs!");
        FoodProducer foodProducer;

        while(!Thread.interrupted()) {
            try {
                Thread.sleep(1000);
                System.out.println("Thread is running...");
                System.out.println(foodProducerBuffer.get().size() + " size");
                foodProducer = foodProducerBuffer.get();
                for (int r = 0; r < foodProducer.size(); r++){ // antal gånger
                    System.out.println("food producing: " + foodProducer.nextFood().getName());
                    foodItemBuffer.put(foodProducer.nextFood()); // producera maten en efter en
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }

    }

}
