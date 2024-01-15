package org.talend.esbconsole.server.tools.base.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 时间参数格式校验注解
 *
 * @author xingzilong
 * @create: 2023-08-31 15:06
 */
@Documented
@Constraint(validatedBy = TimeFormatValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TimeFormat {

    String message() default "时间参数格式不正确";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
