package it.unisalento.se.iservices;

import it.unisalento.se.exceptions.CourseOfStudyNotFoundException;
import it.unisalento.se.models.CourseOfStudyModel;

import java.util.List;

public interface ICourseOfStudyService {

    CourseOfStudyModel getCourseOfStudyByID(Integer ID) throws CourseOfStudyNotFoundException;

    CourseOfStudyModel saveCourseOfStudy(CourseOfStudyModel model);

    List<CourseOfStudyModel> getAllCourses();
}
