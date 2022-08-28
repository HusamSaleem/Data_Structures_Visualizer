import dataStructures.DataStructureManager;
import graphics.Graphics;
import graphics.GraphicsController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import static graphics.Graphics.*;

/**
 * Displays several data structures on screen to help new beginners learning data structures.
 * Version 1.0
 *
 * @Author: Husam Saleem
 */
public class Main extends Application {
    public static GraphicsController graphicsController;
    static GridPane dataStructureLayout = null;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        DataStructureManager.initDataStructures();
        Graphics.rootLayout = new BorderPane();
        dataStructureLayout = new GridPane();
        Graphics.canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        Graphics.gc = Graphics.canvas.getGraphicsContext2D();
        primaryStage.setTitle("Data Structure Visualizer");
        primaryStage.setScene(new Scene(Graphics.rootLayout, WINDOW_WIDTH, WINDOW_HEIGHT));
        Graphics.rootLayout.setCenter(Graphics.canvas);
        graphicsController = new GraphicsController();
        primaryStage.show();
    }

}

