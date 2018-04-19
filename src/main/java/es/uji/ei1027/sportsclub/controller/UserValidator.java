package es.uji.ei1027.sportsclub.controller;

import es.uji.ei1027.sportsclub.model.UserDetails;
import org.jetbrains.annotations.NotNull;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

class UserValidator implements Validator {

    @Override
    public boolean supports(final @NotNull Class<?> clazz) {
        return UserDetails.class.equals(clazz);
    }

    @Override
    public void validate(final @NotNull Object target, final @NotNull Errors errors) {
        final @NotNull UserDetails user = (UserDetails) target;
        if (user.getUsername().trim().isEmpty())
            errors.rejectValue("username", "required", "This field cannot be empty");
        if (user.getPassword().trim().isEmpty())
            errors.rejectValue("password", "required", "This field cannot be empty");
    }
}
