package it.unisalento.se.iservices;

import it.unisalento.se.exceptions.ReportingNotFoundException;
import it.unisalento.se.exceptions.ReportingStatusNotSupported;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.exceptions.ValidationException;
import it.unisalento.se.models.ClassroomModel;
import it.unisalento.se.models.ReportingModel;
import it.unisalento.se.models.UserModel;

import java.util.List;

public interface IReportingService {
    ReportingModel getReportingByID(Integer ID) throws UserTypeNotSupported, ReportingStatusNotSupported, ReportingNotFoundException;

    ReportingModel saveReporting(ReportingModel model) throws UserTypeNotSupported, ReportingStatusNotSupported, ValidationException;

    List<ReportingModel> getAllReporting() throws UserTypeNotSupported, ReportingStatusNotSupported;

    List<ReportingModel> getAllReportingByProfessor(UserModel prof) throws UserTypeNotSupported, ReportingStatusNotSupported;

    List<ReportingModel> getAllReportedProblemsByClassroom(ClassroomModel classroom) throws UserTypeNotSupported, ReportingStatusNotSupported;
}


