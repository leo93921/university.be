package it.unisalento.se.exceptions;

import it.unisalento.se.dao.ReportingStatus;

public class ReportingStatusNotSupported extends Exception {

    public ReportingStatusNotSupported(String message){
        super(message);
    }
}

