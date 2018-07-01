package it.unisalento.se.services;


import it.unisalento.se.converters.daoToDto.SupportDeviceDaoToDto;
import it.unisalento.se.converters.dtoToDao.SupportDeviceDtoToDao;
import it.unisalento.se.dao.SupportDevice;
import it.unisalento.se.exceptions.SupportDeviceNotFoundException;
import it.unisalento.se.iservices.ISupportDeviceService;
import it.unisalento.se.models.SupportDeviceModel;
import it.unisalento.se.repositories.SupportDeviceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SupportDeviceService implements ISupportDeviceService {
    private SupportDeviceRepository supportDeviceRepository;

    @Override
    @Transactional
    public SupportDeviceModel createSupportDevice(SupportDeviceModel supportDevice) {
        SupportDevice dao = SupportDeviceDtoToDao.convert(supportDevice);
        dao = supportDeviceRepository.save(dao);
        return SupportDeviceDaoToDto.convert(dao);
    }

    @Override
    @Transactional(readOnly = true)
    public SupportDeviceModel getSupportDeviceByID(Integer ID) throws SupportDeviceNotFoundException {
        try {
            SupportDevice supportDevice = supportDeviceRepository.getOne(ID);
            return SupportDeviceDaoToDto.convert(supportDevice);
        } catch (EntityNotFoundException e) {
            throw new SupportDeviceNotFoundException();
        }
    }


    @Override
    public List<SupportDeviceModel> getAll() {
        List<SupportDevice> daos = supportDeviceRepository.findAll();
        List<SupportDeviceModel> models = new ArrayList<>();

        for (SupportDevice dao : daos) {
            models.add(SupportDeviceDaoToDto.convert(dao));
        }

        return models;
    }

}


