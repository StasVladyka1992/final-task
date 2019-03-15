package by.vladyka.epam.service.validator.impl;

import by.vladyka.epam.service.validator.AbstractValidator;

import java.util.regex.Matcher;

import static by.vladyka.epam.service.validator.util.IncorrectDataMessage.INCORRECT_QUANTITY;
import static by.vladyka.epam.service.validator.util.IncorrectDataMessage.INCORRECT_REMEDY_NAME;
import static by.vladyka.epam.service.validator.util.RegexValidationPattern.QUANTITY;
import static by.vladyka.epam.service.validator.util.RegexValidationPattern.REMEDY_NAME;

/**
 * Created by Vladyka Stas
 * on 11.03.2019 at 18:17
 **/
public class StorageValidator extends AbstractValidator {

    public boolean isAddingDataCorrect(int remedyId, int remedyLeft) {
        boolean isQuantityCorrect = checkQuantityAndSetMessage(remedyLeft);
        boolean isIdCorrect = checkIdAndSetMessage(remedyId);
        return isQuantityCorrect && isIdCorrect;
    }

    private boolean checkQuantityAndSetMessage(int quantity) {
        Matcher matcher = QUANTITY.matcher(String.valueOf(quantity));
        if (matcher.find()) {
            return true;
        }
        addIncorrectDataMessage(INCORRECT_QUANTITY);
        return false;
    }

    public boolean checkNameAndSetMessage(String name) {
        Matcher matcher = REMEDY_NAME.matcher(name);
        if (matcher.find()) {
            return true;
        }
        addIncorrectDataMessage(INCORRECT_REMEDY_NAME);
        return false;
    }

    @Override
    public StringBuilder getIncorrectDataMessages() {
        return super.getIncorrectDataMessages();
    }

    @Override
    public void addIncorrectDataMessage(String incorrectMessage) {
        super.addIncorrectDataMessage(incorrectMessage);
    }
}
