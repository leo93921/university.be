package it.unisalento.se.converter.dtoToDao;

import it.unisalento.se.dao.AcademicYear;
import it.unisalento.se.dto.AcademicYearModel;


public class AcademicYearDtoToDao {

    public static AcademicYear convert(AcademicYearModel model){

        AcademicYear dao = new AcademicYear();
        dao.setId(model.getId());
        dao.setStartYear(model.getStartYear());
        dao.setEndYear(model.getEndYear());
        return dao;

    }


}
