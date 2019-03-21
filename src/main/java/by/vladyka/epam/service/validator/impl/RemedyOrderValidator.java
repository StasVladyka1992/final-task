package by.vladyka.epam.service.validator.impl;

import by.vladyka.epam.service.validator.AbstractValidator;

import static by.vladyka.epam.service.validator.util.IncorrectDataMessage.INCORRECT_ID;

/**
 * Created by Vladyka Stas
 * on 21.03.2019 at 12:39
 **/
public class RemedyOrderValidator extends AbstractValidator {

    public boolean checkRemedyOrderIdAndSetMessage(int id) {
        boolean result = checkId(id);
        if (!result) {
            addIncorrectDataMessage(INCORRECT_ID);
        }
        return result;
    }
}
