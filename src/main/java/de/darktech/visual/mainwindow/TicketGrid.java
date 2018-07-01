package de.darktech.visual.mainwindow;

import de.darktech.tickets.Ticket;
import de.darktech.main.ProgramRuntimeInformation;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.darktech.visual.mainwindow.MainWindow.COLOR_BACKGROUND_TICKET_HEADLINE;

public class TicketGrid extends GridPane {



    public static final String SELECT_STR = "Selected";
    public static final String IN_PROGRESS_STR = "In Progress";
    public static final String DONE_STR = "Done";
    public final MainWindow mainWindow;



    private List<ColumnInformation> columnInformations = new ArrayList<>();
    private Map<Integer, Integer> columnToTicketIndex = new HashMap<>();


    TicketGrid(MainWindow mainWindow){
        this.mainWindow = mainWindow;
        this.setHgap(10);
        this.setVgap(10);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setMinWidth(300);
        col1.setMaxWidth(300);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setMinWidth(300);
        col2.setMaxWidth(300);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setMinWidth(300);
        col3.setMaxWidth(300);
        this.getColumnConstraints().addAll(col1,col2,col3);

        columnInformations.add(new ColumnInformation(0,SELECT_STR));
        columnInformations.add(new ColumnInformation(1,IN_PROGRESS_STR));
        columnInformations.add(new ColumnInformation(2,DONE_STR));
        initHeadlines();

    }

    private void initHeadlines() {



        Font fontHeadline = Font.font("Arial", FontWeight.BOLD, 20);

        columnToTicketIndex.put(0, 1);
        TextField selectTextField = new TextField();
        selectTextField.setText(SELECT_STR);
        selectTextField.setEditable(false);
        selectTextField.setFont(fontHeadline);
        selectTextField.setStyle("-fx-control-inner-background: " + COLOR_BACKGROUND_TICKET_HEADLINE + ";");
        this.add(selectTextField, 0, 0);


        TextField progressTextField = new TextField();
        progressTextField.setText(IN_PROGRESS_STR);
        progressTextField.setFont(fontHeadline);
        progressTextField.setEditable(false);
        progressTextField.setStyle("-fx-control-inner-background: " + COLOR_BACKGROUND_TICKET_HEADLINE + ";");
        this.add(progressTextField, 1, 0);
        columnToTicketIndex.put(1, 1);


        TextField doneTextField = new TextField();
        doneTextField.setText(DONE_STR);
        doneTextField.setFont(fontHeadline);
        doneTextField.setEditable(false);
        doneTextField.setStyle("-fx-control-inner-background: " + COLOR_BACKGROUND_TICKET_HEADLINE + ";");
        this.add(doneTextField, 2, 0);

        columnToTicketIndex.put(2, 1);
    }


    public void addTicketToGrid(Ticket ticket){
        String state = ticket.getState();
        for (ColumnInformation information : columnInformations) {
            if(information.getName().equals(state)){
                int colNumber = information.getNumber();
                Integer rowNumber = columnToTicketIndex.get(colNumber);
                GridPane panelForTicket = new TicketPane(ticket);
                this.add(panelForTicket, colNumber, rowNumber );

                panelForTicket.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if(event.getButton().compareTo(MouseButton.PRIMARY)==0){
                            if(event.getSource().getClass().equals(TicketPane.class)){
                                TicketPane pane = (TicketPane) event.getSource();
                                System.out.println(pane.getTicket());
                                mainWindow.displayDetailOfTicket(pane.getTicket());
                            }

                        }
                    }
                });
                System.out.println("Adding " + ticket +" to view row:" + rowNumber + " column:" +colNumber);
                columnToTicketIndex.put(colNumber, rowNumber+1);
                return;
            }
        }
    }



    public void refreshTickets(){
        System.out.println("Refresh");
        removeAllTicketsFromGrid();
        addTicketsFromPlaning();
    }


    public void addTicketsFromPlaning(){
        System.out.println("------ Adding Tickets to Grid -------");
        System.out.println(ProgramRuntimeInformation.get().getPlaning());
        for (Ticket ticket : ProgramRuntimeInformation.get().getPlaning().getTickets()) {
            addTicketToGrid(ticket);
        }
    }

    public void removeAllTicketsFromGrid(){

        ObservableList<Node> nodes = this.getChildren();
        nodes.clear();

        initHeadlines();
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
