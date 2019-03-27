package by.vladyka.epam.service.validator;

import static by.vladyka.epam.service.validator.util.RegexValidationPattern.ID;

/**
 * Created by Vladyka Stas
 * on 01.03.2019 at 16:30
 **/
public abstract class AbstractValidator {
    private StringBuffer incorrectDataMessages = new StringBuffer();

    public StringBuffer getIncorrectDataMessages() {
        return incorrectDataMessages;
    }

    public void addIncorrectDataMessage(String incorrectMessage) {
        incorrectDataMessages.append(incorrectMessage);
    }

    public boolean checkId(int id) {
        return ID.matcher(String.valueOf(id)).find() ? true : false;
    }
}
