package it.unisalento.se.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, value = HttpStatus.CONFLICT, reason = "User already registered")
public class UserAlreadyRegisteredException extends Throwable {
}
