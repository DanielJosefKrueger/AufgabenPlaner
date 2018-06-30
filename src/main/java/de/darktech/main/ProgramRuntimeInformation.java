package de.darktech.main;

import de.darktech.tickets.Planing;




public class ProgramRuntimeInformation {

    private static  ProgramRuntimeInformation INSTANCE = null;
    private Planing planing = null;


    private ProgramRuntimeInformation(){
    }

    public static ProgramRuntimeInformation get(){
        if(INSTANCE ==null){
            INSTANCE = new ProgramRuntimeInformation();
        }
        return INSTANCE;
    }


    public Planing getPlaning() {
        return planing;
    }

    public void setPlaning(Planing planing) {
        this.planing = planing;
    }


}