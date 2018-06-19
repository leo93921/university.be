package it.unisalento.se.api.rest;


import it.unisalento.se.dto.AcademicYearModel;
import it.unisalento.se.exceptions.AcademicYearNotFoundException;
import it.unisalento.se.iservice.IAcademicYearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/academic-year")
public class AcademicYearRestController {



    @Autowired
    private IAcademicYearService academicYearService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AcademicYearModel getAcademicYearById(@PathVariable("id") Integer ID) throws AcademicYearNotFoundException {
        return academicYearService.getAcademicYearByID(ID);

    }


    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public AcademicYearModel saveAcademicYear(@RequestBody AcademicYearModel academicyear){
        return academicYearService.createAcademicYear(academicyear);
    }
}
