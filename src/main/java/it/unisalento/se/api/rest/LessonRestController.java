package it.unisalento.se.api.rest;


import it.unisalento.se.exceptions.LessonNotFoundException;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.iservices.ILessonService;
import it.unisalento.se.models.LessonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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


}
