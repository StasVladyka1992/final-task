package by.vladyka.epam.service.validator.impl;

import by.vladyka.epam.service.validator.AbstractValidator;

import java.util.regex.Matcher;

import static by.vladyka.epam.service.validator.util.IncorrectDataMessage.INCORRECT_REMEDY_NAME;
import static by.vladyka.epam.service.validator.util.RegexValidationPattern.REMEDY_NAME;

/**
 * Created by Vladyka Stas
 * on 26.02.2019 at 2:00
 **/
public final class RemedyInfoValidator extends AbstractValidator {

    public boolean isSearchingParametersCorrect(String remedyName) {
        return checkRemedyNameAndSetMessage(remedyName);
    }

    private boolean checkRemedyNameAndSetMessage(String name) {
        Matcher matcher = REMEDY_NAME.matcher(name);
        if (matcher.find()) {
            return true;
        }
        getIncorrectDataMessages().append(INCORRECT_REMEDY_NAME);
        return false;
    }

    public StringBuilder getIncorrectMessages() {
        return getIncorrectDataMessages();
    }
}
