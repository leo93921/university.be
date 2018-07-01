package it.unisalento.se.converters.dtoToDao;

import it.unisalento.se.dao.SupportDevice;
import it.unisalento.se.models.SupportDeviceModel;

public class SupportDeviceDtoToDao {
    public static SupportDevice convert(SupportDeviceModel model) {
        SupportDevice dao = new SupportDevice();
        dao.setId(model.getID());
        dao.setName(model.getName());
        return dao;

    }


}
