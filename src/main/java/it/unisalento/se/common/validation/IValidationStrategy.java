package it.unisalento.se.common.validation;

import it.unisalento.se.models.CourseOfStudyNode;

import java.util.List;

public interface IValidationStrategy {
    boolean isValid(CourseOfStudyNode node);

    List<String> brokenRules(CourseOfStudyNode node);
}
