package it.unisalento.se.converter.daoToDto;

import it.unisalento.se.dao.AcademicYear;
import it.unisalento.se.dto.AcademicYearModel;

public class AcademicYearDaoToDto {
    public static AcademicYearModel convert (AcademicYear y) {
        AcademicYearModel year = new AcademicYearModel();
        year.setId(y.getId());
        year.setStartYear(y.getStartYear());
        year.setEndYear(y.getEndYear());
        return year;

    }


}
