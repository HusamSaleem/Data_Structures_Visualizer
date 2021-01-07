package DataStructuresInterfaces;

public interface QueueInterface<E> {
    public void enqueue(E data);

    public E dequeue();

    public E peek(); // Returns the element at the top of the queue without removing

    public String toString();

    public boolean isEmpty();

    public int getSize();

    public int getCapacity();
}
