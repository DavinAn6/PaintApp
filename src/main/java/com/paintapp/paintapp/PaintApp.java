package com.paintapp.paintapp;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.canvas.Canvas;

/**
 * Simplified version of MSPaint
 * @author Davin An
 * @version 1.0
 */
public class PaintApp extends Application {
    private static final RadioButton drawButton = new RadioButton("Draw");
    private static final RadioButton eraseButton = new RadioButton("Erase");
    private static final RadioButton circleButton = new RadioButton("Circle");
    private static final TextField textBox = new TextField();
    private static final Button clearButton = new Button("Clear Canvas");
    private static final Canvas CANVAS = new Canvas(650, 450);
    private static final GraphicsContext GC = CANVAS.getGraphicsContext2D();
    private static final Text currentPosition = new Text();
    private static final Text numShapesText = new Text();
    private static int numShapes = 0;


    /**
     * Override start method in Application
     * @param primaryStage Stage object to show
     */
    @Override
    public void start(Stage primaryStage) {
        BorderPane border = new BorderPane();
        border.setLeft(addSidePane());
        border.setBottom(addBottomPane());
        border.setCenter(CANVAS);


        // Add radio buttons to Toggle Group so that only one can be selected
        ToggleGroup toggleGroup = new ToggleGroup();
        drawButton.setToggleGroup(toggleGroup);
        eraseButton.setToggleGroup(toggleGroup);
        circleButton.setToggleGroup(toggleGroup);
        drawButton.setSelected(true);

        // Event Handling
        CANVAS.addEventHandler(MouseEvent.MOUSE_CLICKED,
                e -> {
                    if (toggleGroup.getSelectedToggle() == drawButton) {
                        GC.closePath();
                        GC.setStroke(getColor());
                        GC.setLineWidth(2);
                        GC.beginPath();
                        GC.moveTo(e.getX(), e.getY());
                        GC.stroke();
                    } else if (toggleGroup.getSelectedToggle() == eraseButton) {
                        GC.setFill(Color.WHITE);
                        GC.fillOval(e.getX(), e.getY(), 10, 10);
                    } else if (toggleGroup.getSelectedToggle() == circleButton) {
                        GC.setFill(getColor());
                        GC.fillOval(e.getX(), e.getY(), 15, 15);
                    }
                });


        CANVAS.addEventHandler(MouseEvent.MOUSE_DRAGGED,
                e -> {
                    if (toggleGroup.getSelectedToggle() == drawButton) {
                        GC.setStroke(getColor());
                        GC.setLineWidth(2);
                        GC.lineTo(e.getX(), e.getY());
                        GC.stroke();
                    } else if (toggleGroup.getSelectedToggle() == eraseButton) {
                        GC.setFill(Color.WHITE);
                        GC.fillOval(e.getX(), e.getY(), 10, 10);
                    }
                });


        CANVAS.addEventHandler(MouseEvent.MOUSE_RELEASED,
                e -> {
                    numShapes++;
                    numShapesText.setText("Number of shapes: " + numShapes);
                });

        CANVAS.addEventHandler(MouseEvent.MOUSE_MOVED,
                e -> currentPosition.setText("(" + e.getX() + ", " + e.getY() + ")"));

        // Clear Canvas button
        clearButton.setOnAction(e -> {
            numShapes = 0;
            numShapesText.setText("Number of shapes: " + numShapes);
            GC.setFill(Color.WHITE);
            GC.fillRect(0, 0, CANVAS.getWidth(), CANVAS.getHeight());
        });


        // Rest of the GUI setup
        Group root = new Group();
        root.getChildren().addAll(border);
        Scene scene = new Scene(root);
        primaryStage.setTitle("Paint Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Initialization method for side pane
     * @return A VBox side pane
     */
    public static VBox addSidePane() {
        // Buttons
        drawButton.setFont(Font.font("Arial", 15));
        eraseButton.setFont(Font.font("Arial", 15));
        circleButton.setFont(Font.font("Arial", 15));
        clearButton.setFont(Font.font("Arial", 15));

        // Add nodes to VBox
        VBox sidePane = new VBox();
        sidePane.setStyle("-fx-background-color:grey;");
        sidePane.setPadding(new Insets(5, 20, 5, 5)); // top, right, bottom, left
        sidePane.setSpacing(10);
        sidePane.getChildren().addAll(drawButton, eraseButton, circleButton, textBox, clearButton);
        return sidePane;
    }

    /**
     * Initialization method for bottom pane
     * @return An HBox bottom pane
     */
    public static HBox addBottomPane() {
        currentPosition.setFont(Font.font("Arial", 15));
        numShapesText.setFont(Font.font("Arial", 15));

        // Add nodes to HBox
        HBox bottomPane = new HBox();
        bottomPane.setStyle("-fx-background-color:lightgrey;");
        bottomPane.setPadding(new Insets(5, 10, 5, 10));  // top, right, bottom, left
        bottomPane.setSpacing(15);
        bottomPane.getChildren().addAll(currentPosition, numShapesText);
        return bottomPane;
    }

    /**
     * Returns color that user types in to the text field
     * @return User color
     */
    public static Color getColor() {
        Color userColor = new Color(0, 0, 0, 0);
        Alert alert = new Alert(AlertType.ERROR, "Invalid color entered!");
        try {
            userColor = Color.valueOf(textBox.getText());
        } catch (NullPointerException | IllegalArgumentException e) {
            alert.show();
        }
        return userColor;
    }


    /**
     * Main method
     * @param args Console arguments
     */
    public static void main(String[] args) {
        launch();
    }

}