package it.unisalento.se.services;


import it.unisalento.se.converters.daoToDto.LessonDaoToDto;
import it.unisalento.se.converters.dtoToDao.LessonDtoToDao;
import it.unisalento.se.converters.dtoToDao.SubjectDtoToDao;
import it.unisalento.se.dao.Lesson;
import it.unisalento.se.exceptions.LessonNotFoundException;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.iservices.IFcmService;
import it.unisalento.se.iservices.ILessonService;
import it.unisalento.se.models.LessonFilterModel;
import it.unisalento.se.models.LessonModel;
import it.unisalento.se.models.SubjectModel;
import it.unisalento.se.repositories.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class LessonService implements ILessonService {

    @Autowired
    private LessonRepository repository;
    @Autowired
    private IFcmService fcmService;

    @Override
    @Transactional(readOnly = true)
    public LessonModel getLessonByID(Integer ID) throws UserTypeNotSupported, LessonNotFoundException {
        try {
            Lesson dao = repository.getOne(ID);
            return LessonDaoToDto.convert(dao);
        } catch (EntityNotFoundException e) {
            throw new LessonNotFoundException();
        }
    }


    @Override
    @Transactional
    public LessonModel saveLesson(LessonModel model) throws UserTypeNotSupported {
        Boolean sendNotification = false;
        if (model.getID() != null) {
            Lesson oldLesson = repository.getOne(model.getID());
            // Send a notification only if something changed
            sendNotification = !(oldLesson.getClassroom() != null &&
                    oldLesson.getClassroom().getId().equals(model.getClassroom().getID()) &&
                    oldLesson.getTimeslot().getStartTime().equals(model.getTimeSlot().getStartTime()) &&
                    oldLesson.getTimeslot().getEndTime().equals(model.getTimeSlot().getEndTime()));
        }
        // TODO check if user is a professor, otherwise throw a new exception
        Lesson lesson = LessonDtoToDao.convert(model);
        Lesson saved = repository.save(lesson);
        if (sendNotification) {
            try {
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
                String subjectName = model.getSubject().getName();
                String title = "Lesson of " + subjectName + " changed";
                String body = "The lesson is at " + df.format(model.getTimeSlot().getStartTime()) + " in " + model.getClassroom().getName();
                // Send message to students
                fcmService.sendMessageToTopic(
                        title,
                        body,
                        subjectName.replaceAll(" ", "")
                );
                // Send message to professor
                String token = saved.getSubject().getUser().getFcmToken();
                if (token != null && !token.equals("")) {
                    fcmService.sendMessageToUser(
                            title,
                            body,
                            token
                    );
                }
            } catch (Exception e) {
                System.err.println("Cannot send notification");
                e.printStackTrace();
            }
        }
        return LessonDaoToDto.convert(saved);
    }

    @Override
    public List<LessonModel> filterByTimeAndSubject(LessonFilterModel filter) throws UserTypeNotSupported {
        List<Lesson> daos = repository.findByTimeAndSubject(
                filter.getStartTime().getStartTime(),
                filter.getEndTime().getEndTime(),
                filter.getSubject().getID());

        List<LessonModel> models = new ArrayList<>();
        for (Lesson dao : daos) {
            models.add(LessonDaoToDto.convert(dao));
        }
        return models;
    }



    @Override
    public List<LessonModel> filterByTimeAndCourseOfStudy(LessonFilterModel filter) throws UserTypeNotSupported {
        List<Lesson> daos = repository.findByTimeAndCourseOfStudy(
                filter.getStartTime().getStartTime(),
                filter.getEndTime().getEndTime(),
                filter.getCourseOfStudy().getID());

        List<LessonModel> models = new ArrayList<>();
        for (Lesson dao : daos) {
            models.add(LessonDaoToDto.convert(dao));
        }
        return models;
    }



    @Override
    public List<LessonModel> getLessonsBySubjects(SubjectModel subject) throws UserTypeNotSupported {
        List<Lesson> daos = repository.findBySubject(SubjectDtoToDao.convert(subject));
        List<LessonModel> models = new ArrayList<>();
        for (Lesson dao : daos) {
            models.add(LessonDaoToDto.convert(dao));
        }
        return models;
    }

}
