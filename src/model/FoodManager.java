package model;

import control.Control;
import view.GUISemaphore;

public class FoodManager implements FoodProducer{
    private Control control;
    private GUISemaphore view;
    private FoodItem[] foodItems;
    private int size = 5;
    private int currentIndex = -1;
    private Producer producer;
    private Buffer<FoodItem> foodItemBuffer = new Buffer<>();
    private Buffer<FoodProducer> foodProducerBuffer = new Buffer<>();
    private model.Buffer storage;
    private Consumer truck;

    public FoodManager(Control control, GUISemaphore view) {
        initFoodItems();
        this.control = control;
        this.view = view;
        producer = new Producer(foodProducerBuffer, foodItemBuffer);
        producer.startProducer();
    }

    private void initFoodItems() {
        foodItems = new FoodItem[size];
        foodItems[0] = new FoodItem(1.1,0.5,"Milk");
        foodItems[1] = new FoodItem(0.6,0.1,"Cream");
        foodItems[2] = new FoodItem(1.1,0.4,"Yoghurt");
        foodItems[3] = new FoodItem(1.2,0.3,"Milkshake");
        foodItems[4] = new FoodItem(1.3,0.2,"Apple");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public FoodItem nextFood() {
        System.out.println("next food called");
        if(size()==0)
            return null;
        currentIndex = (currentIndex+1) % foodItems.length;
        return foodItems[currentIndex];
    }
}
