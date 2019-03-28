package by.vladyka.epam.service.validator;

import by.vladyka.epam.service.validator.impl.UserValidator;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Vladyka Stas
 * on 16.03.2019 at 11:33
 **/
public class AbstractValidatorTest {
    private List<Integer> correctValues = new ArrayList<>();
    private List<Integer> incorrectValues = new ArrayList<>();

    @Before
    public void setUp() {
        correctValues.add(1);
        correctValues.add(2);
        correctValues.add(3);
        correctValues.add(10);
        correctValues.add(20);
        correctValues.add(2013);

        incorrectValues.add(-12);
        incorrectValues.add(0);
        incorrectValues.add(-3);
        incorrectValues.add(-16);
        incorrectValues.add(00);
        correctValues.add(01);
    }

    @Test
    public void isIdCorrectTest() {
        UserValidator validator = new UserValidator();
        for (Integer value :
                correctValues) {
            assertEquals(true, validator.checkId(value));
        }
        for (Integer value :
                incorrectValues) {
            assertEquals(false, validator.checkId(value));
        }
    }
}