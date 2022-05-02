package model;

import java.util.LinkedList;

public class Buffer<T> {
    private LinkedList<T> buffer = new LinkedList<T>();

    // producer
    public synchronized void put(T obj) {
        // produce data
        buffer.addLast(obj);
        notifyAll();
    }

    // consumer
    public synchronized T get() throws InterruptedException {
        // så länge lagret är tom, vänta
        while(buffer.isEmpty()) {
            wait();
        }
        // consume ett objekt (hämta objekt)
        return buffer.removeFirst();
    }

    public int size() {
        return buffer.size();
    }
}
