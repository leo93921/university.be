package it.unisalento.se.api.rest;

import it.unisalento.se.exceptions.ReportingNotFoundException;
import it.unisalento.se.exceptions.ReportingStatusNotSupported;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.iservices.IReportingService;
import it.unisalento.se.models.ReportingModel;
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
    public ReportingModel saveReporting(@RequestBody ReportingModel model) throws UserTypeNotSupported, ReportingStatusNotSupported {
        return reportingService.saveReporting(model);
    }

    @GetMapping(value = "/find-all", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ReportingModel> getAllReportedProblems() throws UserTypeNotSupported, ReportingStatusNotSupported {
        return reportingService.getAllReporting();
    }


}

