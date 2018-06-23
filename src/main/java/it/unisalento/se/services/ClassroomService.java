package it.unisalento.se.services;


import it.unisalento.se.converters.daoToDto.ClassroomDaoToDto;
import it.unisalento.se.converters.dtoToDao.ClassroomDtoToDao;
import it.unisalento.se.dao.Classroom;
import it.unisalento.se.models.ClassroomModel;
import it.unisalento.se.exceptions.ClassroomNotFoundException;
import it.unisalento.se.iservices.IClassroomService;
import it.unisalento.se.repositories.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class ClassroomService implements IClassroomService {
    @Autowired private ClassroomRepository classroomRepository;

    @Override @Transactional
    public ClassroomModel createClassroom(ClassroomModel classroom) {
        Classroom dao = ClassroomDtoToDao.convert(classroom);
        dao = classroomRepository.save(dao);
        return ClassroomDaoToDto.convert(dao);
    }



    @Override @Transactional(readOnly=true)
    public ClassroomModel getClassroomByID(Integer ID) throws ClassroomNotFoundException {

        try {
            Classroom classroom = classroomRepository.getOne(ID);
            return ClassroomDaoToDto.convert(classroom);
        } catch (EntityNotFoundException e) {
            throw new ClassroomNotFoundException();

        }

    }
}




