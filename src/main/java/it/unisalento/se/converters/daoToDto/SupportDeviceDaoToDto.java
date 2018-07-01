package it.unisalento.se.converters.daoToDto;

import it.unisalento.se.dao.SupportDevice;
import it.unisalento.se.models.SupportDeviceModel;

public class SupportDeviceDaoToDto {

    public static SupportDeviceModel convert(SupportDevice sd) {

        SupportDeviceModel supportDevice = new SupportDeviceModel();
        supportDevice.setID(sd.getId());
        supportDevice.setName(sd.getName());
        return supportDevice;

    }
}

