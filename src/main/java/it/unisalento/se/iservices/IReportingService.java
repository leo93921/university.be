package it.unisalento.se.iservices;

import it.unisalento.se.exceptions.ReportingNotFoundException;
import it.unisalento.se.exceptions.ReportingStatusNotSupported;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.ReportingModel;

public interface IReportingService {
    ReportingModel getReportingByID(Integer ID) throws UserTypeNotSupported, ReportingStatusNotSupported, ReportingNotFoundException;

    ReportingModel saveReporting(ReportingModel model) throws UserTypeNotSupported, ReportingStatusNotSupported;

}


