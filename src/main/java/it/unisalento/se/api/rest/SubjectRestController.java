package it.unisalento.se.api.rest;

import it.unisalento.se.exceptions.SubjectNotFoundException;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.iservices.ISubjectService;
import it.unisalento.se.models.CourseOfStudyModel;
import it.unisalento.se.models.SubjectModel;
import it.unisalento.se.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectRestController {

    @Autowired
    private ISubjectService subjectService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SubjectModel getSubjectByID(@PathVariable("id") Integer ID) throws UserTypeNotSupported, SubjectNotFoundException {
        return subjectService.getSubjectByID(ID);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SubjectModel saveSubject(@RequestBody SubjectModel model) throws UserTypeNotSupported {
        return subjectService.saveSubject(model);
    }

    @PostMapping(value = "/find-by-course", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<SubjectModel> getAllSubjectsByCourseOfStudy(@RequestBody CourseOfStudyModel model) throws UserTypeNotSupported {
        return subjectService.getAllSubjectsByCourseOfStudy(model);
    }

    @PostMapping(value = "/find-by-prof", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<SubjectModel> getAllSubjectsByProfessor(@RequestBody UserModel prof) throws UserTypeNotSupported {
        return subjectService.getAllSubjectsByProfessor(prof);
    }

}
