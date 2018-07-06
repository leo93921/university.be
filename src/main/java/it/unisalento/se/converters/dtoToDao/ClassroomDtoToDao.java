package it.unisalento.se.converters.dtoToDao;

import it.unisalento.se.dao.Classroom;
import it.unisalento.se.models.ClassroomModel;
import it.unisalento.se.models.SupportDeviceModel;

public class ClassroomDtoToDao {
    public static Classroom convert(ClassroomModel model) {

        Classroom dao = new Classroom();
        dao.setName(model.getName());
        dao.setLatitude(model.getLatitude());
        dao.setLongitude(model.getLongitude());
        dao.setId(model.getID());

        if (model.getSupportDevices() != null) {
            for (SupportDeviceModel m : model.getSupportDevices()) {
                dao.getSupportDevices().add(SupportDeviceDtoToDao.convert(m));
            }
        }

        return dao;
    }


}



