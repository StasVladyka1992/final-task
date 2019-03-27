package by.vladyka.epam.service.validator.impl;

import by.vladyka.epam.entity.User;
import by.vladyka.epam.service.validator.AbstractValidator;

import java.util.Map;
import java.util.regex.Matcher;

import static by.vladyka.epam.controller.util.ParameterName.*;
import static by.vladyka.epam.service.validator.util.IncorrectDataMessage.*;
import static by.vladyka.epam.service.validator.util.RegexValidationPattern.*;

/**
 * Created by Vladyka Stas
 * on 18.02.2019 at 13:12
 **/
public final class UserValidator extends AbstractValidator {

    public boolean checkAuthorizationDataAndSetMessage(String email, String password) {
        boolean isEmailCorrect = checkEmailAndSetMessage(email);
        boolean isPasswordCorrect = checkPasswordAndSetMessage(password);
        return isEmailCorrect && isPasswordCorrect;
    }

    public boolean checkRegistrationDataAndSetMessage(Map<String, String> userInfo, User.UserRole role,
                                                      String password) {
        boolean isCommonUpdateAndRegistrationDataCorrect = checkUpdateDataAndSetMessage(userInfo);
        boolean isRoleCorrect = checkRoleAndSetMessage(role);
        boolean isPasswordCorrect = checkPasswordAndSetMessage(password);
        return isCommonUpdateAndRegistrationDataCorrect && isRoleCorrect && isPasswordCorrect;
    }

    public boolean checkUpdateDataAndSetMessage(Map<String, String> userInfo) {
        boolean isEmailCorrect = checkEmailAndSetMessage(userInfo.get(PARAM_NAME_EMAIL));
        boolean isFirstNameCorrect = checkNameAndSetMessage(userInfo.get(PARAM_NAME_FIRST_NAME));
        boolean isLastNameCorrect = checkNameAndSetMessage(userInfo.get(PARAM_NAME_LAST_NAME));
        boolean isPhoneCorrect = checkPhoneAndSetMessage(userInfo.get(PARAM_NAME_PHONE));
        return isEmailCorrect && isFirstNameCorrect &&
                isLastNameCorrect && isPhoneCorrect;
    }

    public boolean checkPasswordAndSetMessage(String password) {
        Matcher matcher = PASSWORD.matcher(password);
        if (matcher.find()) {
            return true;
        }
        addIncorrectDataMessage(INCORRECT_PASSWORD);
        return false;
    }

    public boolean checkEmailAndSetMessage(String email) {
        Matcher matcher = EMAIL.matcher(email);
        if (matcher.find()) {
            return true;
        }
        addIncorrectDataMessage(INCORRECT_EMAIL);
        return false;
    }

    public boolean checkNameAndSetMessage(String firstName) {
        Matcher matcher = USER_NAME.matcher(firstName);
        if (matcher.find()) {
            return true;
        } else {
            if (!getIncorrectDataMessages().toString().contains(INCORRECT_USER_NAME)) {
                addIncorrectDataMessage(INCORRECT_USER_NAME);
            }
        }
        return false;
    }

    public boolean checkRoleAndSetMessage(User.UserRole role) {
        Matcher matcher = ROLE.matcher(role.toString());
        if (matcher.find()) {
            return true;
        }
        addIncorrectDataMessage(INCORRECT_ROLE);
        return false;
    }

    public boolean checkPhoneAndSetMessage(String phone) {
        Matcher matcher = PHONE.matcher(phone);
        if (matcher.find()) {
            return true;
        }
        addIncorrectDataMessage(INCORRECT_PHONE);
        return false;
    }
}










