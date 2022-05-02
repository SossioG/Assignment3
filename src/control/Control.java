package control;

import model.FoodManager;
import view.GUISemaphore;

public class Control {
    private GUISemaphore view;
    private FoodManager foodManager;

    public Control() {
        view = new GUISemaphore(this);
        foodManager = new FoodManager(this, view);
    }
}
