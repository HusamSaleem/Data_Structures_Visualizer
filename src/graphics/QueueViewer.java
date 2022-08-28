package graphics;

import dataStructures.Queue;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import static graphics.Graphics.CANVAS_HEIGHT;

/**
 * Displays a Queue structure and the GUI options as well
 *
 * @Author: Husam Saleem
 */
public class QueueViewer<E> {
    private final int HOR_GAP = 50;
    Queue<E> queue;
    GraphicsController graphicsController;
    private String name;

    public QueueViewer(String name, Queue<E> queue, GraphicsController graphicsController) {
        this.name = name;
        this.queue = queue;
        this.graphicsController = graphicsController;
    }

    public void displayQueue() {
        drawQueueOptions();
        drawQueue();
    }

    private void drawQueue() {
        graphicsController.clearCanvas();
        drawQueue(new Queue<E>(queue), 0, CANVAS_HEIGHT / 2, HOR_GAP);
    }

    private void drawQueue(Queue<E> copy, int x, int y, int hgap) {
        int index = 0;

        while (!copy.isEmpty()) {
            E dequeue = copy.dequeue();
            Graphics.gc.drawImage(graphicsController.createRectangleNumber(dequeue), x, y);
            Graphics.gc.fillText(index + "", x, y - 10);

            x += hgap;
            index++;
        }
    }

    /**
     * Displays Stack options
     * Messy because of the GUI stuff
     * TODO: Make the GUI look nicer and make this function cleaner :)
     */
    private void drawQueueOptions() {
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
            queue.clear();
            drawQueue();
        });

        pane1.getChildren().add(dataStructureName);
        pane1.setAlignment(Pos.CENTER);

        pane2.getChildren().addAll(goBack, clear, setUpInsertionOptions(), setUpDeletionOptions());
        pane2.setAlignment(Pos.TOP_CENTER);
        pane2.setSpacing(5);

        pane.setCenter(pane1);
        pane.setBottom(pane2);

        Graphics.rootLayout.setTop(pane);
    }

    private HBox setUpInsertionOptions() {
        HBox pane = new HBox();

        TextField insertElementField = new TextField();
        insertElementField.setPromptText("\tElement");

        Button insertBtn = new Button("Enqueue");
        insertBtn.setOnAction(event -> {
            if (queue.getSize() == queue.getCapacity()) {
                System.out.println("Reached maximum queue size");
                graphicsController.log("Reached maximum queue size");
                return;
            }

            try {
                queue.enqueue((E) Integer.valueOf(insertElementField.getText()));
                insertElementField.setText("");
                drawQueue();
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

        Button deleteBtn = new Button("Dequeue");
        deleteBtn.setOnAction(event -> {
            if (queue.isEmpty()) {
                graphicsController.log("Queue is empty");
                return;
            }

            try {
                queue.dequeue();
                drawQueue();
            } catch (NumberFormatException e) {
                System.out.println("Only integers!");
            }
        });

        pane.getChildren().add(deleteBtn);
        return pane;
    }
}
