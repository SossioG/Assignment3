package model;

public class Consumer {
    private int totalNumberOfItems;
    private int totalWeight;
    private int totalVolume;

    private Buffer<Integer> buffer;

    public Consumer(int totalNumberOfItems, int totalWeight, int totalVolume) {
        this.totalNumberOfItems = totalNumberOfItems;
        this.totalWeight = totalWeight;
        this.totalVolume = totalVolume;
    }

    // Getters and setters
    public int getTotalNumberOfItems() {
        return totalNumberOfItems;
    }
    public void setTotalNumberOfItems(int totalNumberOfItems) {
        this.totalNumberOfItems = totalNumberOfItems;
    }

    public int getTotalWeight() {
        return totalWeight;
    }
    public void setTotalWeight(int totalWeight) {
        this.totalWeight = totalWeight;
    }

    public int getTotalVolume() {
        return totalVolume;
    }
    public void setTotalVolume(int totalVolume) {
        this.totalVolume = totalVolume;
    }
}
