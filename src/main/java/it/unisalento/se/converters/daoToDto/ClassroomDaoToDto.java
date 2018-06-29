package it.unisalento.se.converters.daoToDto;

import it.unisalento.se.dao.Classroom;
import it.unisalento.se.models.ClassroomModel;

public class ClassroomDaoToDto {

    public static ClassroomModel convert(Classroom c) {
        ClassroomModel classroom = new ClassroomModel();

        classroom.setId(c.getId());
        classroom.setName(c.getName());
        classroom.setLatitude(c.getLatitude());
        classroom.setLongitude(c.getLongitude());

        return classroom;

    }


}

