package it.unisalento.se.iservices;

import it.unisalento.se.models.ClassroomModel;
import it.unisalento.se.exceptions.ClassroomNotFoundException;

public interface IClassroomService {

    ClassroomModel createClassroom(ClassroomModel classroom) ;
    ClassroomModel getClassroomByID(Integer ID) throws ClassroomNotFoundException;
}