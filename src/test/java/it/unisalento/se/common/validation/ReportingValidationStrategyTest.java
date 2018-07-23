package it.unisalento.se.common.validation;

import it.unisalento.se.models.ReportingModel;
import it.unisalento.se.models.ReportingStatusModel;
import it.unisalento.se.models.UserModel;
import it.unisalento.se.models.UserTypeModel;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ReportingValidationStrategyTest {

    @Test
    public void brokenRules() {
        ReportingModel model = new ReportingModel();
        UserModel doneBy = new UserModel();
        doneBy.setUserType(UserTypeModel.STUDENT);
        model.setDoneBy(doneBy);
        model.setReportingStatus(ReportingStatusModel.REFUSED);

        ReportingValidationStrategy validationStrategy = new ReportingValidationStrategy();


        List<String> messages = validationStrategy.brokenRules(model);

        assertEquals(2, messages.size());
        assertEquals("Reported problems can be created only by professors", messages.get(0));
        assertEquals("Note is required when a problem is refused", messages.get(1));

        doneBy.setUserType(UserTypeModel.PROFESSOR);
        model.setDoneBy(doneBy);

        messages = validationStrategy.brokenRules(model);
        assertEquals(1, messages.size());
        assertEquals("Note is required when a problem is refused", messages.get(0));

        model.setID(1);
        messages = validationStrategy.brokenRules(model);
        assertEquals(1, messages.size());
        assertEquals("Note is required when a problem is refused", messages.get(0));

        model.setNote("Hi");
        messages = validationStrategy.brokenRules(model);
        assertEquals(0, messages.size());

        model.setNote("");
        messages = validationStrategy.brokenRules(model);
        assertEquals(1, messages.size());
        assertEquals("Note is required when a problem is refused", messages.get(0));

        model.setReportingStatus(ReportingStatusModel.RECEIVED);
        messages = validationStrategy.brokenRules(model);
        assertEquals(0, messages.size());
    }

    @Test
    public void isValid() {
        ReportingModel model = new ReportingModel();
        UserModel doneBy = new UserModel();
        doneBy.setUserType(UserTypeModel.STUDENT);
        model.setDoneBy(doneBy);
        model.setReportingStatus(ReportingStatusModel.REFUSED);

        ReportingValidationStrategy validationStrategy = new ReportingValidationStrategy();
        Boolean result = validationStrategy.isValid(model);

        assertFalse(result);

        doneBy.setUserType(UserTypeModel.PROFESSOR);
        model.setDoneBy(doneBy);

        result = validationStrategy.isValid(model);
        assertFalse(result);


        model.setID(1);
        result = validationStrategy.isValid(model);
        assertFalse(result);


        model.setNote("Hi");
        result = validationStrategy.isValid(model);
        assertTrue(result);

        model.setNote("");
        result = validationStrategy.isValid(model);
        assertFalse(result);


        model.setReportingStatus(ReportingStatusModel.RECEIVED);
        result = validationStrategy.isValid(model);
        assertTrue(result);

    }
}