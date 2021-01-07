package graphics;

import Driver.Main;
import Nodes.ListNode;
import abstractClasses.LinkedList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * Linked List viewer to display a linked list structure whether its doubly or singly and the GUI options as well.
 * DOES NOT SUPPORT CIRCULAR LINKED LIST
 *
 * @Author: Husam Saleem
 */
public class LinkedListViewer<E> {
    private final int HOR_GAP = 75;
    public LinkedList<E> list;
    GraphicsController graphicsController;
    String name;

    public LinkedListViewer(String name, LinkedList<E> list, GraphicsController graphicsController) {
        this.name = name;
        this.list = list;
        this.graphicsController = graphicsController;
    }

    /**
     *
     */
    public void displayLinkedlist() {
        displaySinglyLinkedListOptions();
        drawLinkedList();
    }

    public <E> void drawLinkedList(ListNode<E> head, double x, double y, double hgap, int index) {
        if (head != null) {
            if (this.name.equals("Doubly Linked List (Integers)")) {
                graphicsController.drawArrow(Main.gc, x, y + hgap / 8, x + hgap, y + hgap / 8);
                graphicsController.drawArrow(Main.gc, x + hgap, y + hgap / 3, x + 35, y + hgap / 3);
            } else {
                graphicsController.drawArrow(Main.gc, x, y + hgap / 4, x + hgap, y + hgap / 4);
            }

            Main.gc.drawImage(graphicsController.createRectangleNumber(head.data), x, y);
            Main.gc.fillText(index + "", x, y - 10);
            drawLinkedList(head.next, x + hgap, y, hgap, index + 1);
        }
    }

    public <E> void drawLinkedList() {
        graphicsController.clearCanvas();
        drawLinkedList(list.getHead(), 0, Main.CANVAS_HEIGHT / 2, HOR_GAP, 0);
    }

    /**
     * Displays Linked List options
     * Messy because of the GUI stuff
     * TODO: Make the GUI look nicer and make this function cleaner :)
     */
    private void displaySinglyLinkedListOptions() {
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
            list.clear();
            drawLinkedList();
        });

        pane1.getChildren().add(dataStructureName);
        pane1.setAlignment(Pos.CENTER);

        pane2.getChildren().addAll(goBack, clear, setUpInsertionOptions(), setUpDeletionOptions(), setUpSwapOptions());
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

        TextField insertElementPos = new TextField();
        insertElementPos.setPromptText("Index (Optional)");
        insertElementPos.autosize();

        Button insertBtn = new Button("Insert");
        insertBtn.setOnAction(event -> {
            if (this.list.getSize() == this.list.getCapacity()) {
                System.out.println("Reached maximum element size");
                graphicsController.log("Reached maximum element size");
                return;
            }

            try {
                this.list.insert((E) Integer.valueOf(insertElementField.getText()), Integer.parseInt(insertElementPos.getText()));
                insertElementField.setText("");
                insertElementPos.setText("");
                drawLinkedList();
                return;
            } catch (NumberFormatException e) {
                System.out.println("Only integers!");
            }

            try {
                this.list.insert((E) Integer.valueOf(insertElementField.getText()));
                insertElementField.setText("");
                drawLinkedList();
                return;
            } catch (NumberFormatException e) {
                System.out.println("Only integers!");
            }

        });

        pane.getChildren().addAll(insertElementField, insertElementPos, insertBtn);
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
            if (this.list.isEmpty()) {
                graphicsController.log("List is empty");
                return;
            }

            try {
                this.list.delete(Integer.parseInt(deleteElementField.getText()));
                deleteElementField.setText("");
                drawLinkedList();
            } catch (NumberFormatException e) {
                System.out.println("Only integers!");
            }
        });

        pane.getChildren().addAll(deleteElementField, deleteBtn);
        return pane;
    }

    private HBox setUpSwapOptions() {
        HBox pane = new HBox();

        TextField swapPos1 = new TextField();
        swapPos1.setPromptText("Index 1");
        swapPos1.autosize();

        TextField swapPos2 = new TextField();
        swapPos2.setPromptText("Index 2");
        swapPos2.autosize();

        Button swapBtn = new Button("Swap Positions");
        swapBtn.setOnAction(event -> {
            if (this.list.isEmpty())
                return;

            try {
                this.list.swap(Integer.parseInt(swapPos1.getText()), Integer.parseInt(swapPos2.getText()));
                swapPos1.setText("");
                swapPos2.setText("");
                drawLinkedList();
            } catch (NumberFormatException e) {
                System.out.println("Only integers!");
            }
        });

        pane.getChildren().addAll(swapPos1, swapPos2, swapBtn);
        return pane;
    }

    public String getName() {
        return this.name;
    }
}
