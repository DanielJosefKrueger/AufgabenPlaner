package de.darktech.visual;

import de.darktech.exceptions.InvalidInputException;
import de.darktech.tickets.Ticket;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class AddNewTicketWIndow extends Stage {


    private final MainWindow origin;

    AddNewTicketWIndow(MainWindow origin){
        this.origin = origin;
        this.setTitle("My New Stage Title");
        StackPane root = new StackPane();
        this.setScene(new Scene(root, 450, 450));
        TicketInputForm ticketInputForm = new TicketInputForm(origin);
        root.getChildren().add(ticketInputForm);
        this.show();
        System.out.println(origin);
    }





    private static class TicketInputForm extends GridPane{



        private final TextField nameField;
        private final TextField descrField;
        private final DatePicker dateInput;


        TicketInputForm(MainWindow mainwindow){


            //name
            Label nameLabel = new Label("Name:");
            nameField = new TextField();
            this.add(nameLabel, 0 ,0);
            this.add(nameField, 1,0);

            //description
            Label descrLabel = new Label("Description:");
            descrField = new TextField();
            this.add(descrLabel, 0 ,1);
            this.add(descrField, 1,1);

            //date
            Label dateLabel = new Label("Due Date:");
            dateInput = new DatePicker();



            this.add(dateLabel, 0 ,2);
            this.add(dateInput, 1,2);


            Button buttonNewTicket = new Button("Add Ticket");
            buttonNewTicket.setOnAction(event -> {
                try {
                    ProgramRuntimeInformation.get().getPlaning().addTicket(getTicket());
                    mainwindow.refreshTickets();
                } catch (InvalidInputException e) {
                    e.printStackTrace();
                }
            });
            this.add(buttonNewTicket, 0,3);



        }


        public Ticket getTicket() throws InvalidInputException{
            String name = nameField.getText();
            String description = descrField.getText();
            LocalDate localDate = dateInput.getValue();
            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            return new Ticket(name, description, 1, "In Progress" , date);
        }
    }






}
