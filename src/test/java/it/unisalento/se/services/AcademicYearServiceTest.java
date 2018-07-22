package it.unisalento.se.services;

import it.unisalento.se.dao.AcademicYear;
import it.unisalento.se.exceptions.AcademicYearNotFoundException;
import it.unisalento.se.exceptions.ExamNotFoundException;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.AcademicYearModel;
import it.unisalento.se.repositories.AcademicYearRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AcademicYearServiceTest {

    @Mock
    private AcademicYearRepository academicYearRepository;
    @InjectMocks
    private AcademicYearService academicYearService;


    @Test
    public void createAcademicYear() {

        AcademicYear ay = new AcademicYear();
        ay.setId(1);
        ay.setStartYear(2017);
        ay.setEndYear(2018);


        AcademicYearModel ayM = new AcademicYearModel();
        ayM.setID(1);
        ayM.setStartYear(2017);
        ayM.setEndYear(2018);


        when(academicYearRepository.save(any(AcademicYear.class))).thenReturn(ay);

        AcademicYearModel model1 = academicYearService.createAcademicYear(ayM);


        assertEquals(ay.getId(), model1.getID());
        assertEquals(new Integer(ay.getEndYear()), model1.getEndYear());
        assertEquals(new Integer(ay.getStartYear()), model1.getStartYear());

    }

    @Test
    public void getAcademicYearByID() throws AcademicYearNotFoundException {
        AcademicYear ay = new AcademicYear();
        ay.setId(1);
        ay.setStartYear(2017);
        ay.setEndYear(2018);

        when(academicYearRepository.getOne(1)).thenReturn(ay);

        AcademicYearModel model = academicYearService.getAcademicYearByID(1);
        assertEquals(new Integer(1), model.getID());
        assertEquals(new Integer(ay.getStartYear()), model.getStartYear());
        assertEquals(new Integer(ay.getEndYear()), model.getEndYear());

    }

    @Test(expected = AcademicYearNotFoundException.class)
    public void getay_shouldFail() throws ExamNotFoundException, UserTypeNotSupported, AcademicYearNotFoundException {
        when(academicYearRepository.getOne(10)).thenThrow(new EntityNotFoundException());

        AcademicYearModel model = academicYearService.getAcademicYearByID(10);
    }

    @Test
    public void getAll() {
        AcademicYear ay = new AcademicYear();
        ay.setId(1);
        ay.setStartYear(2017);
        ay.setEndYear(2018);

        AcademicYear ay2 = new AcademicYear();
        ay2.setId(2);
        ay2.setStartYear(2016);
        ay2.setEndYear(2017);


        List<AcademicYear> list = new ArrayList<>();
        list.add(ay);
        list.add(ay2);

        when(academicYearRepository.findAll()).thenReturn(list);

        List<AcademicYearModel> model = academicYearService.getAll();
        assertEquals(new Integer(1), model.get(0).getID());
        assertEquals(new Integer(ay.getStartYear()), model.get(0).getStartYear());
        assertEquals(new Integer(ay.getEndYear()), model.get(0).getEndYear());


    }
}