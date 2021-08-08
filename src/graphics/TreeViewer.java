package graphics;

import Nodes.TreeNode;
import dataStructures.AVLTree;
import dataStructures.BinarySearchTree;
import dataStructures.BinaryTree;
import driver.Main;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * Displays a Tree structure and the GUI options as well
 * Currently can only work for binary type trees with left and right childs
 *
 * @Author: Husam Saleem
 */
public class TreeViewer<E extends Comparable<E>> {
    private final int VERT_GAP = 50; // Vertical gap per node
    BinaryTree<E> tree;
    GraphicsController graphicsController;
    private String name;
    private boolean isHeap;
    private boolean isMinHeap;

    public TreeViewer(String name, GraphicsController graphicsController) {
        this.name = name;
        this.graphicsController = graphicsController;
    }

    public TreeViewer(String name, BinaryTree<E> tree, GraphicsController graphicsController) {
        this(name, graphicsController);
        this.tree = tree;
    }

    public TreeViewer(String name, AVLTree<E> tree, GraphicsController graphicsController) {
        this(name, graphicsController);
        this.tree = tree;
    }

    public TreeViewer(String name, BinarySearchTree<E> tree, GraphicsController graphicsController) {
        this(name, graphicsController);
        this.tree = tree;
    }

    public TreeViewer(String name, BinaryTree<E> tree, GraphicsController graphicsController, boolean isHeap, boolean isMinHeap) {
        this(name, graphicsController);
        this.tree = tree;
        this.isHeap = isHeap;
        this.isMinHeap = isMinHeap;
    }

    public void displayTree() {
        drawTreeOptions();
        drawTree();
    }

    private void drawTree() {
        graphicsController.clearCanvas();
        drawTree(tree.getRoot(), Main.CANVAS_WIDTH / 2, 50, Main.CANVAS_WIDTH / 4);
    }

    /**
     * Recursively draws a binary search tree
     */
    private <E> void drawTree(TreeNode<E> root, double x, double y, double hGap) {
        if (root != null) {
            if (root.left != null) {
                Main.gc.strokeLine(x - hGap, y + VERT_GAP, x, y); // Draw line to left node
            }
            if (root.right != null) {
                Main.gc.strokeLine(x + hGap, y + VERT_GAP, x, y); // Draw line to right node
            }

            drawTree(root.left, x - hGap, y + VERT_GAP, hGap / 2);
            Main.gc.drawImage(graphicsController.createCircledNumber((Integer) root.data), x - 15, y - 12); // The -15, and -12 are just offsets for the circles to align with the lines
            drawTree(root.right, x + hGap, y + VERT_GAP, hGap / 2);
        }
    }

    public <E> void drawTreeOptions() {
        BorderPane pane = new BorderPane(); // Root for everything else
        HBox pane1 = new HBox(); // Just for the name...
        HBox pane2 = new HBox(); // For inserts and deletion of nodes

        Text dataStructureName = new Text(this.name);
        dataStructureName.setStyle("-fx-font: 20 arial;");

        Button goBack = new Button("Go Back");
        goBack.setOnAction(e -> {
            graphicsController.showVisualizationOptions();
        });

        Button clear = new Button("Clear");
        clear.setOnAction(e -> {
            if (this.isHeap)
                if (this.isMinHeap)
                    Main.minHeap.clear();
                else
                    Main.maxHeap.clear();
            tree.clear();
            drawTree();
        });

        pane1.getChildren().add(dataStructureName);
        pane1.setAlignment(Pos.CENTER);

        pane2.getChildren().addAll(goBack, clear, setUpInsertionOptions(), setUpDeletionOptions());
        pane2.setAlignment(Pos.TOP_CENTER);
        pane2.setSpacing(10);

        pane.setCenter(pane1);
        pane.setBottom(pane2);

        Main.rootLayout.setTop(pane);
    }

    private HBox setUpInsertionOptions() {
        HBox pane = new HBox(); // For inserts and deletion of nodes

        /** Insertion option **/
        TextField insertElementField = new TextField();
        insertElementField.setPromptText("Element");
        insertElementField.autosize();

        Button insertBtn = new Button("Insert");
        insertBtn.setOnAction(event -> {
            if (this.tree.getSize() == this.tree.getCapacity()) {
                System.out.println("Reached maximum element size");
                System.out.println(this.tree.getSize());
                graphicsController.log("Reached maximum tree size");
                return;
            }

            try {
                if (this.isHeap) {
                    this.tree.clear();

                    if (this.isMinHeap) {
                        Main.minHeap.insert((Integer) Integer.valueOf(insertElementField.getText()));
                        this.tree = (BinaryTree<E>) Main.minHeap.convertToTree();
                    } else {
                        Main.maxHeap.insert((Integer) Integer.valueOf(insertElementField.getText()));
                        this.tree = (BinaryTree<E>) Main.maxHeap.convertToTree();
                    }
                } else {
                    this.tree.insert((E) Integer.valueOf(insertElementField.getText()));
                }
                insertElementField.setText("");
                drawTree();
                return;
            } catch (NumberFormatException e) {
                System.out.println("Only integers!");
            }
        });

        pane.getChildren().addAll(insertElementField, insertBtn);
        return pane;
    }

    private HBox setUpDeletionOptions() {
        HBox pane = new HBox(); // For inserts and deletion of nodes

        /** Deleltion option **/
        TextField deleteElementField = new TextField();
        deleteElementField.setPromptText("Element");
        deleteElementField.autosize();

        Button deleteBtn = new Button("Delete");
        deleteBtn.autosize();
        deleteBtn.setOnAction(event -> {
            if (this.tree.getSize() == 0) {
                graphicsController.log("Tree is empty");
                return;
            }

            try {

                if (this.isHeap) {
                    this.tree.clear();

                    if (this.isMinHeap) {
                        Main.minHeap.extractNum();
                        this.tree = (BinaryTree<E>) Main.minHeap.convertToTree();
                    } else {
                        Main.maxHeap.extractNum();
                        this.tree = (BinaryTree<E>) Main.maxHeap.convertToTree();
                    }
                } else {
                    this.tree.delete((E) Integer.valueOf(deleteElementField.getText()));
                }
                deleteElementField.setText("");
                drawTree();
            } catch (NumberFormatException e) {
                System.out.println("Only integers!");
            }
        });

        if (this.isHeap)
            pane.getChildren().addAll(deleteBtn);
        else
            pane.getChildren().addAll(deleteElementField, deleteBtn);
        return pane;
    }
}
