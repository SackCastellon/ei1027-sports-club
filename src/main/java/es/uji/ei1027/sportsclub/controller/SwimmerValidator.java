package es.uji.ei1027.sportsclub.controller;

import es.uji.ei1027.sportsclub.model.Swimmer;
import org.jetbrains.annotations.NotNull;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public final class SwimmerValidator implements Validator {

    @Override
    public boolean supports(final @NotNull Class<?> clazz) {
        return Swimmer.class.equals(clazz);
    }

    @Override
    public void validate(final @NotNull Object target, final @NotNull Errors errors) {
        final @NotNull Swimmer swimmer = (Swimmer) target;
        if (swimmer.getName().trim().isEmpty())
            errors.rejectValue("name", "required", "This field cannot be empty");
        if (swimmer.getAge() <= 15)
            errors.rejectValue("age", "minAge16", "The age of the swimmers must be greater than 15");
    }
}
