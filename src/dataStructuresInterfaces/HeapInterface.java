package dataStructuresInterfaces;

public interface HeapInterface<E> {
    public void insert(E data);

    public E extractNum();

    public E peek();

    public <E extends Comparable<E>> void heapifyUp();

    public <E extends Comparable<E>> void heapifyDown();

    /**
     * Utility Functions
     **/
    public int getSize();

    public int getCapacity();

    public boolean isEmpty();

    public E getParent(int index);

    public E getRightChild(int index);

    public E getLeftChild(int index);

    public boolean isRightNull(int index);

    public boolean isLeftNull(int index);
}
