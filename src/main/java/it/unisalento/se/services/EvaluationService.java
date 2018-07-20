package it.unisalento.se.services;

import it.unisalento.se.common.Constants;
import it.unisalento.se.converters.daoToDto.DocumentEvaluationDaoToDto;
import it.unisalento.se.converters.daoToDto.LessonEvaluationDaoToDto;
import it.unisalento.se.converters.dtoToDao.DocumentDtoToDao;
import it.unisalento.se.converters.dtoToDao.DocumentEvaluationDtoToDao;
import it.unisalento.se.converters.dtoToDao.LessonDtoToDao;
import it.unisalento.se.converters.dtoToDao.LessonEvaluationDtoToDao;
import it.unisalento.se.dao.DocumentEvaluation;
import it.unisalento.se.dao.LessonEvaluation;
import it.unisalento.se.exceptions.EvaluationNotFoundException;
import it.unisalento.se.exceptions.EvaluationRecipientNotSupported;
import it.unisalento.se.exceptions.ScoreNotValidException;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.iservices.IEvaluationService;
import it.unisalento.se.iservices.IFcmService;
import it.unisalento.se.iservices.IUserService;
import it.unisalento.se.models.*;
import it.unisalento.se.repositories.DocumentEvaluationRepository;
import it.unisalento.se.repositories.LessonEvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EvaluationService implements IEvaluationService {

    @Autowired
    private LessonEvaluationRepository repositoryL;
    @Autowired
    private DocumentEvaluationRepository repositoryD;
    @Autowired
    private IFcmService fcmService;
    @Autowired
    private IUserService userService;

    @Override
    @Transactional(readOnly = true)
    public EvaluationModel getLessonEvaluationbyID(Integer ID) throws EvaluationNotFoundException, UserTypeNotSupported, EvaluationRecipientNotSupported, ScoreNotValidException {
        try {
            LessonEvaluation dao = repositoryL.getOne(ID);
            return LessonEvaluationDaoToDto.convert(dao);

        } catch (EntityNotFoundException e) {
            throw new EvaluationNotFoundException();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public EvaluationModel getDocumentEvaluationbyID(Integer ID) throws EvaluationNotFoundException, UserTypeNotSupported, EvaluationRecipientNotSupported, ScoreNotValidException {
        try {
            DocumentEvaluation dao = repositoryD.getOne(ID);
            return DocumentEvaluationDaoToDto.convert(dao);

        } catch (EntityNotFoundException e) {
            throw new EvaluationNotFoundException();
        }
    }

    @Override
    public List<EvaluationModel> getEvaluationsByLesson(LessonModel lesson) throws UserTypeNotSupported, EvaluationRecipientNotSupported, ScoreNotValidException {
        List<LessonEvaluation> daos = repositoryL.findByLesson(LessonDtoToDao.convert(lesson));
        List<EvaluationModel> models = new ArrayList<>();
        for (LessonEvaluation dao : daos) {
            models.add(LessonEvaluationDaoToDto.convert(dao));
        }
        return models;
    }

    @Override
    public List<EvaluationModel> getEvaluationsByDocument(DocumentModel document) throws UserTypeNotSupported, EvaluationRecipientNotSupported, ScoreNotValidException {
        List<DocumentEvaluation> daos = repositoryD.findByDocument(DocumentDtoToDao.convert(document));
        List<EvaluationModel> models = new ArrayList<>();
        for (DocumentEvaluation dao : daos) {
            models.add(DocumentEvaluationDaoToDto.convert(dao));
        }
        return models;
    }

    @Override
    public boolean checkEvaluation(EvaluationFilterModel filter) throws EvaluationRecipientNotSupported {

        if (filter.getObjectType().equals(Constants.DOCUMENT)) {
            boolean exist = repositoryD.checkDocumentEvaluation(
                    filter.getUser().getId(),
                    filter.getDocument().getId()

            );
            return exist;
        }
        if (filter.getObjectType().equals(Constants.LESSON)) {
            boolean exist = repositoryL.checkLessonEvaluation(
                    filter.getUser().getId(),
                    filter.getLesson().getId()

            );
            return exist;
        } else {
            throw new EvaluationRecipientNotSupported("cannot have information about this object evaluation");
        }


    }


    @Override
    @Transactional
    public EvaluationModel createEvaluation(EvaluationModel model) throws EvaluationRecipientNotSupported, UserTypeNotSupported, ScoreNotValidException {
        UserModel professor;

        // Evaluating lesson
        if (model.getRecipientType().equals(Constants.LESSON)) {
            LessonEvaluation lessEval = LessonEvaluationDtoToDao.convert(model);
            LessonEvaluation saved = repositoryL.save(lessEval);

            professor = model.getRecipientL().getSubject().getProfessor();
            sendNotification(model, professor);

            return LessonEvaluationDaoToDto.convert(saved);
        }

        // Evaluating documents
        if (model.getRecipientType().equals(Constants.DOCUMENT)) {
            DocumentEvaluation docEval = DocumentEvaluationDtoToDao.convert(model);
            DocumentEvaluation saved = repositoryD.save(docEval);

            professor = model.getRecipientD().getLesson().getSubject().getProfessor();
            sendNotification(model, professor);

            return DocumentEvaluationDaoToDto.convert(saved);
        } else {
            throw new EvaluationRecipientNotSupported("You cannot evaluate this object");
        }
    }

    private void sendNotification(EvaluationModel model, UserModel professor) {
        if (professor != null) {
            String token = userService.getFCMToken(professor);
            if (token.trim().length() != 0) {
                String body;
                if (model.getRecipientType().equals(Constants.DOCUMENT)) {
                    body = "A new evaluation has been left for the document \"" + model.getRecipientD().getName() + "\" of " + model.getRecipientD().getLesson().getSubject().getName();
                } else {
                    body = "A new evaluation has been left for a lesson of " + model.getRecipientL().getSubject().getName();

                }

                try {
                    this.fcmService.sendMessageToUser(
                            "New evaluation left",
                            body,
                            token
                    );
                } catch (Exception e) {
                    System.err.println("Cannot send notification");
                    e.printStackTrace();
                }

            }

        }
    }


}





