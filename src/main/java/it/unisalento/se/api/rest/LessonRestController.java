package it.unisalento.se.api.rest;


import it.unisalento.se.exceptions.LessonNotFoundException;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.iservices.ILessonService;
import it.unisalento.se.models.LessonFilterModel;
import it.unisalento.se.models.LessonModel;
import it.unisalento.se.models.SubjectModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lesson")

public class LessonRestController {


    @Autowired
    private ILessonService lessonService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LessonModel getLessonByID(@PathVariable("id") Integer ID) throws UserTypeNotSupported, LessonNotFoundException {
        return lessonService.getLessonByID(ID);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LessonModel saveLesson(@RequestBody LessonModel model) throws UserTypeNotSupported {
        return lessonService.saveLesson(model);
    }

    @PostMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<LessonModel> getLessonByDate(@RequestBody LessonFilterModel filter) throws UserTypeNotSupported {
        return lessonService.filterByTimeAndSubject(filter);
    }


    @PostMapping(value = "/daily", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<LessonModel> getDailyLessons(@RequestBody LessonFilterModel filter) throws UserTypeNotSupported {
        return lessonService.filterByTimeAndCourseOfStudy(filter);
    }


    @PostMapping(value = "/find-by-subject", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<LessonModel> getLessonsBySubject(@RequestBody SubjectModel subject) throws UserTypeNotSupported {
        return lessonService.getLessonsBySubjects(subject);
    }


}
