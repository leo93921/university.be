package it.unisalento.se.converters.dtoToDao;

import it.unisalento.se.dao.CourseOfStudy;
import it.unisalento.se.models.CourseOfStudyModel;

public class CourseOfStudyDtoToDao {

    public static CourseOfStudy convert(CourseOfStudyModel model) {
        CourseOfStudy dao = new CourseOfStudy();
        dao.setId(model.getID());
        dao.setName(model.getName());
        dao.setAcademicYear(AcademicYearDtoToDao.convert(model.getAcademicYear()));
        return dao;
    }

}
