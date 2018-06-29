package it.unisalento.se.iservices;

import it.unisalento.se.exceptions.ClassroomNotFoundException;
import it.unisalento.se.models.ClassroomModel;

import java.util.List;

public interface IClassroomService {

    ClassroomModel createClassroom(ClassroomModel classroom) ;
    ClassroomModel getClassroomByID(Integer ID) throws ClassroomNotFoundException;

    List<ClassroomModel> getAllClassrooms();
}