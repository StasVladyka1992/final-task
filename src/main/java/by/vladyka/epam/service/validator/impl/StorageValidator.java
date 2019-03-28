package by.vladyka.epam.service.validator.impl;

import by.vladyka.epam.service.validator.AbstractValidator;

import java.util.regex.Matcher;

import static by.vladyka.epam.service.validator.util.IncorrectDataMessage.INCORRECT_QUANTITY;
import static by.vladyka.epam.service.validator.util.RegexValidationPattern.QUANTITY;

/**
 * Created by Vladyka Stas
 * on 11.03.2019 at 18:17
 **/
public final class StorageValidator extends AbstractValidator {

    public boolean checkAddingDataAndSetMessage(int remedyId, int remedyLeft) {

        boolean isQuantityCorrect = checkQuantityAndSetMessage(remedyLeft);
        boolean isIdCorrect = checkId(remedyId);
        return isQuantityCorrect && isIdCorrect;
    }

    public boolean checkQuantityAndSetMessage(int quantity) {
        Matcher matcher = QUANTITY.matcher(String.valueOf(quantity));
        if (matcher.find()) {
            return true;
        }
        addIncorrectDataMessage(INCORRECT_QUANTITY);
        return false;
    }

}
