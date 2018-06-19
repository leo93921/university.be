package it.unisalento.se.api.rest;


import it.unisalento.se.dto.ClassroomModel;
import it.unisalento.se.exceptions.ClassroomNotFoundException;
import it.unisalento.se.iservice.IClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/classroom")
public class ClassroomRestController {


    @Autowired
    private IClassroomService classroomService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClassroomModel getClassroomById(@PathVariable("id") Integer ID) throws ClassroomNotFoundException {
        return classroomService.getClassroomByID(ID);
    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ClassroomModel saveClass(@RequestBody ClassroomModel classroom)  {
        return classroomService.createClassroom(classroom);
    }


}