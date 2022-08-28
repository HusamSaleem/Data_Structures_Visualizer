package dataStructures;

public class DataStructureManager {
    private static final int MAXIMUM_LIST_SIZE = 12; // For stack/queues/linked lists
    private static final int MAXIMUM_TREE_SIZE = 35; // For tree
    public static LinkedList<Integer> singlyLinkedList;
    public static LinkedList<Integer> doublyLinkedList;
    public static Stack<Integer> stack;
    public static Queue<Integer> queue;
    public static BinarySearchTree<Integer> bstTree;
    public static AVLTree<Integer> avlTree;
    public static BinaryTree<Integer> binaryTree;
    public static Heap<Integer> minHeap;
    public static MaxHeap<Integer> maxHeap;

    public static void initDataStructures() {
        singlyLinkedList = new SinglyLinkedList<Integer>(MAXIMUM_LIST_SIZE);
        doublyLinkedList = new DoublyLinkedList<Integer>(MAXIMUM_LIST_SIZE);
        stack = new Stack<Integer>(MAXIMUM_LIST_SIZE);
        queue = new Queue<Integer>(MAXIMUM_LIST_SIZE);
        bstTree = new BinarySearchTree<Integer>(MAXIMUM_TREE_SIZE);
        avlTree = new AVLTree<Integer>(MAXIMUM_TREE_SIZE);
        binaryTree = new BinaryTree<Integer>(MAXIMUM_TREE_SIZE);
        minHeap = new MinHeap<Integer>(MAXIMUM_TREE_SIZE);
        maxHeap = new MaxHeap<Integer>(MAXIMUM_TREE_SIZE);
    }
}
