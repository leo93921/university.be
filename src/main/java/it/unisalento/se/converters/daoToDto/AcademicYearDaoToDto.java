package it.unisalento.se.converters.daoToDto;

import it.unisalento.se.dao.AcademicYear;
import it.unisalento.se.models.AcademicYearModel;

public class AcademicYearDaoToDto {
    public static AcademicYearModel convert(AcademicYear y) {
        AcademicYearModel year = new AcademicYearModel();
        year.setID(y.getId());
        year.setStartYear(y.getStartYear());
        year.setEndYear(y.getEndYear());
        return year;

    }


}
