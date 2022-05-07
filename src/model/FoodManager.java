package model;

import control.Control;
import view.GUISemaphore;

import java.util.Random;

public class FoodManager{
    private Control control;
    private GUISemaphore view;
    private Producer producer;

    private FoodItem[] foodItems;
    private final String[] ArrayProduct = {
            "Milk",
            "Loi",
            "Beer",
            "Tomato",
            "Bif",
    };
    private Buffer<FoodItem> foodItemBuffer = new Buffer<>();

    public FoodManager(Control control, GUISemaphore view) {
        this.control = control;
        this.view = view;
        initFoodItems();

        Producer producer1 = new Producer(foodItems, foodItemBuffer, view);
        producer1.setName("Scan");

        Producer producer2 = new Producer(foodItems, foodItemBuffer, view);
        producer2.setName("Arla");

        Producer producer3 = new Producer(foodItems, foodItemBuffer, view);
        producer3.setName("AxFood");


        Consumer consumer1 = new Consumer(foodItemBuffer, view);
        consumer1.setName("ICA");
        consumer1.start();

        Consumer consumer2 = new Consumer(foodItemBuffer, view);
        consumer2.setName("COOP");
        consumer2.start();

        Consumer consumer3 = new Consumer(foodItemBuffer, view);
        consumer3.setName("CITY GROSS");
        consumer3.start();


    }

    private void initFoodItems() {
        foodItems = new FoodItem[]{
            new FoodItem(1.2, 0.5, "Milk"),
            new FoodItem(1.3, 0.9, "Apple"),
            new FoodItem(3.9, 1.0, "Yoghurt"),
            new FoodItem(2.9, 0.2, "Leachate"),
            new FoodItem(1.3, 2.44, "Potato"),
            new FoodItem(4.0, 2.12, "Chis"),
            new FoodItem(6.1, 3.4, "Strawberry"),
            new FoodItem(3.0, 0.02, "Bread"),
            new FoodItem(8.2, 1.5, "Tomato"),
            new FoodItem(1.5, 3.0, "Cream")};
    }


}
