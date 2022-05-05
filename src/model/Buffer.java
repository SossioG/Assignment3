package model;

import java.util.LinkedList;

public class Buffer<T> {
    private int size = 4;
    private final LinkedList<T> buffer = new LinkedList<T>();

    // producer
    public synchronized void put(T obj) throws InterruptedException {
        // produce data
        if (buffer.size() == size){
            wait();
        }else
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
        return size;
    }
}
