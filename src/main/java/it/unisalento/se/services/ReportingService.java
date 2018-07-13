package it.unisalento.se.services;

import it.unisalento.se.converters.daoToDto.ReportingDaoToDto;
import it.unisalento.se.converters.dtoToDao.ClassroomDtoToDao;
import it.unisalento.se.converters.dtoToDao.ReportingDtoToDao;
import it.unisalento.se.converters.dtoToDao.UserDtoToDao;
import it.unisalento.se.dao.Classroom;
import it.unisalento.se.dao.Reporting;
import it.unisalento.se.dao.User;
import it.unisalento.se.exceptions.ReportingNotFoundException;
import it.unisalento.se.exceptions.ReportingStatusNotSupported;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.iservices.IFcmService;
import it.unisalento.se.iservices.IReportingService;
import it.unisalento.se.models.ClassroomModel;
import it.unisalento.se.models.ReportingModel;
import it.unisalento.se.models.UserModel;
import it.unisalento.se.repositories.ReportingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportingService implements IReportingService {


    @Autowired
    private ReportingRepository repository;
    @Autowired
    private IFcmService fcmService;

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
        // if there is an ID, it's an update of a reported problem, so a notification has to be sent
        Boolean sendNotification = model.getID() != null;
        // TODO check if user is a professor [or secretariat?], otherwise throw a new exception
        Reporting reporting = ReportingDtoToDao.convert(model);
        Reporting saved = repository.save(reporting);
        if (sendNotification) {

            String token = saved.getUser().getFcmToken();
            if (token != null && !token.trim().equals("")) {
                // The professor has done the access from a mobile device
                try {
                    this.fcmService.sendMessageToUser(
                            "Problem reported has been updated",
                            "Problem to classroom " + model.getClassroom().getName() + " has been updated",
                            token
                    );
                } catch (Exception e) {
                    System.err.println("Unable to send notification");
                    e.printStackTrace();
                }

            }
        }
        return ReportingDaoToDto.convert(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReportingModel> getAllReporting() throws UserTypeNotSupported, ReportingStatusNotSupported {
        List<Reporting> daos = repository.findAll();
        return getListOfModels(daos);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReportingModel> getAllReportingByProfessor(UserModel professor) throws UserTypeNotSupported, ReportingStatusNotSupported {
        User profDao = UserDtoToDao.convert(professor);

        List<Reporting> daos = repository.findAllByUser(profDao);
        return getListOfModels(daos);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReportingModel> getAllReportedProblemsByClassroom(ClassroomModel classroom) throws UserTypeNotSupported, ReportingStatusNotSupported {
        Classroom classDao = ClassroomDtoToDao.convert(classroom);

        List<Reporting> daos = repository.findAllByClassroom(classDao);
        return getListOfModels(daos);
    }

    private List<ReportingModel> getListOfModels(List<Reporting> daos) throws UserTypeNotSupported, ReportingStatusNotSupported {
        List<ReportingModel> models = new ArrayList<>();

        for (Reporting dao : daos) {
            models.add(ReportingDaoToDto.convert(dao));
        }

        return models;
    }
}


