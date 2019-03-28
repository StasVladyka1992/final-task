package by.vladyka.epam.service.validator.impl;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Vladyka Stas
 * on 16.03.2019 at 12:40
 **/
public class UserValidatorTest {
    private List<String> correctEmail = new ArrayList<>();
    private List<String> incorrectEmail = new ArrayList<>();
    private List<String> correctPassword = new ArrayList<>();
    private List<String> incorrectPassword = new ArrayList<>();
    private List<String> correctName = new ArrayList<>();
    private List<String> incorrectName = new ArrayList<>();
    private List<String> correctPhone = new ArrayList<>();
    private List<String> incorrectPhone = new ArrayList<>();
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

        correctName.add("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        correctName.add("Имя-ВтороеИмя");
        correctName.add("afafa afaS");
        correctName.add("Ю");

        incorrectName.add("sss - asasaf");
        incorrectName.add("-addad");
        incorrectName.add(" addad");
        incorrectName.add("addad-");
        incorrectName.add("addad ");
        incorrectName.add("--");
        incorrectName.add(" ");
        incorrectName.add("Sda@");
        incorrectName.add("252dadafs");
        incorrectName.add("dad5afs");

        correctPhone.add("+375-29-111-62-52");
        correctPhone.add("+375-11-111-62-52");

        incorrectPhone.add("+311-29-111-11-11");
        incorrectPhone.add("+311-29-111-11-1");
        incorrectPhone.add("+311-29-111-11-111");

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
            assertFalse(validator.checkPasswordAndSetMessage(password));
        }
    }

    @Test
    public void isNameCorrectTest() {
        for (String name :
                correctName) {
            assertTrue(validator.checkNameAndSetMessage(name));
        }
        for (String name :
                incorrectName) {
            assertFalse(validator.checkNameAndSetMessage(name));
        }
    }

    @Test
    public void isPhoneCorrect() {
        for (String phone :
                correctPhone) {
            assertTrue(validator.checkPhoneAndSetMessage(phone));
        }
        for (String phone :
                incorrectPhone) {
            assertFalse(validator.checkPhoneAndSetMessage(phone));
        }
    }
}