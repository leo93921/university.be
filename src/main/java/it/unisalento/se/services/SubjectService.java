package it.unisalento.se.services;

import it.unisalento.se.converters.daoToDto.SubjectDaoToDto;
import it.unisalento.se.converters.dtoToDao.SubjectDtoToDao;
import it.unisalento.se.dao.Subject;
import it.unisalento.se.exceptions.SubjectNotFoundException;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.iservices.ISubjectService;
import it.unisalento.se.models.SubjectModel;
import it.unisalento.se.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class SubjectService implements ISubjectService {

    @Autowired
    private SubjectRepository repository;

    @Override
    @Transactional(readOnly = true)
    public SubjectModel getSubjectByID(Integer ID) throws UserTypeNotSupported, SubjectNotFoundException {
        try {
            Subject dao = repository.getOne(ID);
            return SubjectDaoToDto.convert(dao);
        } catch (EntityNotFoundException e) {
            throw new SubjectNotFoundException();
        }
    }

    @Override
    @Transactional
    public SubjectModel saveSubject(SubjectModel model) throws UserTypeNotSupported {
        // TODO check if user is a professor, otherwise throw a new exception
        Subject subject = SubjectDtoToDao.convert(model);
        Subject saved = repository.save(subject);
        return SubjectDaoToDto.convert(saved);
    }

}
