package it.unisalento.se.iservices;

import it.unisalento.se.exceptions.SupportDeviceNotFoundException;
import it.unisalento.se.models.SupportDeviceModel;

import java.util.List;

public interface ISupportDeviceService {


    SupportDeviceModel createSupportDevice(SupportDeviceModel supportDevice);

    SupportDeviceModel getSupportDeviceByID(Integer ID) throws SupportDeviceNotFoundException;

    List<SupportDeviceModel> getAll();

}
