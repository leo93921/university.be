package it.unisalento.se.services;


import it.unisalento.se.converters.daoToDto.ClassroomDaoToDto;
import it.unisalento.se.converters.dtoToDao.ClassroomDtoToDao;
import it.unisalento.se.dao.Classroom;
import it.unisalento.se.dao.SupportDevice;
import it.unisalento.se.exceptions.ClassroomNotFoundException;
import it.unisalento.se.exceptions.EntityNotDeletableException;
import it.unisalento.se.iservices.IClassroomService;
import it.unisalento.se.models.ClassroomModel;
import it.unisalento.se.repositories.ClassroomRepository;
import it.unisalento.se.repositories.SupportDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClassroomService implements IClassroomService {
    @Autowired
    private ClassroomRepository classroomRepository;
    @Autowired
    private SupportDeviceRepository supportDeviceRepository;

    @Override
    @Transactional
    public ClassroomModel createClassroom(ClassroomModel classroom) {
        Classroom dao = ClassroomDtoToDao.convert(classroom);

        for (SupportDevice d : dao.getSupportDevices()) {
            if (d.getId() == null) {
                supportDeviceRepository.save(d);
            }
        }

        dao = classroomRepository.save(dao);
        return ClassroomDaoToDto.convert(dao);
    }


    @Override
    @Transactional(readOnly = true)
    public ClassroomModel getClassroomByID(Integer ID) throws ClassroomNotFoundException {

        try {
            Classroom classroom = classroomRepository.getOne(ID);
            return ClassroomDaoToDto.convert(classroom);
        } catch (EntityNotFoundException e) {
            throw new ClassroomNotFoundException();
        }
    }

    @Override
    public List<ClassroomModel> getAllClassrooms() {
        List<Classroom> daos = classroomRepository.findAll();

        List<ClassroomModel> models = new ArrayList<>();
        for (Classroom dao : daos) {
            models.add(ClassroomDaoToDto.convert(dao));
        }
        return models;
    }

    @Override
    public Boolean deleteClassroom(Integer ID) throws ClassroomNotFoundException, EntityNotDeletableException {
        try {
            Classroom classroom = classroomRepository.getOne(ID);
            try {
                classroomRepository.delete(classroom);
            } catch (Exception e) {
                throw new EntityNotDeletableException();
            }

            return true;
        } catch (EntityNotFoundException e) {
            throw new ClassroomNotFoundException();
        }
    }
}




