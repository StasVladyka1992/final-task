package by.vladyka.epam.service.validator.impl;

import by.vladyka.epam.service.validator.AbstractValidator;

import java.util.regex.Matcher;

import static by.vladyka.epam.service.validator.util.IncorrectDataMessage.INCORRECT_ID_REMEDY;
import static by.vladyka.epam.service.validator.util.IncorrectDataMessage.INCORRECT_QUANTITY;
import static by.vladyka.epam.service.validator.util.RegexValidationPattern.ID_REMEDY;
import static by.vladyka.epam.service.validator.util.RegexValidationPattern.QUANTITY;

/**
 * Created by Vladyka Stas
 * on 11.03.2019 at 18:17
 **/
public class StoragePositionValidator extends AbstractValidator {
    @Override
    protected StringBuilder getIncorrectDataMessages() {
        return super.getIncorrectDataMessages();
    }

    @Override
    protected void setIncorrectDataMessages(StringBuilder incorrectDataMessages) {
        super.setIncorrectDataMessages(incorrectDataMessages);
    }

    @Override
    protected void addIncorrectDataMessage(String incorrectMessage) {
        super.addIncorrectDataMessage(incorrectMessage);
    }

    public boolean checkIdAndSetMessage(int id) {
        Matcher matcher = ID_REMEDY.matcher(String.valueOf(id));
        if (matcher.find()) {
            return true;
        }
        //TODO изменить название
        addIncorrectDataMessage(INCORRECT_ID_REMEDY);
        return false;
    }

    public boolean checkQuantitty(int quantity) {
        Matcher matcher = QUANTITY.matcher(String.valueOf(quantity));
        if (matcher.find()) {
            return true;
        }
        addIncorrectDataMessage(INCORRECT_QUANTITY);
        return false;
    }


}
