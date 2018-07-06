package it.unisalento.se.services;

import it.unisalento.se.converters.daoToDto.ReportingDaoToDto;
import it.unisalento.se.converters.dtoToDao.ReportingDtoToDao;
import it.unisalento.se.dao.Reporting;
import it.unisalento.se.exceptions.ReportingNotFoundException;
import it.unisalento.se.exceptions.ReportingStatusNotSupported;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.iservices.IReportingService;
import it.unisalento.se.models.ReportingModel;
import it.unisalento.se.repositories.ReportingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class ReportingService implements IReportingService {


    @Autowired
    private ReportingRepository repository;

    @Override
    @Transactional(readOnly = true)


    public ReportingModel getReportingByID(Integer ID) throws UserTypeNotSupported, ReportingNotFoundException, ReportingStatusNotSupported {
        try {
            Reporting dao = repository.getOne(ID);
            return ReportingDaoToDto.convert(dao);
        } catch (EntityNotFoundException e) {
            throw new ReportingNotFoundException();
        }
    }


    @Override
    @Transactional
    public ReportingModel saveReporting(ReportingModel model) throws UserTypeNotSupported, ReportingStatusNotSupported {
        // TODO check if user is a professor, otherwise throw a new exception
        Reporting reporting = ReportingDtoToDao.convert(model);
        Reporting saved = repository.save(reporting);
        return ReportingDaoToDto.convert(saved);
    }


}


