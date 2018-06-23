package it.unisalento.se.converters.dtoToDao;

import it.unisalento.se.dao.AcademicYear;
import it.unisalento.se.models.AcademicYearModel;


public class AcademicYearDtoToDao {

    public static AcademicYear convert(AcademicYearModel model){

        AcademicYear dao = new AcademicYear();
        dao.setId(model.getId());
        dao.setStartYear(model.getStartYear());
        dao.setEndYear(model.getEndYear());
        return dao;

    }


}
