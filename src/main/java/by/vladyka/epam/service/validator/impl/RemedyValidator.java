package by.vladyka.epam.service.validator.impl;

import by.vladyka.epam.service.validator.AbstractValidator;

import java.util.regex.Matcher;

import static by.vladyka.epam.service.validator.util.IncorrectDataMessage.*;
import static by.vladyka.epam.service.validator.util.RegexValidationPattern.*;

/**
 * Created by Vladyka Stas
 * on 26.02.2019 at 2:00
 **/
public final class RemedyValidator extends AbstractValidator {
    public boolean checkRemedyAddingDataAndSetMessage(String name, String description, double price,
                                                      boolean receiptRequired) {
        boolean isNameCorrect = checkNameAndSetMessage(name);
        boolean isDescriptionCorrect = checkDescriptionAndSetMessage(description);
        boolean isPriceCorrect = checkPriceAndSetMessage(price);
        boolean isReceiptRequiredCorrect = checkReceiptRequiredAndSetMessage(receiptRequired);
        return isNameCorrect && isDescriptionCorrect && isPriceCorrect && isReceiptRequiredCorrect;
    }

    public boolean checkNameAndSetMessage(String name) {
        Matcher matcher = REMEDY_NAME.matcher(name);
        if (matcher.find()) {
            return true;
        }
        if (!getIncorrectDataMessages().toString().contains(INCORRECT_REMEDY_NAME)) {
            addIncorrectDataMessage(INCORRECT_REMEDY_NAME);
        }
        return false;
    }

    public boolean checkPriceAndSetMessage(double price) {
        Matcher matcher = PRICE.matcher(String.valueOf(price));
        if (matcher.find()) {
            return true;
        }
        addIncorrectDataMessage(INCORRECT_PRICE);
        return false;
    }

    public boolean checkReceiptRequiredAndSetMessage(boolean receiptRequired) {
        Matcher matcher = RECEIPT.matcher(String.valueOf(receiptRequired));
        if (matcher.find()) {
            return true;
        }
        addIncorrectDataMessage(INCORRECT_RECEIPT_REQUIRED);
        return false;
    }

    public boolean checkDescriptionAndSetMessage(String description) {
        Matcher matcher = DESCRIPTION.matcher(description);
        if (matcher.find()) {
            return true;
        }
        if (!getIncorrectDataMessages().toString().contains(INCORRECT_DESCRIPTION)) {
            addIncorrectDataMessage(INCORRECT_DESCRIPTION);
        }
        return false;
    }

}
