package it.unisalento.se.exceptions;

import java.io.IOException;

public class StorageException extends Throwable {
    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, IOException e) {
        super(message, e);
    }
}
