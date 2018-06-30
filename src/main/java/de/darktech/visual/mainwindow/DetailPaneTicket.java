package de.darktech.visual.mainwindow;

import de.darktech.tickets.Ticket;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.time.Instant;
import java.util.Date;

public class DetailPaneTicket extends GridPane {

    private Ticket ticket;

    DetailPaneTicket(Ticket ticket){

        this.ticket = ticket;
        this.setHgap(10);
        this.setVgap(10);
        Text name = new Text(ticket.getName());
        name.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        this.add(name, 0, 0);

        Text description = new Text(ticket.getDescription());
        description.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        this.add(description, 0, 1);

        Text date = new Text(ticket.getDateString());
        date.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        this.add(date, 0, 2);



        if(ticket.getDueDate().before(Date.from(Instant.now()))){
            this.setStyle("-fx-background-color: RED");

        }else{
            this.setStyle("-fx-background-color: rgba(25,25,25,0.46)");
        }
    }




}
