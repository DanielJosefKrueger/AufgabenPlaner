package de.darktech.visual.mainwindow;

import de.darktech.exceptions.UnableToLoadException;
import de.darktech.exceptions.UnableToSaveException;
import de.darktech.tickets.Planing;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.io.File;

public class MenueBar extends HBox{


    private final MainWindow mainWindow;
    private final Planing planing;

    MenueBar(MainWindow mainWindow, Planing planing){
        this.planing = planing;
        this.mainWindow = mainWindow;
        this.setPadding(new Insets(15, 12, 15, 12));
        this.setSpacing(10);
        this.setStyle("-fx-background-color: #336699;");

        Button saveButton = new Button("Save");
        saveButton.setPrefSize(100, 20);
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    planing.saveToFile("test.txt");
                } catch (UnableToSaveException e) {
                    e.printStackTrace();
                }
            }
        });


        Button buttonNewTicket = new Button("New Ticket");
        buttonNewTicket.setPrefSize(100, 20);
        buttonNewTicket.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new AddNewTicketWIndow(mainWindow);
            }
        });




        Button loadButton = new Button("Load");
        loadButton.setPrefSize(100, 20);
        loadButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    planing.loadFromFile(new File("test.txt"));
                    mainWindow.refreshTickets();
                } catch (UnableToLoadException e) {
                    e.printStackTrace();
                }
            }
        });



        this.getChildren().addAll(saveButton, loadButton, buttonNewTicket);


    }


}
