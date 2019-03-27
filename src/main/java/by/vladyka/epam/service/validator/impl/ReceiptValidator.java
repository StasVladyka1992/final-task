package by.vladyka.epam.service.validator.impl;

import by.vladyka.epam.entity.Receipt;
import by.vladyka.epam.service.validator.AbstractValidator;

import java.util.Date;
import java.util.regex.Matcher;

import static by.vladyka.epam.service.validator.util.IncorrectDataMessage.*;
import static by.vladyka.epam.service.validator.util.RegexValidationPattern.*;

/**
 * Created by Vladyka Stas
 * on 15.03.2019 at 12:10
 **/
public final class ReceiptValidator extends AbstractValidator {
    public boolean checkClientAddingDataAndSetMessage(int clientId, int remedyId) {
        boolean isClientIdCorrect = checkClientIdAndSetMessage(clientId);
        boolean isRemedyIdCorrect = checkRemedyIdAndSetMessage(remedyId);
        return isClientIdCorrect && isRemedyIdCorrect;
    }

    public boolean checkDoctorAddingDataAndSetMessage(int id, int doctorId, Date expireDate, Date prescriptionDate,
                                                      String message, Receipt.Status status) {
        boolean isIdCorrect = checkId(id);
        boolean isDoctorIdCorrect = checkDoctorIdAndSetMessage(doctorId);
        boolean isExpireDateCorrect = checkDateAndSetMessage(expireDate);
        boolean isPrescriptionDateCorrect = checkDateAndSetMessage(prescriptionDate);
        if ((prescriptionDate.getTime() - expireDate.getTime()) > 0) {
            addIncorrectDataMessage(INCORRECT_RESIDENCE_BETWEEN_DATES);
        }
        boolean isMessageCorrect = checkMessageAndSetMessage(message);
        boolean isStatusCorrect = checkStatusAndSetMessage(status);
        return isIdCorrect && isDoctorIdCorrect && isExpireDateCorrect && isPrescriptionDateCorrect &&
                isMessageCorrect && isStatusCorrect;
    }

    public boolean checkRejectionDataAndSetMessage(int id, int doctorId, String message) {
        boolean isIdCorrect = checkId(id);
        boolean isDoctorIdCorrect = checkDoctorIdAndSetMessage(doctorId);
        boolean isMessageCorrect = checkMessageAndSetMessage(message);
        return isIdCorrect && isMessageCorrect && isDoctorIdCorrect;
    }

    public boolean checkMessageAndSetMessage(String message) {
        Matcher matcher = MESSAGE.matcher(message);
        if (!matcher.find()) {
            addIncorrectDataMessage(INCORRECT_MESSAGE);
            return false;
        }
        return true;
    }

    public boolean checkStatusAndSetMessage(Receipt.Status status) {
        Matcher matcher = STATUS.matcher(String.valueOf(status));
        if (!matcher.find()) {
            addIncorrectDataMessage(INCORRECT_STATUS);
            return false;
        }
        return true;
    }

    public boolean checkDoctorIdAndSetMessage(int doctorId) {
        boolean result = checkId(doctorId);
        if (!result) {
            addIncorrectDataMessage(INCORRECT_DOCTOR_ID);
        }
        return result;
    }

    public boolean checkClientIdAndSetMessage(int clientId) {
        boolean result = checkId(clientId);
        if (!result) {
            addIncorrectDataMessage(INCORRECT_CLIENT_ID);
        }
        return result;
    }

    public boolean checkRemedyIdAndSetMessage(int remedyId) {
        boolean result = checkId(remedyId);
        if (!result) {
            addIncorrectDataMessage(INCORRECT_REMEDY_ID);
        }
        return result;
    }

    public boolean checkDateAndSetMessage(Date date) {
        boolean result = DATE.matcher(String.valueOf(date.getTime())).find() ? true : false;
        if (!result) {
            addIncorrectDataMessage(INCORRECT_DATE);
        }
        return result;
    }

    public boolean checkReceiptIdAndSetMessage(int id) {
        boolean result = checkId(id);
        if (!result) {
            addIncorrectDataMessage(INCORRECT_ID);
        }
        return result;
    }

}
