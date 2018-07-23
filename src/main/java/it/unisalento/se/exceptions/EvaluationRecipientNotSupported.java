package it.unisalento.se.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class EvaluationRecipientNotSupported extends Exception {
    public EvaluationRecipientNotSupported(String message) {
        super(message);
    }
}

