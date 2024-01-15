package org.talend.esbconsole.server.tools.base.annotation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintValidatorContext;

import static org.junit.Assert.assertTrue;

/**
 * {@link TimeFormatValidator} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/16
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TimeFormatValidatorTest {

    @Test
    public void isValidTest() {
        TimeFormatValidator timeFormatValidator = Mockito.spy(TimeFormatValidator.class);
        ConstraintValidatorContext context = Mockito.mock(ConstraintValidatorContext.class);
        assertTrue(timeFormatValidator.isValid(null, context));
        assertTrue(timeFormatValidator.isValid("", context));
        assertTrue(timeFormatValidator.isValid("2021-08-23", context));
    }
}
