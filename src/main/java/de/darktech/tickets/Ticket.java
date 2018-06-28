package de.darktech.tickets;

public class Ticket {


    private final String name;
    private final String description;
    private final int id;
    private final String state;

    public Ticket(String name, String description, int id, String state) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.state = state;
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
}
