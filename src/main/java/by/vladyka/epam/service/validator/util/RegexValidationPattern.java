package by.vladyka.epam.service.validator.util;

import java.util.regex.Pattern;

/**
 * Created by Vladyka Stas
 * on 01.03.2019 at 12:13
 **/
public final class RegexValidationPattern {
    //user's pattern
    public static final Pattern EMAIL_REGEX =
            Pattern.compile("^[A-Z0-9._]+?@[A-Z0-9_]+?\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern PASSWORD = Pattern.compile("^[А-яA-Z0-9_.]+$", Pattern.CASE_INSENSITIVE);
    public static final Pattern USER_NAME = Pattern.compile("^[A-ZА-я]+$", Pattern.CASE_INSENSITIVE);
    public static final Pattern ROLE = Pattern.compile("^[dcp]$", Pattern.CASE_INSENSITIVE);
    public static final Pattern PHONE = Pattern.compile("^\\+\\d{3}-\\d{2}-\\d{3}-\\d{2}-\\d{2}$");

    //remedy's pattern
    public static final Pattern REMEDY_NAME = Pattern.compile("^[A-ZA-я _.]++$", Pattern.CASE_INSENSITIVE);
    public static final Pattern PACKING = Pattern.compile("^[A-ZА-я_]++$", Pattern.CASE_INSENSITIVE);
    public static final Pattern MAKER = Pattern.compile("^[A-ZА-я_]++$", Pattern.CASE_INSENSITIVE);
    public static final Pattern QUANTITY = Pattern.compile("^\\d++$");
    public static final Pattern PRICE = Pattern.compile("(^\\d{1,4}\\.\\d{1,2}$)++|(^\\d{1,4}$)");
    public static final Pattern RECEIPT = Pattern.compile("^[yn]$");
}
