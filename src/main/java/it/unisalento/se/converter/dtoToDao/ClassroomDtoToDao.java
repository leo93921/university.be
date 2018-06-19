package it.unisalento.se.converter.dtoToDao;

import it.unisalento.se.dao.Classroom;
import it.unisalento.se.dto.ClassroomModel;

public class ClassroomDtoToDao {
    public static Classroom convert(ClassroomModel model) {

        Classroom dao = new Classroom();
        dao.setName(model.getName());
        dao.setLatitude(model.getLatitude());
        dao.setLongitude(model.getLongitude());
        dao.setId(model.getId());


        return dao;
    }


}



