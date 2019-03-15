package by.vladyka.epam.service.validator;

import java.util.regex.Matcher;

import static by.vladyka.epam.service.validator.util.IncorrectDataMessage.INCORRECT_ID;
import static by.vladyka.epam.service.validator.util.RegexValidationPattern.ID;

/**
 * Created by Vladyka Stas
 * on 01.03.2019 at 16:30
 **/
public abstract class AbstractValidator {
    private StringBuilder incorrectDataMessages = new StringBuilder();

    protected StringBuilder getIncorrectDataMessages() {
        return incorrectDataMessages;
    }

    protected void setIncorrectDataMessages(StringBuilder incorrectDataMessages) {
        this.incorrectDataMessages = incorrectDataMessages;
    }

    protected void addIncorrectDataMessage(String incorrectMessage) {
        incorrectDataMessages.append(incorrectMessage);
    }

    protected boolean checkIdAndSetMessage(int id){
        Matcher matcher = ID.matcher(String.valueOf(id));
        if (matcher.find()) {
            return true;
        }
        addIncorrectDataMessage(INCORRECT_ID);
        return false;
    }

    public boolean isIdCorrect(int id){
        return checkIdAndSetMessage(id);
    }
}
