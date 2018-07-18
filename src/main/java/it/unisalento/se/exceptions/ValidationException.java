package it.unisalento.se.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_GATEWAY, reason = "The request you made is not valid")
public class ValidationException extends Exception {

    public ValidationException(String message) {
        super(message);
    }
}
