package it.unisalento.se.api.rest;

import it.unisalento.se.exceptions.ExamResultNotFoundException;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.iservices.IExamResultService;
import it.unisalento.se.models.ExamResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exam-result")
public class ExamResultRestController {


    @Autowired
    private IExamResultService examResultService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ExamResultModel getExamResultByID(@PathVariable("id") Integer ID) throws UserTypeNotSupported, ExamResultNotFoundException {
        return examResultService.getExamResultByID(ID);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ExamResultModel saveExamResult(@RequestBody ExamResultModel model) throws UserTypeNotSupported {
        return examResultService.saveExamResult(model);
    }


}
