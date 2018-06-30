package de.darktech.tickets;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.darktech.exceptions.UnableToLoadException;
import de.darktech.exceptions.UnableToSaveException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Planing {
    private static final ObjectMapper mapper = new ObjectMapper();
    private final List<Ticket> tickets = new ArrayList<>();

    private  static  Planing INSTANCE =null;
    private Planing(){
    }


    public static Planing get(){
        if(INSTANCE==null){
            INSTANCE=new Planing();
        }
        return INSTANCE;
    }










    public void loadFromFile(File inputFile) throws UnableToLoadException {
        try(BufferedReader in = new BufferedReader(new FileReader(inputFile))){
            StringBuilder sb = new StringBuilder();
            in.lines().forEach(sb::append);
            loadFromJson(sb.toString());
        } catch (IOException e) {
            throw new UnableToLoadException(e);
        }
    }

    public void loadFromJson(String jsonString) throws UnableToLoadException {
        try {
          //  Planing planing = mapper.readValue(jsonString, Planing.class);
            this.tickets.clear();
            JsonNode rootNode = mapper.readTree(jsonString);
            for (JsonNode singleTicket : rootNode.get("tickets")) {
                JsonNode nameNode = singleTicket.get("name");
                JsonNode descrNode = singleTicket.get("description");
                JsonNode idNode = singleTicket.get("id");
                JsonNode stateNode = singleTicket.get("state");
                JsonNode dateNote = singleTicket.get("dueDate");
                Ticket ticket = new Ticket(nameNode.asText(), descrNode.asText(), idNode.asInt(), stateNode.asText(), dateNote.asLong());
                this.tickets.add(ticket);
            }
            System.out.println(tickets);
          //  this.tickets.addAll(planing.tickets);
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


    public void addTickets(List<Ticket> tickets){
        this.tickets.addAll(tickets);
    }


    @Override
    public String toString() {
        return "Planing{" +
                "tickets=" + tickets +
                '}';
    }
}
