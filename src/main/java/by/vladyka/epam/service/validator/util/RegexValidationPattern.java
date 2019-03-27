package by.vladyka.epam.service.validator.util;

import java.util.regex.Pattern;

/**
 * Created by Vladyka Stas
 * on 01.03.2019 at 12:13
 **/
public final class RegexValidationPattern {
    //common
    public static final Pattern ID = Pattern.compile("(^[1-9]?$)|(^[1-9][0-9]+$)");
    //user's pattern
    public static final Pattern EMAIL = Pattern.compile("^[A-Z0-9._]+?@[A-Z0-9_]+?\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE);
    public static final Pattern PASSWORD = Pattern.compile("^[А-яA-Z0-9_.]+$", Pattern.CASE_INSENSITIVE);
    public static final Pattern USER_NAME = Pattern.compile("^[A-ZА-я -]+$", Pattern.CASE_INSENSITIVE);
    public static final Pattern ROLE = Pattern.compile("^(DOCTOR)|(CLIENT)|(PHARMACIST)$",
            Pattern.CASE_INSENSITIVE);
    public static final Pattern PHONE = Pattern.compile("^\\+\\d{3}-\\d{2}-\\d{3}-\\d{2}-\\d{2}$");
    //remedy's pattern
    public static final Pattern REMEDY_NAME = Pattern.compile("^[A-ZA-я _.]++$", Pattern.CASE_INSENSITIVE);
    public static final Pattern PRICE = Pattern.compile("(^\\d{1,4}\\.\\d{1,2}$)++|(^\\d{1,4}$)");
    public static final Pattern RECEIPT = Pattern.compile("^(true)|(false)$");
    //TODO протестить эту регулярку - хреновая регулярка, т.к. она открыта для SQL-инъекции
    public static final Pattern DESCRIPTION = Pattern.compile("^[A-ZА-я _()\",.]++", Pattern.CASE_INSENSITIVE);
    //storage's pattern
    public static final Pattern QUANTITY = Pattern.compile("^[0-9]++$");
    //receipt's pattern
    public static final Pattern DATE = Pattern.compile("^\\d+$");
    public static final Pattern STATUS = Pattern.compile("^(REJECTED)|(APPROVED)|(NONE)|(USED)$");
    public static final Pattern MESSAGE = Pattern.compile("^[A-ZА-я _()\",.]++", Pattern.CASE_INSENSITIVE);

}
