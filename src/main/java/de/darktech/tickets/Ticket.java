package de.darktech.tickets;

import org.apache.commons.lang3.time.FastDateFormat;

import java.util.Date;

public class Ticket {


    private final String name;
    private final String description;
    private final int id;
    private final String state;
    private final Date dueDate;

    public static final FastDateFormat format =  FastDateFormat.getInstance("dd-MM-yyyy hh");

    public Ticket(String name, String description, int id, String state, Date dueDate) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.state = state;
        this.dueDate = dueDate;
    }

    public String getState() {
        return state;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public String getDateString() {
        return format.format(dueDate);
    }
}
