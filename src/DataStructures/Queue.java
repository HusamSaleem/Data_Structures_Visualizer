package DataStructures;

import DataStructuresInterfaces.QueueInterface;

public class Queue<E> implements QueueInterface<E> {
    private int size;
    private int capacity;

    private int frontPtr, rearPtr;
    private E[] buffer;

    public Queue(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.buffer = (E[]) new Object[capacity];

        this.frontPtr = -1;
        this.rearPtr = -1;
    }

    public Queue(Queue<E> copy) {
        this.capacity = copy.getCapacity();
        this.size = copy.getSize();
        this.buffer = copy.buffer;

        this.frontPtr = copy.frontPtr;
        this.rearPtr = copy.rearPtr;
    }

    /**
     * O(1) run-time
     * Adds an element to the back of the queue
     */
    @Override
    public void enqueue(E data) {
        if (this.getSize() < this.getCapacity()) {
            if (frontPtr == -1) {
                frontPtr++;
                rearPtr++;
                this.buffer[rearPtr] = data;
                this.size++;

                return;
            }

            rearPtr++;
            if (rearPtr == this.getCapacity()) {
                rearPtr = 0;
            }
            this.buffer[rearPtr] = data;

            this.size++;
        }
    }

    /**
     * O(1) run-time
     * Removes the first element in the queue
     */
    @Override
    public E dequeue() {
        if (this.isEmpty())
            return null;

        E temp = this.buffer[frontPtr];

        this.frontPtr++;
        this.size--;

        if (this.isEmpty()) {
            this.frontPtr = -1;
            this.rearPtr = -1;
        } else if (this.frontPtr == this.getCapacity()) {
            this.frontPtr = 0;
        }

        return temp;
    }

    /**
     * Returns the first element in the queue if the queue isnt empty without removing it
     */
    @Override
    public E peek() {
        if (!this.isEmpty())
            return this.buffer[frontPtr];
        return null;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    public void clear() {
        this.frontPtr = -1;
        this.rearPtr = -1;
        this.size = 0;
        this.buffer = (E[]) new Object[capacity];
    }

    @Override
    public String toString() {
        String result = "";

        int i = frontPtr;
        int count = 0;
        while (count < getSize()) {
            result += this.buffer[i] + ", ";

            count++;
            i++;
            if (i == getCapacity())
                i = 0;
        }
        result += "\n";

        return result;
    }
}
