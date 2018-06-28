package de.darktech.exceptions;

import java.io.IOException;

public class UnableToSaveException extends Exception{
    public UnableToSaveException(IOException e) {
        super(e);
    }
}
