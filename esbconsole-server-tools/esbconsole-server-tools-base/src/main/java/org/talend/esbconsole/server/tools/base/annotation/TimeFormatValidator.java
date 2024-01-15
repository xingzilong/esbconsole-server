package org.talend.esbconsole.server.tools.base.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 请求参数时间校验
 *
 * @author xingzilong
 * @create: 2023/08/31
 **/
public class TimeFormatValidator implements ConstraintValidator<TimeFormat, String> {

    private static final String[] TIME_FORMATS = {
            "yyyy-MM-dd HH:mm:ss",
            "yyyy-MM-dd HH:mm",
            "yyyy-MM-dd HH",
            "yyyy-MM-dd",
            // 几乎不会出现一下类型的时间格式
//            "yy-MM-dd HH:mm:ss",
//            "yy-MM-dd HH:mm",
//            "yy-MM-dd HH",
//            "yy-MM-dd",
//            "yy-MM",
//            "yy",
//            "MM-dd HH:mm:ss",
//            "MM-dd HH:mm",
//            "MM-dd HH",
//            "MM-dd",
//            "dd HH:mm:ss",
//            "dd HH:mm",
//            "dd HH",
//            "HH:mm:ss",
//            "HH:mm",
    };

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 允许该时间参数为空
        if (value == null || value.isEmpty()) {
            return true;
        }
        boolean validFormat = false;
        for (String format : TIME_FORMATS) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            sdf.setLenient(false);
            try {
                sdf.parse(value);
                validFormat = true;
                break;
            } catch (ParseException ignore) {
                // 当前格式不匹配，尝试下一个格式
            }
        }
        return validFormat;
    }
}


