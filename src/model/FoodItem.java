package model;

public class FoodItem {
    private double weight;
    private double volume;
    private String name;
    private int currentIndex = -1;

    public FoodItem(double weight, double volume, String name) {
        this.weight = weight;
        this.volume = volume;
        this.name = name;
    }


    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // To string
    public String toString() {
        String txt =
                "Weight: " + Double.toString(weight) + "\n" +
                "Volume: " + Double.toString(volume) + "\n" +
                "Name: " + name + " ";
        return txt;
    }

}
