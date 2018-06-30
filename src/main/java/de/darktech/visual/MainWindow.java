package de.darktech.visual;

import de.darktech.Util;
import de.darktech.tickets.Planing;
import de.darktech.tickets.Ticket;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.time.Instant;
import java.util.*;

import static de.darktech.visual.TicketGrid.IN_PROGRESS_STR;


public class MainWindow extends Application {


    public static final String COLOR_BACKGROUND_TICKET_HEADLINE = "#f4f1f1";
    
    private TicketGrid ticketGrid;
    private StackPane body;
    private  BorderPane rootBorderPane;


    protected void displayDetailOfTicket(Ticket ticket){
        DetailPaneTicket detailPaneTicket = new DetailPaneTicket(ticket);
        this.rootBorderPane.setRight(detailPaneTicket);
    }

    public HBox getNavigation() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");

        Button buttonCurrent = new Button("Save");
        buttonCurrent.setPrefSize(100, 20);

        Button buttonNewTicket = new Button("New Ticket");
        buttonNewTicket.setPrefSize(100, 20);
        MainWindow origin = this;
        buttonNewTicket.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               new AddNewTicketWIndow(origin);
            }
        });


        Button buttonProjected = new Button("Load");
        buttonProjected.setPrefSize(100, 20);
        hbox.getChildren().addAll(buttonCurrent, buttonProjected, buttonNewTicket);

        return hbox;
    }






    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("hallo");
        ScrollPane root = new ScrollPane();
        body = new StackPane();
        root.setContent(body);
        Scene scene = new Scene(root, 1200, 600);

        rootBorderPane = new BorderPane();
        rootBorderPane.setTop(getNavigation());
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
        Planing planing = new Planing(tickets);
        ProgramRuntimeInformation.get().setPlaning(planing);
        MainWindow window = new MainWindow();
        launch(args);

    }


    public void refreshTickets() {
        this.ticketGrid.refreshTickets();
    }
}
