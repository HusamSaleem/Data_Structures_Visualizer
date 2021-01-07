package graphics;

import DataStructures.Stack;
import Driver.Main;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * Displays a Stack structure and the GUI options as well
 *
 * @Author: Husam Saleem
 */
public class StackViewer<E> {
    private final int HOR_GAP = 50;
    Stack<E> stack;
    GraphicsController graphicsController;
    private String name;

    public StackViewer(String name, Stack<E> stack, GraphicsController graphicsController) {
        this.name = name;
        this.stack = stack;
        this.graphicsController = graphicsController;
    }

    public void displayStack() {
        drawStackOptions();
        drawStack();
    }

    private void drawStack() {
        graphicsController.clearCanvas();
        drawStack(new Stack<E>(stack), 0, Main.CANVAS_HEIGHT / 2, HOR_GAP);
    }

    private void drawStack(Stack<E> copy, int x, int y, int hgap) {
        int index = 0;

        while (!copy.isEmpty()) {
            E poppedData = copy.pop();
            Main.gc.drawImage(graphicsController.createRectangleNumber(poppedData), x, y);
            Main.gc.fillText(index + "", x, y - 10);

            x += hgap;
            index++;
        }
    }

    /**
     * Displays Stack options
     * Messy because of the GUI stuff
     * TODO: Make the GUI look nicer and make this function cleaner :)
     */
    private void drawStackOptions() {
        BorderPane pane = new BorderPane(); // Root for everything else
        HBox pane1 = new HBox(); // Just for the name...
        HBox pane2 = new HBox(); // For inserts and deletion of nodes

        Text dataStructureName = new Text(this.name);
        dataStructureName.setStyle("-fx-font: 24 arial;");

        Button goBack = new Button("Go Back");
        goBack.setOnAction(e -> {
            graphicsController.showVisualizationOptions();
        });

        Button clear = new Button("Clear");
        clear.setOnAction(e -> {
            stack.clear();
            drawStack();
        });

        pane1.getChildren().add(dataStructureName);
        pane1.setAlignment(Pos.CENTER);

        pane2.getChildren().addAll(goBack, clear, setUpInsertionOptions(), setUpDeletionOptions());
        pane2.setAlignment(Pos.TOP_CENTER);
        pane2.setSpacing(5);

        pane.setCenter(pane1);
        pane.setBottom(pane2);

        Main.rootLayout.setTop(pane);
    }

    private HBox setUpInsertionOptions() {
        HBox pane = new HBox();

        TextField insertElementField = new TextField();
        insertElementField.setPromptText("\tElement");

        Button insertBtn = new Button("Push");
        insertBtn.setOnAction(event -> {
            if (stack.getSize() == stack.getCapacity()) {
                System.out.println("Reached maximum stack size");
                graphicsController.log("Reached maximum stack size");
                return;
            }

            try {
                stack.push((E) Integer.valueOf(insertElementField.getText()));
                insertElementField.setText("");
                drawStack();
                return;
            } catch (NumberFormatException e) {
                System.out.println("Only integers!");
            }
        });

        pane.getChildren().addAll(insertElementField, insertBtn);
        return pane;
    }

    private HBox setUpDeletionOptions() {
        HBox pane = new HBox();

        Button deleteBtn = new Button("Pop");
        deleteBtn.setOnAction(event -> {
            if (stack.isEmpty()) {
                graphicsController.log("Stack is empty");
                return;
            }

            try {
                stack.pop();
                drawStack();
            } catch (NumberFormatException e) {
                System.out.println("Only integers!");
            }
        });

        pane.getChildren().add(deleteBtn);
        return pane;
    }
}
