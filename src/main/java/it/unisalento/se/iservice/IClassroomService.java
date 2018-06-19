package it.unisalento.se.iservice;

import it.unisalento.se.dto.ClassroomModel;
import it.unisalento.se.exceptions.ClassroomNotFoundException;

public interface IClassroomService {

    ClassroomModel createClassroom(ClassroomModel classroom) ;
    ClassroomModel getClassroomByID(Integer ID) throws ClassroomNotFoundException;
}