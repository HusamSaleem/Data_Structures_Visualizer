package driver;

import abstractClasses.Heap;
import dataStructures.*;
import dataStructuresInterfaces.LinkedList;
import graphics.GraphicsController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Displays several data structures on screen to help new beginners learning data structures.
 * Version 1.0
 *
 * @Author: Husam Saleem
 */
public class Main extends Application {
    public static final int CANVAS_WIDTH = 1000;
    public static final int CANVAS_HEIGHT = 620;
    public static BorderPane rootLayout = null;
    public static Canvas canvas = null;
    public static GraphicsContext gc = null;
    public static GraphicsController graphicsController;
    /**
     * Data Structures
     **/
    public static LinkedList<Integer> singlyLinkedList;
    public static LinkedList<Integer> doublyLinkedList;
    public static Stack<Integer> stack;
    public static Queue<Integer> queue;
    public static BinarySearchTree<Integer> bstTree;
    public static AVLTree<Integer> avlTree;
    public static BinaryTree<Integer> binaryTree;
    public static Heap<Integer> minHeap;
    public static MaxHeap<Integer> maxHeap;
    static GridPane dataStructureLayout = null;
    /**
     * CONSTANTS
     **/
    private final int MAXIMUM_LIST_SIZE = 12; // For stack/queues/linked lists
    private final int MAXIMUM_TREE_SIZE = 25; // For tree
    private final int WINDOW_WIDTH = 1280;
    private final int WINDOW_HEIGHT = 750;

    public static void main(String[] args) {
        launch(args);
    }

    private void initDataStructures() {
        singlyLinkedList = new SinglyLinkedList<Integer>(MAXIMUM_LIST_SIZE);
        doublyLinkedList = new DoublyLinkedList<Integer>(MAXIMUM_LIST_SIZE);
        stack = new Stack<Integer>(MAXIMUM_LIST_SIZE);
        queue = new Queue<Integer>(MAXIMUM_LIST_SIZE);
        bstTree = new BinarySearchTree<Integer>(MAXIMUM_TREE_SIZE);
        avlTree = new AVLTree<Integer>(MAXIMUM_TREE_SIZE + 15);
        binaryTree = new BinaryTree<Integer>(MAXIMUM_TREE_SIZE + 10);
        minHeap = new MinHeap<Integer>(MAXIMUM_TREE_SIZE + 10);
        maxHeap = new MaxHeap<Integer>(MAXIMUM_TREE_SIZE + 10);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        initDataStructures();

        rootLayout = new BorderPane();
        dataStructureLayout = new GridPane();

        canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        gc = canvas.getGraphicsContext2D();

        primaryStage.setTitle("Data Structure Visualizer");
        primaryStage.setScene(new Scene(rootLayout, WINDOW_WIDTH, WINDOW_HEIGHT));

        rootLayout.setCenter(canvas);

        graphicsController = new GraphicsController();
        primaryStage.show();
    }

}

