package de.darktech.visual.mainwindow;

import de.darktech.tickets.Ticket;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.time.Instant;
import java.util.Date;

public class DetailPaneTicket extends GridPane {

    private Ticket ticket;

    private final MainWindow mainWindow;
    DetailPaneTicket(Ticket ticket, MainWindow mainWindow){
        this.mainWindow = mainWindow;
        this.ticket = ticket;
        this.setMinSize(300, 600);
        this.setMaxSize(300, 600);

        this.setHgap(10);
        this.setVgap(10);



        MenueBarTicket menueBarTicket = new MenueBarTicket(mainWindow, ticket);
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(menueBarTicket);
        this.add(menuBar, 0, 0);
        Text name = new Text(ticket.getName());
        name.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        this.add(name, 0, 1);

        Text description = new Text(ticket.getDescription());
        description.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        this.add(description, 0, 2);

        Text date = new Text(ticket.getDateString());
        date.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        this.add(date, 0, 3);



        if(ticket.getDueDate().before(Date.from(Instant.now()))){
            this.setStyle("-fx-background-color: RED");

        }else{
            this.setStyle("-fx-background-color: rgba(25,25,25,0.46)");
        }
    }






}
