package model;

import view.GUISemaphore;

import java.util.LinkedList;

public class Buffer<T> {
    private int size = 4;
    private final LinkedList<T> buffer = new LinkedList<T>();

    // producer


    public synchronized void put(T obj) throws InterruptedException {

        // produce data
        while (buffer.size() == size){
            System.out.println(buffer.size());
            System.out.println("Buffer is full..");
            wait();
        }
        buffer.addLast(obj);
        notifyAll();
    }

    // consumer
    public synchronized T get() throws InterruptedException {
        while(buffer.isEmpty()) {
            System.out.println("Buffer is empty..");
            wait();
        }
        notifyAll();
        return buffer.removeFirst();
    }

    public int size() {
        return size;
    }
}
