package it.unisalento.se.iservice;

import it.unisalento.se.dto.AcademicYearModel;
import it.unisalento.se.exceptions.AcademicYearNotFoundException;

public interface IAcademicYearService {


    AcademicYearModel createAcademicYear (AcademicYearModel academicYear);
    AcademicYearModel getAcademicYearByID (Integer ID) throws AcademicYearNotFoundException;
}
