package model;

public class Producer extends Thread{

    private Buffer<FoodItem> foodItemBuffer;
    private Buffer<FoodProducer> foodProducerBuffer;

    private Thread thread;

    public Producer(Buffer<FoodProducer> foodProducerBuffer, Buffer<FoodItem> foodItemBuffer) {
        this.foodItemBuffer = foodItemBuffer;
        this.foodProducerBuffer = foodProducerBuffer;
    }

    public void start() {
        thread = new Thread();
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("Producer runs!");

        FoodProducer foodProducer;

        while(!Thread.interrupted()) {
            try {
                foodProducer = foodProducerBuffer.get();
                for (int r = 0; r < foodProducer.size(); r++){ // antal gånger
                    foodItemBuffer.put(foodProducer.nextFood()); // producera maten en efter en
                }
            } catch (InterruptedException e) {
                break;
            }
        }

    }

}
