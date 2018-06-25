package it.unisalento.se.converters.daoToDto;

import it.unisalento.se.dao.CourseOfStudy;
import it.unisalento.se.models.CourseOfStudyModel;

public class CourseOfStudyDaoToDto {

    public static CourseOfStudyModel convert(CourseOfStudy dao) {
        CourseOfStudyModel model = new CourseOfStudyModel();
        model.setID(dao.getId());
        model.setName(dao.getName());
        model.setAcademicYear(AcademicYearDaoToDto.convert(dao.getAcademicYear()));
        return model;
    }

}
