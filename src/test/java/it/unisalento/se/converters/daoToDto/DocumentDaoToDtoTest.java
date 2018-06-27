package it.unisalento.se.converters.daoToDto;

import it.unisalento.se.common.Constants;
import it.unisalento.se.dao.*;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.DocumentModel;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class DocumentDaoToDtoTest {

    @Test
    public void convert() throws UserTypeNotSupported {

        Document dao = new Document();
        dao.setId(1);
        dao.setNote("A note");
        dao.setName("A name");
        dao.setPublishDate(new Date());
        dao.setLink("A new linlk");

        Lesson lesson = new Lesson(
                new Classroom("01", 1.1, 2.3, null, null, null, null),
                new Subject(
                        new CourseOfStudy(
                                new AcademicYear(2017, 2018),
                                "Computer Engineering"
                        ),
                        new User(
                                new UserType(Constants.PROFESSOR),
                                "Mario",
                                "Rossi",
                                "mario.rossi@example.it"
                        ),
                        "Software Engineering",
                        9,
                        1
                ),
                new Timeslot(new Date(), new Date())
        );
        dao.setLesson(lesson);

        DocumentModel model = DocumentDaoToDto.convert(dao);

        assertEquals(dao.getId(), model.getID());
        assertEquals(dao.getPublishDate(), model.getPublishDate());
        assertEquals(dao.getName(), model.getName());
        assertEquals(dao.getNote(), model.getNote());
        assertEquals(dao.getLink(), model.getLink());
    }
}