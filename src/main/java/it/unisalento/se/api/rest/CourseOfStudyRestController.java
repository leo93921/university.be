package it.unisalento.se.api.rest;

import it.unisalento.se.exceptions.CourseOfStudyNotFound;
import it.unisalento.se.iservices.ICourseOfStudyService;
import it.unisalento.se.models.CourseOfStudyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course-of-study")
public class CourseOfStudyRestController {

    @Autowired
    private ICourseOfStudyService service;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CourseOfStudyModel getCourseOfStudyByID(@PathVariable("id") Integer ID) throws CourseOfStudyNotFound {
        return service.getCourseOfStudyByID(ID);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CourseOfStudyModel saveCourseOfStudy(@RequestBody CourseOfStudyModel model) {
        return service.saveCourseOfStudy(model);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<CourseOfStudyModel> getAll() {
        return service.getAllCourses();
    }
}
