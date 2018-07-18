package it.unisalento.se.api.rest;

import it.unisalento.se.exceptions.ReportingNotFoundException;
import it.unisalento.se.exceptions.ReportingStatusNotSupported;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.exceptions.ValidationException;
import it.unisalento.se.iservices.IReportingService;
import it.unisalento.se.models.ClassroomModel;
import it.unisalento.se.models.ReportingModel;
import it.unisalento.se.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reporting")
public class ReportingRestController {


    @Autowired
    private IReportingService reportingService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ReportingModel getReportingByID(@PathVariable("id") Integer ID) throws ReportingStatusNotSupported, UserTypeNotSupported, ReportingNotFoundException {
        return reportingService.getReportingByID(ID);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ReportingModel saveReporting(@RequestBody ReportingModel model) throws UserTypeNotSupported, ReportingStatusNotSupported, ValidationException {
        return reportingService.saveReporting(model);
    }

    @GetMapping(value = "/find-all", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ReportingModel> getAllReportedProblems() throws UserTypeNotSupported, ReportingStatusNotSupported {
        return reportingService.getAllReporting();
    }

    @PostMapping(value = "/find-by-professor", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ReportingModel> getAllReportedProblemsByProfessor(@RequestBody UserModel prof) throws UserTypeNotSupported, ReportingStatusNotSupported {
        return reportingService.getAllReportingByProfessor(prof);
    }

    @PostMapping(value = "/find-by-classroom", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ReportingModel> getAllReportedProblemsByClassroom(@RequestBody ClassroomModel classroom) throws UserTypeNotSupported, ReportingStatusNotSupported {
        return reportingService.getAllReportedProblemsByClassroom(classroom);
    }
}

