package it.unisalento.se.services;

import it.unisalento.se.converters.daoToDto.AcademicYearDaoToDto;
import it.unisalento.se.converters.dtoToDao.AcademicYearDtoToDao;
import it.unisalento.se.dao.AcademicYear;
import it.unisalento.se.exceptions.AcademicYearNotFoundException;
import it.unisalento.se.iservices.IAcademicYearService;
import it.unisalento.se.models.AcademicYearModel;
import it.unisalento.se.repositories.AcademicYearRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AcademicYearService implements IAcademicYearService {

    @Autowired
    private AcademicYearRepository academicYearRepository;

    @Override
    @Transactional
    public AcademicYearModel createAcademicYear(AcademicYearModel academicYear) {
        AcademicYear dao = AcademicYearDtoToDao.convert(academicYear);
        dao = academicYearRepository.save(dao);
        return AcademicYearDaoToDto.convert(dao);
    }

    @Override
    @Transactional(readOnly = true)
    public AcademicYearModel getAcademicYearByID(Integer ID) throws AcademicYearNotFoundException {
        try {
            AcademicYear academicYear = academicYearRepository.getOne(ID);
            return AcademicYearDaoToDto.convert(academicYear);
        } catch (EntityNotFoundException e) {
            throw new AcademicYearNotFoundException();
        }
    }

    @Override
    public List<AcademicYearModel> getAll() {
        List<AcademicYear> daos = academicYearRepository.findAll();
        List<AcademicYearModel> models = new ArrayList<>();

        for (AcademicYear dao : daos) {
            models.add(AcademicYearDaoToDto.convert(dao));
        }

        return models;
    }
}
