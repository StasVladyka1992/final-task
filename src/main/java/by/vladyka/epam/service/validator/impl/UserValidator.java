package by.vladyka.epam.service.validator.impl;

import by.vladyka.epam.entity.UserRole;
import by.vladyka.epam.service.validator.AbstractValidator;

import java.util.regex.Matcher;

import static by.vladyka.epam.service.validator.util.IncorrectDataMessage.*;
import static by.vladyka.epam.service.validator.util.RegexValidationPattern.*;

/**
 * Created by Vladyka Stas
 * on 18.02.2019 at 13:12
 **/
public final class UserValidator extends AbstractValidator {

    public boolean isAuthorizationDataCorrect(String email, String password) {
        boolean isEmailCorrect = checkEmailAndSetMessage(email);
        boolean isPasswordCorrect = checkPasswordAndSetMessage(password);
        return isEmailCorrect && isPasswordCorrect;
    }

    public boolean isRegistrationDataCorrect(String email, String firstName, String lastName, String password, String phone,
                                             UserRole role) {
        boolean isEmailCorrect = checkEmailAndSetMessage(email);
        boolean isPasswordCorrect = checkPasswordAndSetMessage(password);
        boolean isFirstNameCorrect = checkNameAndSetMessage(firstName);
        boolean isLastNameCorrect = checkNameAndSetMessage(lastName);
        boolean isPhoneCorrect = checkPhoneAndSetMessage(phone);
        boolean isRoleCorrect = checkRoleAndSetMessage(role);
        return isEmailCorrect && isPasswordCorrect && isFirstNameCorrect &&
                isLastNameCorrect && isPhoneCorrect && isRoleCorrect;
    }

    private boolean checkPasswordAndSetMessage(String password) {
        Matcher matcher = PASSWORD.matcher(password);
        if (matcher.find()) {
            return true;
        }
        addIncorrectDataMessage(INCORRECT_PASSWORD);
        return false;
    }

    private boolean checkEmailAndSetMessage(String email) {
        Matcher matcher = EMAIL_REGEX.matcher(email);
        if (matcher.find()) {
            return true;
        }
        addIncorrectDataMessage(INCORRECT_EMAIL);
        return false;
    }

    private boolean checkNameAndSetMessage(String firstName) {
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

    private boolean checkRoleAndSetMessage(UserRole role) {
        Matcher matcher = ROLE.matcher(role.toString());
        if (matcher.find()) {
            return true;
        }
        addIncorrectDataMessage(INCORRECT_ROLE);
        return false;
    }

    private boolean checkPhoneAndSetMessage(String phone) {
        Matcher matcher = PHONE.matcher(phone);
        if (matcher.find()) {
            return true;
        }
        addIncorrectDataMessage(INCORRECT_PHONE);
        return false;
    }

    public StringBuilder getIncorrectDataMessages() {
        return super.getIncorrectDataMessages();
    }

    public void addIncorrectDataMessage(String incorrectMessage) {
        super.addIncorrectDataMessage(incorrectMessage);
    }

}










