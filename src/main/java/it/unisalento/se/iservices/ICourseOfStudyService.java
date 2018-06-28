package it.unisalento.se.iservices;

import it.unisalento.se.exceptions.CourseOfStudyNotFound;
import it.unisalento.se.models.CourseOfStudyModel;

import java.util.List;

public interface ICourseOfStudyService {

    CourseOfStudyModel getCourseOfStudyByID(Integer ID) throws CourseOfStudyNotFound;

    CourseOfStudyModel saveCourseOfStudy(CourseOfStudyModel model);

    List<CourseOfStudyModel> getAllCourses();
}
