package control;

import model.Consumer;
import model.FoodItem;
import model.Producer;
import view.GUISemaphore;

public class Control {
    private GUISemaphore view;

    private Producer factory;
    private FoodItem food;
    private model.Buffer storage;
    private Consumer truck;

    public Control() {
        view = new GUISemaphore(this);
    }
}
