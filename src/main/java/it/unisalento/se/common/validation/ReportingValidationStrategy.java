package it.unisalento.se.common.validation;

import it.unisalento.se.models.CourseOfStudyNode;
import it.unisalento.se.models.ReportingModel;
import it.unisalento.se.models.ReportingStatusModel;
import it.unisalento.se.models.UserTypeModel;

import java.util.ArrayList;

public class ReportingValidationStrategy implements IValidationStrategy {

    @Override
    public boolean isValid(CourseOfStudyNode node) {
        return brokenRules(node).size() == 0;
    }

    @Override
    public ArrayList<String> brokenRules(CourseOfStudyNode node) {
        ArrayList<String> rules = new ArrayList<>();

        ReportingModel reportingModel = (ReportingModel) node;

        // Reported problems can be created only by professors
        if (reportingModel.getID() == null) {
            if (!reportingModel.getDoneBy().getUserType().equals(UserTypeModel.PROFESSOR)) {
                rules.add("Reported problems can be created only by professors");
            }
        }

        if (reportingModel.getReportingStatus().equals(ReportingStatusModel.REFUSED)) {
            // If it's refused, the note is required
            if (reportingModel.getNote() == null || reportingModel.getNote().trim().length() == 0) {
                rules.add("Note is required when a problem is refused");
            }
        }

        return rules;
    }
}
