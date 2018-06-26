package it.unisalento.se.api.rest;

import it.unisalento.se.exceptions.SubjectNotFoundException;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.iservices.ISubjectService;
import it.unisalento.se.models.SubjectModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

}
