package graphics;

import dataStructures.DataStructureManager;
import javafx.geometry.Pos;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Transform;

import static graphics.Graphics.CANVAS_HEIGHT;
import static graphics.Graphics.CANVAS_WIDTH;

/**
 * This controls the GUI
 *
 * @Author: Husam Saleem
 */
public class GraphicsController {
    LinkedListViewer<Integer> linkedListViewer;
    TreeViewer<Integer> tree;
    StackViewer<Integer> stackViewer;
    QueueViewer<Integer> queueViewer;

    public GraphicsController() {
        showVisualizationOptions();
    }

    public void showVisualizationOptions() {
        clearScreen();
        fullClearCanvas();

        HBox hBox = new HBox();

        Button singlyLinkedListBtn = new Button("Singly Linked List");
        singlyLinkedListBtn.autosize();
        singlyLinkedListBtn.setOnAction(e -> {
            clearScreen();
            linkedListViewer = new LinkedListViewer<Integer>("Singly Linked List (Integers)", DataStructureManager.singlyLinkedList, this);
            linkedListViewer.displayLinkedlist();
        });

        Button doublyLinkedListButton = new Button("Doubly Linked List");
        doublyLinkedListButton.autosize();
        doublyLinkedListButton.setOnAction(e -> {
            clearScreen();
            linkedListViewer = new LinkedListViewer<Integer>("Doubly Linked List (Integers)", DataStructureManager.doublyLinkedList, this);
            linkedListViewer.displayLinkedlist();
        });

        Button binaryTreeBtn = new Button("Binary Tree");
        binaryTreeBtn.autosize();
        binaryTreeBtn.setOnAction(e -> {
            clearScreen();
            tree = new TreeViewer<Integer>("Binary Tree (Integers)", DataStructureManager.binaryTree, this);
            tree.displayTree();
        });

        Button bstTreeBtn = new Button("Binary Search Tree");
        bstTreeBtn.autosize();
        bstTreeBtn.setOnAction(e -> {
            clearScreen();
            tree = new TreeViewer<Integer>("Binary Search Tree (Integers)", DataStructureManager.bstTree, this);
            tree.displayTree();
        });

        Button avlTreeBtn = new Button("AVL Tree");
        avlTreeBtn.autosize();
        avlTreeBtn.setOnAction(e -> {
            clearScreen();
            tree = new TreeViewer<Integer>("AVL Tree (Integers)", DataStructureManager.avlTree, this);
            tree.displayTree();
        });

        Button minHeapBtn = new Button("Min Heap");
        minHeapBtn.autosize();
        minHeapBtn.setOnAction(e -> {
            clearScreen();
            tree = new TreeViewer<Integer>("Min Heap (Integers)", DataStructureManager.minHeap.convertToTree(), this, true, true);
            tree.displayTree();
        });

        Button maxHeapBtn = new Button("Max Heap");
        maxHeapBtn.autosize();
        maxHeapBtn.setOnAction(e -> {
            clearScreen();
            tree = new TreeViewer<Integer>("Max Heap (Integers)", DataStructureManager.minHeap.convertToTree(), this, true, false);
            tree.displayTree();
        });

        Button stackBtn = new Button("Stack");
        stackBtn.autosize();
        stackBtn.setOnAction(e -> {
            clearScreen();
            stackViewer = new StackViewer<Integer>("Stack (Integers)", DataStructureManager.stack, this);
            stackViewer.displayStack();
        });

        Button queueBtn = new Button("Queue");
        queueBtn.autosize();
        queueBtn.setOnAction(e -> {
            clearScreen();
            queueViewer = new QueueViewer<Integer>("Queue (Integers)", DataStructureManager.queue, this);
            queueViewer.displayQueue();
        });

        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(singlyLinkedListBtn, doublyLinkedListButton, binaryTreeBtn, bstTreeBtn, avlTreeBtn, minHeapBtn, maxHeapBtn, stackBtn, queueBtn);
        hBox.setSpacing(15);
        Graphics.rootLayout.setTop(hBox);
    }

    public void fullClearCanvas() {
        Graphics.gc.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
    }

    /**
     * Places text on canvas
     */
    public void log(String txt) {
        Graphics.gc.setFill(Color.RED);
        Graphics.gc.setFont(new Font("Arial Black", 18));
        Graphics.gc.fillText(txt, CANVAS_WIDTH / 3, CANVAS_HEIGHT - 25);
        Graphics.gc.setFont(new Font("Arial Black", 12));
        Graphics.gc.setFill(Color.BLACK);
    }

    public void clearCanvas() {
        Graphics.gc.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT - 50);
        Graphics.gc.setFont(new Font("Arial Black", 12));
    }

    public void clearScreen() {
        clearCanvas();
        Graphics.rootLayout.getChildren().clear();
        Graphics.rootLayout.setCenter(Graphics.canvas);
    }

    public <E> WritableImage createCircledNumber(E data) {
        StackPane sPane = new StackPane();
        sPane.setPrefSize(30, 30); // 30 px by 30 px

        Circle c = new Circle(30 / 2.0);
        c.setStroke(Color.GREEN);
        c.setFill(Color.WHITE);
        c.setStrokeWidth(4);
        sPane.getChildren().add(c);

        Text txtNum = new Text(data + "");
        txtNum.setTextAlignment(TextAlignment.CENTER);
        sPane.getChildren().add(txtNum);
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        return sPane.snapshot(parameters, null);
    }

    public <E> WritableImage createRectangleNumber(E data) {
        StackPane sPane = new StackPane();
        sPane.setPrefSize(30, 30); // 30 px by 30 px

        Rectangle r = new Rectangle(30, 30);
        r.setStroke(Color.GREEN);
        r.setFill(Color.WHITE);
        r.setStrokeWidth(4);
        sPane.getChildren().add(r);

        Text txtNum = new Text(data + "");
        txtNum.setTextAlignment(TextAlignment.CENTER);
        sPane.getChildren().add(txtNum);
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        return sPane.snapshot(parameters, null);
    }

    public void drawArrow(GraphicsContext gc, double x1, double y1, double x2, double y2) {
        double dx = x2 - x1, dy = y2 - y1;
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx * dx + dy * dy);

        Transform transform = Transform.translate(x1, y1);
        transform = transform.createConcatenation(Transform.rotate(Math.toDegrees(angle), 0, 0));
        Transform temp = gc.getTransform();
        gc.setTransform(new Affine(transform));

        gc.strokeLine(0, 0, len, 0);
        gc.fillPolygon(new double[]{len, len - 8, len - 8, len}, new double[]{0, -8, 8, 0}, 4);
        gc.setTransform(new Affine(temp));
    }
}
