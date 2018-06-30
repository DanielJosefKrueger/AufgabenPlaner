package de.darktech.visual;

import de.darktech.tickets.Planing;




public class ProgramRuntimeInformation {

    private static  ProgramRuntimeInformation INSTANCE = null;
    private Planing planing = null;
    private MainWindow window = null;

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

    public MainWindow getWindow() {
        return window;
    }

    public void setWindow(MainWindow window) {
        this.window = window;
    }
}
