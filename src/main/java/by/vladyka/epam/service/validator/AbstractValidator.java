package by.vladyka.epam.service.validator;

import static by.vladyka.epam.service.validator.util.RegexValidationPattern.ID;

/**
 * Created by Vladyka Stas
 * on 01.03.2019 at 16:30
 **/
public abstract class AbstractValidator {
    private StringBuilder incorrectDataMessages = new StringBuilder();

    public StringBuilder getIncorrectDataMessages() {
        return incorrectDataMessages;
    }

    public void addIncorrectDataMessage(String incorrectMessage) {
        incorrectDataMessages.append(incorrectMessage);
    }

    public boolean checkId(int id) {
        boolean result = ID.matcher(String.valueOf(id)).find() ? true : false;
        return result;
    }
}
