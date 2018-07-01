package de.darktech.visual.mainwindow;

import de.darktech.tickets.Ticket;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static de.darktech.visual.mainwindow.TicketGrid.DONE_STR;
import static de.darktech.visual.mainwindow.TicketGrid.IN_PROGRESS_STR;
import static de.darktech.visual.mainwindow.TicketGrid.SELECT_STR;

public class MenueBarTicket extends Menu {

    private final MainWindow mainWindow;


    MenueBarTicket(MainWindow mainWindow, Ticket ticket){
        super("Options");
        this.mainWindow = mainWindow;


        Menu menuEffect = new Menu("Change State");
        final ToggleGroup groupEffect = new ToggleGroup();


        RadioMenuItem todoOption = new RadioMenuItem("Selected");
        todoOption.setUserData(SELECT_STR);
        todoOption.setToggleGroup(groupEffect);
        menuEffect.getItems().add(todoOption);

        RadioMenuItem inProgOption = new RadioMenuItem("In Progress");
        inProgOption.setUserData(IN_PROGRESS_STR);
        inProgOption.setToggleGroup(groupEffect);
        menuEffect.getItems().add(inProgOption);

        RadioMenuItem doneOption = new RadioMenuItem("Done");
        doneOption.setUserData(DONE_STR);
        doneOption.setToggleGroup(groupEffect);
        menuEffect.getItems().add(doneOption);

        groupEffect.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle old_toggle, Toggle new_toggle) {
                if (groupEffect.getSelectedToggle() != null) {
                    String state = (String) groupEffect.getSelectedToggle().getUserData();
                    ticket.setState(state);
                    mainWindow.refreshTickets();
                }
            }
        });


        this.getItems().add(menuEffect);

    }




}
