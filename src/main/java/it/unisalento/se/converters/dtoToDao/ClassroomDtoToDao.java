package it.unisalento.se.converters.dtoToDao;

import it.unisalento.se.dao.Classroom;
import it.unisalento.se.models.ClassroomModel;

public class ClassroomDtoToDao {
    public static Classroom convert(ClassroomModel model) {

        Classroom dao = new Classroom();
        dao.setName(model.getName());
        dao.setLatitude(model.getLatitude());
        dao.setLongitude(model.getLongitude());
        dao.setId(model.getID());


        return dao;
    }


}



