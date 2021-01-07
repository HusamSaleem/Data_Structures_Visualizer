package DataStructuresInterfaces;

public interface BSTInterface<E> {
    public void insert(E data);

    public void delete(E data);

    public boolean search(E data);

    /**
     * Printing stuff
     **/
    public void printInOrder();

    public void printPreOrder();

    public void printPostOrder();

    public void printLevelTraversal();
}
