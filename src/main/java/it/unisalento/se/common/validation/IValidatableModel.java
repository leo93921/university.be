package it.unisalento.se.common.validation;

import java.util.List;

public interface IValidatableModel {
    Boolean validate(IValidationStrategy strategy);

    List<String> getValidationErrors(IValidationStrategy strategy);
}
