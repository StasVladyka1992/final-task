package by.vladyka.epam.service.validator;

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
}
