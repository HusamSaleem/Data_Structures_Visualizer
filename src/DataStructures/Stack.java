package DataStructures;

import DataStructuresInterfaces.StackInterface;

public class Stack<E> implements StackInterface<E> {
    private int size;
    private int capacity;
    private int tailPtr;
    private E[] stackArr;

    public Stack(int capacity) {
        this.capacity = capacity;
        this.stackArr = (E[]) new Object[capacity];
        this.size = 0;
        this.tailPtr = -1;
    }

    public Stack(Stack<E> copy) {
        this.size = copy.getSize();
        this.stackArr = copy.stackArr;
        this.tailPtr = copy.tailPtr;
    }

    /**
     * O(1) run-time
     * Returns null if the stack is empty.
     * Returns the element at the top of stack if not empty
     */
    @Override
    public E pop() {
        if (this.isEmpty())
            return null;

        E temp = this.stackArr[this.tailPtr];
        this.tailPtr--;
        this.size--;
        return temp;
    }

    /**
     * O(1) run-time
     * Pushes an element to the top of the stack if there is space
     *
     * @param data
     */
    @Override
    public void push(E data) {
        if (this.getSize() < this.getCapacity()) {
            this.tailPtr++;
            this.stackArr[this.tailPtr] = data;
            this.size++;
        }
    }

    @Override
    public E peek() {
        return this.stackArr[this.tailPtr];
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
        this.size = 0;
        this.stackArr = (E[]) new Object[this.getCapacity()];
        this.tailPtr = -1;
    }

    @Override
    public String toString() {
        String result = "";

        for (int i = tailPtr; i >= 0; i--) {
            result += this.stackArr[i] + ", ";
        }

        result += "\n";
        return result;
    }
}