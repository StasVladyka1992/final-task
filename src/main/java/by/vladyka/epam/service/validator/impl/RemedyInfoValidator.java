package by.vladyka.epam.service.validator.impl;

import by.vladyka.epam.controller.util.ParameterName;
import by.vladyka.epam.service.validator.AbstractValidator;

import java.util.Map;
import java.util.regex.Matcher;

import static by.vladyka.epam.service.validator.util.IncorrectDataMessage.*;
import static by.vladyka.epam.service.validator.util.RegexValidationPattern.*;

/**
 * Created by Vladyka Stas
 * on 26.02.2019 at 2:00
 **/
public final class RemedyInfoValidator extends AbstractValidator {

    public boolean isSearchingParametersCorrect(String remedyName) {
        return checkRemedyNameAndSetMessage(remedyName);
    }

    public boolean isRemedyAddingDataCorrect(Map<String, String> remedyData) {
        boolean isNameCorrect = checkRemedyNameAndSetMessage(remedyData.get(ParameterName.REMEDY_NAME));
        boolean isPackingCorrect = checkPackingAndSetMessage(remedyData.get(ParameterName.PACKING));
        boolean isMakerCorrect = checkMakerAndSetMessage(remedyData.get(ParameterName.MAKER));
        boolean isQuantityCorrect = checkQuantityAndSetMessage(remedyData.get(ParameterName.QUANTITY));
        boolean isPriceCorrect = checkPriceAndSetMessage(remedyData.get(ParameterName.PRICE));
        boolean isReceiptCorrect = checkReceiptAndSetMessage(remedyData.get(ParameterName.RECEIPT));
        boolean isRemedyDataCorrect = isNameCorrect && isPackingCorrect && isMakerCorrect &&
                isQuantityCorrect && isPriceCorrect && isReceiptCorrect;
        return isRemedyDataCorrect;
    }

    private boolean checkRemedyNameAndSetMessage(String name) {
        Matcher matcher = REMEDY_NAME.matcher(name);
        if (matcher.find()) {
            return true;
        }
        incorrectDataMessages.append(INCORRECT_REMEDY_NAME);
        return false;
    }

    private boolean checkPackingAndSetMessage(String packing) {
        Matcher matcher = PACKING.matcher(packing);
        if (matcher.find()) {
            return true;
        }
        incorrectDataMessages.append(INCORRECT_PACKING);
        return false;
    }

    private boolean checkMakerAndSetMessage(String maker) {
        Matcher matcher = MAKER.matcher(maker);
        if (matcher.find()) {
            return true;
        }
        incorrectDataMessages.append(INCORRECT_MAKER);
        return false;
    }

    private boolean checkQuantityAndSetMessage(String quantity) {
        Matcher matcher = QUANTITY.matcher(quantity);
        if (matcher.find()) {
            return true;
        }
        incorrectDataMessages.append(INCORRECT_QUANTITY);
        return false;
    }

    private boolean checkPriceAndSetMessage(String price) {
        Matcher matcher = PRICE.matcher(price);
        if (matcher.find()) {
            return true;
        }
        incorrectDataMessages.append(INCORRECT_PRICE);
        return false;
    }

    private boolean checkReceiptAndSetMessage(String receipt) {
        Matcher matcher = RECEIPT.matcher(receipt);
        if (matcher.find()) {
            return true;
        }
        incorrectDataMessages.append(INCORRECT_RECEIPT);
        return false;
    }

    public StringBuilder getIncorrectMessages() {
        return incorrectDataMessages;
    }
}
