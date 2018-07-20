package it.unisalento.se.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "The node passed is not supported")
public class NodeNotSupportedException extends Exception {
}
