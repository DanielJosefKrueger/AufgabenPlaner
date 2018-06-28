package de.darktech.exceptions;

import java.io.IOException;

public class UnableToLoadException extends Exception {
    public UnableToLoadException(IOException e) {
        super(e);
    }
}
