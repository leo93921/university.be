package it.unisalento.se.iservices;

import it.unisalento.se.models.AcademicYearModel;
import it.unisalento.se.exceptions.AcademicYearNotFoundException;

public interface IAcademicYearService {


    AcademicYearModel createAcademicYear (AcademicYearModel academicYear);
    AcademicYearModel getAcademicYearByID (Integer ID) throws AcademicYearNotFoundException;
}
