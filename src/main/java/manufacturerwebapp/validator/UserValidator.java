package manufacturerwebapp.validator;

import manufacturerwebapp.model.User;
import manufacturerwebapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        //email
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Required");
        if (user.getEmail().contains(" ") || !user.getEmail().contains("@")) {
            errors.rejectValue("email", "Size.userForm.email");
        }


        if (userService.findByEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "Duplicate.userForm.email");
        }

        //password
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
        if (user.getPassword().length() < 3 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        //firstName
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "Required");
        if (user.getFirstName().length() < 3 || user.getFirstName().length() > 32) {
            errors.rejectValue("firstName", "Size.userForm.firstName");
        }

        //lastName
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "Required");
        if (user.getLastName().length() < 3 || user.getLastName().length() > 32) {
            errors.rejectValue("lastName", "Size.userForm.lastName");
        }
    }
}
