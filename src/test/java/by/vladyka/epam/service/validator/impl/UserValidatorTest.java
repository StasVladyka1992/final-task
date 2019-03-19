package by.vladyka.epam.service.validator.impl;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Vladyka Stas
 * on 16.03.2019 at 12:40
 **/
public class UserValidatorTest {
    private Set<String> correctEmail = new HashSet<>();
    private Set<String> incorrectEmail = new HashSet<>();
    private Set<String> correctPassword = new HashSet<>();
    private Set<String> incorrectPassword = new HashSet<>();
    private static final UserValidator validator = new UserValidator();

    @Before
    public void setUp() {
        correctEmail.add("stavladyk@gmail.com");
        correctEmail.add("nagegdavladyka@gmail.com");
        correctEmail.add("99.a@gmail.commmm");
        correctEmail.add("9_.a@gmail.commmm");

        incorrectEmail.add("stavladyk@gmail.commmmm");
        incorrectEmail.add("nagegdavladykagmail.com");
        incorrectEmail.add("?@@gmail.com");
        incorrectEmail.add("ю.@gmail.com");
        incorrectEmail.add("a.@gmailcom");
        incorrectEmail.add("ss@gmail.c");

        correctPassword.add("sssss");
        correctPassword.add("s");
        correctPassword.add("я");
        correctPassword.add("я0_.");
        correctPassword.add("я9.");
        correctPassword.add("9.");
        correctPassword.add(".");
        correctPassword.add("dldl224_.");

        incorrectPassword.add("sss@ss");
        incorrectPassword.add("?.2");
        incorrectPassword.add("%");
        incorrectPassword.add("я0=");
        incorrectPassword.add("я$12");
        incorrectPassword.add("9.~");
        incorrectPassword.add("oqwoffs!");
        incorrectPassword.add("e--fa");
    }

    @Test
    public void isEmailCorrectTest() {
        for (String email :
                correctEmail) {
            assertTrue(validator.checkEmailAndSetMessage(email));
        }
        for (String email : incorrectEmail) {
            assertFalse(validator.checkEmailAndSetMessage(email));
        }
    }

    @Test
    public void isPasswordCorrectTest() {
        for (String password :
                correctPassword) {
            assertTrue(validator.checkPasswordAndSetMessage(password));
        }
        for (String password :
                incorrectPassword) {
            System.out.println(password);
            assertFalse(validator.checkPasswordAndSetMessage(password));
        }

    }
}