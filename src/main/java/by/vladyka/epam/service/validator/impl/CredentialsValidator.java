package by.vladyka.epam.service.validator.impl;

import by.vladyka.epam.service.validator.AbstractValidator;

import java.util.Map;
import java.util.regex.Matcher;


import static by.vladyka.epam.service.validator.util.IncorrectDataMessage.*;
import static by.vladyka.epam.service.validator.util.RegexValidationPattern.*;

/**
 * Created by Vladyka Stas
 * on 18.02.2019 at 13:12
 **/
public final class CredentialsValidator extends AbstractValidator {

    public boolean isAuthorizationDataCorrect(String email, String password) {
        boolean isEmailCorrect = checkEmailAndSetMessage(email);
        boolean isPasswordCorrect = checkPasswordAndSetMessage(password);
        boolean result = isEmailCorrect && isPasswordCorrect;
        return result;
    }

    public boolean isRegistrationDataCorrect(Map<String, String> userData) {
        boolean isEmailCorrect = checkEmailAndSetMessage(userData.get("email"));
        boolean isPasswordCorrect = checkPasswordAndSetMessage(userData.get("password"));
        boolean isFirstNameCorrect = checkNameAndSetMessage(userData.get("firstName"));
        boolean isLastNameCorrect = checkNameAndSetMessage(userData.get("lastName"));
        boolean isPhoneCorrect = checkPhoneAndSetMessage(userData.get("phone"));
        boolean isRoleCorrect = checkRoleAndSetMessage(userData.get("role"));
        boolean result = isEmailCorrect && isPasswordCorrect && isFirstNameCorrect &&
                isLastNameCorrect && isPhoneCorrect && isEmailCorrect && isRoleCorrect;
        return result;
    }

    private boolean checkPasswordAndSetMessage(String password) {
        Matcher matcher = PASSWORD.matcher(password);
        if (matcher.find()) {
            return true;
        } else {
            getIncorrectDataMessages().append(INCORRECT_PASSWORD);
            return false;
        }
    }

    private boolean checkEmailAndSetMessage(String email) {
        Matcher matcher = EMAIL_REGEX.matcher(email);
        if (matcher.find()) {
            return true;
        } else {
            getIncorrectDataMessages().append(INCORRECT_EMAIL);
            return false;
        }
    }

    private boolean checkNameAndSetMessage(String firstName) {
        Matcher matcher = USER_NAME.matcher(firstName);
        if (matcher.find()) {
            return true;
        } else {
            if (!getIncorrectDataMessages().toString().contains(INCORRECT_USER_NAME)) {
                getIncorrectDataMessages().append(INCORRECT_USER_NAME);
            }
            return false;
        }
    }

    private boolean checkRoleAndSetMessage(String role) {
        Matcher matcher = ROLE.matcher(role);
        if (matcher.find()) {
            return true;
        }
        getIncorrectDataMessages().append(INCORRECT_ROLE);
        return false;
    }

    private boolean checkPhoneAndSetMessage(String phone) {
        Matcher matcher = PHONE.matcher(phone);
        if (matcher.find()) {
            return true;
        }
        getIncorrectDataMessages().append(INCORRECT_PHONE);
        return false;
    }

    public StringBuilder getIncorrectMessages() {
        return getIncorrectDataMessages();
    }

}










