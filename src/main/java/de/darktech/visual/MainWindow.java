package de.darktech.visual;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;



public class MainWindow extends Application {

    private Label label;
    private TableView table = new TableView();


    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("haölöp");
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 1200, 600);

        label = new Label("test");
        root.getChildren().add(label);

        primaryStage.setTitle("Planer");
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }

}
