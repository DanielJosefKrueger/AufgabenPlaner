package de.darktech.tickets;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.darktech.exceptions.UnableToLoadException;
import de.darktech.exceptions.UnableToSaveException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Planing {

    private static final ObjectMapper mapper = new ObjectMapper();
    private final List<Ticket> tickets = new ArrayList<>();

    public Planing(List<Ticket> tickets){
        this.tickets.addAll(tickets);
    }


    public static Planing loadFromFile(File inputFile) throws UnableToLoadException {
        try(BufferedReader in = new BufferedReader(new FileReader(inputFile))){
            StringBuilder sb = new StringBuilder();
            in.lines().forEach(sb::append);
            return loadFromJson(sb.toString());
        } catch (IOException e) {
            throw new UnableToLoadException(e);
        }
    }

    public static Planing loadFromJson(String jsonString) throws UnableToLoadException {

        try {
            return mapper.readValue(jsonString, Planing.class);
        } catch (IOException e) {
            throw new UnableToLoadException(e);
        }
    }


    public  void saveToFile(String name) throws UnableToSaveException {
        try {
            mapper.writeValue(new File(name), this);
        } catch (IOException e) {
            throw new UnableToSaveException(e);
        }

    }


    public List<Ticket> getTickets() {
        return tickets;
    }

    public void addTicket(Ticket ticket){
        this.tickets.add(ticket);
    }

}
