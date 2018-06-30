package de.darktech.visual.mainwindow;

import de.darktech.Util;
import de.darktech.tickets.Planing;
import de.darktech.tickets.Ticket;
import de.darktech.main.ProgramRuntimeInformation;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.time.Instant;
import java.util.*;

import static de.darktech.visual.mainwindow.TicketGrid.IN_PROGRESS_STR;


public class MainWindow extends Application {


    public static final String COLOR_BACKGROUND_TICKET_HEADLINE = "#f4f1f1";

    private TicketGrid ticketGrid;
    private StackPane body;
    private  BorderPane rootBorderPane;


    protected void displayDetailOfTicket(Ticket ticket){
        DetailPaneTicket detailPaneTicket = new DetailPaneTicket(ticket);
        this.rootBorderPane.setRight(detailPaneTicket);
    }







    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("hallo");
        ScrollPane root = new ScrollPane();
        body = new StackPane();
        root.setContent(body);
        Scene scene = new Scene(root, 1200, 600);

        rootBorderPane = new BorderPane();
        rootBorderPane.setTop(new MenueBar(this,ProgramRuntimeInformation.get().getPlaning()));
        ticketGrid = new TicketGrid(this);
        rootBorderPane.setCenter(ticketGrid);
        body.getChildren().add(rootBorderPane);
        ticketGrid.addTicketsFromPlaning();
        ticketGrid.refreshTickets();
        ticketGrid.refreshTickets();

        primaryStage.setTitle("Planer");
        primaryStage.setScene(scene);
        primaryStage.show();


    }


    public static void main(String[] args) {
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(new Ticket("AufgabePlaner erstellen" , "BeispielDescription", 1, IN_PROGRESS_STR, Date.from(Instant.now())));
        tickets.add(new Ticket("Zweites Ticket adden" , "Quidquid agis prudenter agas et respice", 2, IN_PROGRESS_STR, Util.addDays(Date.from(Instant.now()), 2)));
        Planing planing =  Planing.get();
        System.out.println(planing);
        planing.addTickets(tickets);
        ProgramRuntimeInformation.get().setPlaning(planing);
        MainWindow window = new MainWindow();
        launch(args);

    }


    public void refreshTickets() {
        this.ticketGrid.refreshTickets();
    }
}
