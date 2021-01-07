package DataStructuresInterfaces;

public interface StackInterface<E> {
    public E pop(); // Returns the element from the top of the stock

    public void push(E data); // Pushes into the stack

    public E peek(); // Returns the element at the top of the stack without removing

    public String toString();

    public boolean isEmpty();

    public int getSize();

    public int getCapacity();
}
