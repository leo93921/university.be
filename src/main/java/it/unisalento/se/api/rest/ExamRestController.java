package it.unisalento.se.api.rest;


import it.unisalento.se.exceptions.ExamNotFoundException;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.iservices.IExamService;
import it.unisalento.se.models.ExamModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exam")
public class ExamRestController {


    @Autowired
    private IExamService examService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ExamModel getExamByID(@PathVariable("id") Integer ID) throws UserTypeNotSupported, ExamNotFoundException {
        return examService.getExamByID(ID);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ExamModel saveExam(@RequestBody ExamModel model) throws UserTypeNotSupported {
        return examService.saveExam(model);
    }


}
