package com.github.appdemo.validators;

import com.github.appdemo.constants.AppDemoConstants;
import com.github.appdemo.user.User;
import com.github.appdemo.utilities.AppdemoUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


public class UserRegisterValidator implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User u = (User) o;

        ValidationUtils.rejectIfEmpty(errors, "name", "error.userName.empty");
        ValidationUtils.rejectIfEmpty(errors, "lastName", "error.userLastName.empty");
        ValidationUtils.rejectIfEmpty(errors, "email", "error.userEmail.empty");
        ValidationUtils.rejectIfEmpty(errors, "password", "error.userPassword.empty");

        if(!u.getEmail().equals(null)){
            boolean isMatch = AppdemoUtils.checkEmailOrPassword(AppDemoConstants.EMAIL_PATTERN, u.getEmail());
            if(!isMatch) {
                errors.rejectValue("email", "error.userEmailIsNoMatch");
            }
        }
        if(!u.getPassword().equals(null)){
            boolean isMatch = AppdemoUtils.checkEmailOrPassword(AppDemoConstants.PASSWORD_PATTERN, u.getPassword());
            if(!isMatch){
                errors.rejectValue("password", "error.userPasswordIsNoMatch");
            }
        }
    }
    public void validateEmailExist(User user, Errors errors){
        if(user != null){
            errors.rejectValue("email", "error.userEmailExist");
        }
    }

}
