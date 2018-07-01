package it.unisalento.se.services;

import it.unisalento.se.dao.ReportingStatus;
import it.unisalento.se.exceptions.ReportingStatusNotSupported;
import it.unisalento.se.iservices.IReportingStatusService;
import it.unisalento.se.repositories.ReportingStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class ReportingStatusService implements IReportingStatusService {
    @Autowired
    private ReportingStatusRepository reportingStatusRepository;

    @Override
    @Transactional(readOnly = true)
    public ReportingStatus getReportingStatusDaoByName(String name) throws ReportingStatusNotSupported {
        try {
            return reportingStatusRepository.getReportingStatusByName(name);


        } catch (EntityNotFoundException e) {
            throw new ReportingStatusNotSupported("Reporting Status cannot be " + name);
        }
    }

}

