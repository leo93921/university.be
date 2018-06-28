package it.unisalento.se.iservices;

import it.unisalento.se.exceptions.AcademicYearNotFoundException;
import it.unisalento.se.models.AcademicYearModel;

import java.util.List;

public interface IAcademicYearService {

    AcademicYearModel createAcademicYear (AcademicYearModel academicYear);
    AcademicYearModel getAcademicYearByID (Integer ID) throws AcademicYearNotFoundException;

    List<AcademicYearModel> getAll();

}
