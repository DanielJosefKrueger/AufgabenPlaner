package de.darktech.visual.mainwindow;

import com.sun.javafx.geom.BaseBounds;
import de.darktech.tickets.Ticket;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.time.Instant;
import java.util.Date;

public class TicketPane extends GridPane{

    private Ticket ticket;

    TicketPane(Ticket ticket){

        this.setMinSize(300, 100);
        this.setMaxSize(300, 100);
        this.ticket = ticket;
        this.setHgap(10);
        this.setVgap(10);
        Label name = new Label(ticket.getName());
        name.setMaxSize(300, 50);
        name.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        this.add(name, 0, 0);

        //Text description = new Text(ticket.getDescription());
        //description.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        //this.add(description, 0, 1);

        Label date = new Label(ticket.getDateString());
        date.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        this.add(date, 0, 1);

        if(ticket.getDueDate().before(Date.from(Instant.now()))){
            this.setStyle("-fx-background-color: #ed1a29");

        }else{
            this.setStyle("-fx-background-color: rgba(205,205,205,0.46)");
        }
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
