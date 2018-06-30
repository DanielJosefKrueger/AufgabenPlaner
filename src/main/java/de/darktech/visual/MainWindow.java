package de.darktech.visual;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import de.darktech.Util;
import de.darktech.tickets.Planing;
import de.darktech.tickets.Ticket;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.Instant;
import java.util.*;


public class MainWindow extends Application {


    public static final String COLOR_BACKGROUND_TICKET_HEADLINE = "#f4f1f1";
    private GridPane ticketGrid;
    private List<ColumnInformation> columnInformations = new ArrayList<>();
    private Map<Integer, Integer> columnToTicketIndex = new HashMap<>();
    private StackPane body;


    private  BorderPane rootBorderPane;

    public static final String SELECT_STR = "Selected";
    public static final String IN_PROGRESS_STR = "In Progress";
    public static final String DONE_STR = "Done";

    public static final Double WIDTH_TICKET_COLUM = 200.0;




    private void displayDetailOfTicket(Ticket ticket){
        DetailPaneTicket detailPaneTicket = new DetailPaneTicket(ticket);
        this.rootBorderPane.setRight(detailPaneTicket);
    }



    public void refreshTickets(){
        System.out.println("Refresh");
        removeAllTicketsFromGrid();
        addTicketsFromPlaning();
    }


    public void addTicketsFromPlaning(){
        for (Ticket ticket : ProgramRuntimeInformation.get().getPlaning().getTickets()) {
            addTicketToGrid(ticket);
        }
    }

    public void removeAllTicketsFromGrid(){
        // reset indexes of tickets for all columns
        for (Map.Entry<Integer, Integer> entry : columnToTicketIndex.entrySet()) {
            entry.setValue(0);
        }



        ObservableList<Node> nodes = ticketGrid.getChildren();
        nodes.clear();

    }


    public void addTicketToGrid(Ticket ticket){
        String state = ticket.getState();
        for (ColumnInformation information : columnInformations) {
            if(information.getName().equals(state)){
                int colNumber = information.getNumber();
                Integer rowNumber = columnToTicketIndex.get(colNumber);
                GridPane panelForTicket = getPanelForTicket(ticket);
                ticketGrid.add(panelForTicket, colNumber, rowNumber );

                panelForTicket.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if(event.getButton().compareTo(MouseButton.PRIMARY)==0){
                            if(event.getSource().getClass().equals(TicketPane.class)){
                                TicketPane pane = (TicketPane) event.getSource();
                                System.out.println(pane.getTicket());
                                displayDetailOfTicket(pane.getTicket());
                            }

                        }
                    }
                });
                System.out.println("Adding " + ticket +" to view row:" + rowNumber + " column:" +colNumber);
                columnToTicketIndex.put(colNumber, rowNumber+1);
            }
        }
    }




    private GridPane getPanelForTicket(Ticket ticket){
      return new TicketPane(ticket);
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



    public GridPane getTicketGrid(){
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setMinWidth(200);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setMinWidth(200);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setMinWidth(200);
        grid.getColumnConstraints().addAll(col1,col2,col3);


        Font fontHeadline = Font.font("Arial", FontWeight.BOLD, 20);

        columnInformations.add(new ColumnInformation(0,SELECT_STR));
        columnToTicketIndex.put(0, 1);
        TextField selectTextField = new TextField();
        selectTextField.setText(SELECT_STR);
        selectTextField.setEditable(false);
        selectTextField.setFont(fontHeadline);
        selectTextField.setStyle("-fx-control-inner-background: " + COLOR_BACKGROUND_TICKET_HEADLINE + ";");
        grid.add(selectTextField, 0, 0);


        TextField progressTextField = new TextField();
        progressTextField.setText(IN_PROGRESS_STR);
        progressTextField.setFont(fontHeadline);
        progressTextField.setEditable(false);
        progressTextField.setStyle("-fx-control-inner-background: " + COLOR_BACKGROUND_TICKET_HEADLINE + ";");
        grid.add(progressTextField, 1, 0);
        columnInformations.add(new ColumnInformation(1,IN_PROGRESS_STR));
        columnToTicketIndex.put(1, 1);


        TextField doneTextField = new TextField();
        doneTextField.setText(DONE_STR);
        doneTextField.setFont(fontHeadline);
        doneTextField.setEditable(false);
        doneTextField.setStyle("-fx-control-inner-background: " + COLOR_BACKGROUND_TICKET_HEADLINE + ";");
        grid.add(doneTextField, 2, 0);
        columnInformations.add(new ColumnInformation(2,DONE_STR));
        columnToTicketIndex.put(2, 1);

        return grid;

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
        ticketGrid = getTicketGrid();
        rootBorderPane.setCenter(ticketGrid);
        body.getChildren().add(rootBorderPane);
        this.addTicketsFromPlaning();
        this.refreshTickets();
        this.refreshTickets();

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
        ProgramRuntimeInformation.get().setWindow(window);
        launch(args);

    }



    private static class ColumnInformation{

        private final int number;
        private final String name;


        private ColumnInformation(int number, String name) {
            this.number = number;
            this.name = name;
        }

        public int getNumber() {
            return number;
        }

        public String getName() {
            return name;
        }
    }


}
