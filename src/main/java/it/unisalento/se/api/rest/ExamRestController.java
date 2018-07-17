package it.unisalento.se.api.rest;


import it.unisalento.se.exceptions.ExamNotFoundException;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.iservices.IExamService;
import it.unisalento.se.models.ExamFilterModel;
import it.unisalento.se.models.ExamModel;
import it.unisalento.se.models.LessonFilterModel;
import it.unisalento.se.models.LessonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @PostMapping(value = "/daily", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ExamModel> getDailyExams(@RequestBody ExamFilterModel filter) throws UserTypeNotSupported {
        return examService.filterByTimeAndCourseOfStudy(filter);
    }


    @PostMapping(value = "/daily-professor", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ExamModel> getDailyExamsProfessor(@RequestBody ExamFilterModel filter) throws UserTypeNotSupported {
        return examService.filterByTimeAndProfessor(filter);
    }

}
