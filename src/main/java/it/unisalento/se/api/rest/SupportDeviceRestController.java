package it.unisalento.se.api.rest;


import it.unisalento.se.exceptions.SupportDeviceNotFoundException;
import it.unisalento.se.iservices.ISupportDeviceService;
import it.unisalento.se.models.SupportDeviceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/support-device")
public class SupportDeviceRestController {

    @Autowired
    private ISupportDeviceService supportDeviceService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public SupportDeviceModel getSupportDeviceById(@PathVariable("id") Integer ID) throws SupportDeviceNotFoundException {
        return supportDeviceService.getSupportDeviceByID(ID);

    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public SupportDeviceModel saveSupportDevice(@RequestBody SupportDeviceModel supportdevice) {
        return supportDeviceService.createSupportDevice(supportdevice);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<SupportDeviceModel> getAllSupportDevices() {
        return supportDeviceService.getAll();
    }


}
