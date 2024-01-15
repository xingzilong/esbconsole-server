package org.talend.esbconsole.server.tools.common.utils;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * {@link Arith} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/16
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class DateUtilTest {

    @InjectMocks
    DateUtil deDateUtil;

    @Test
    public void getNowDateTest() {
        deDateUtil.getNowDate();
    }

    @Test
    public void getDateTest() {
        deDateUtil.getDate();
    }

    @Test
    public void getTimeTest() {
        deDateUtil.getTime();
    }

    @Test
    public void dateTimeNowTest() {
        deDateUtil.dateTimeNow();
    }

    @Test
    public void dateTimeTest() {
        deDateUtil.dateTime(new Date());
    }

    @Test
    public void dateTimeTest1() {
        deDateUtil.dateTimeNow("YYYY_MM_DD");
    }

    @Test
    public void dateTimeTest2() {
        try {
            deDateUtil.dateTime("", "");

        } catch (Exception e) {
            assertTrue(e instanceof RuntimeException);
        }
    }

    @Test
    public void datePath() {
        deDateUtil.datePath();
    }

    @Test
    public void dateTimeTest3() {
        deDateUtil.dateTime();
    }

    @Test
    public void parseDateTest() {
        deDateUtil.parseDate(null);

        deDateUtil.parseDate("");
    }

    @Test
    public void getServerStartDateTest() {
        deDateUtil.getServerStartDate();

    }

    @SuppressWarnings("deprecation")
    @Test
    public void differentDaysByMillisecondTest() {
        Date date1 = new Date(2000, 7, 12);
        Date date2 = new Date(2000, 7, 22);

        assertEquals(10, deDateUtil.differentDaysByMillisecond(date1, date2), 0);
    }

    @Test
    public void timeDistanceTest() {
        Date endTime = new Date(2010, 2, 12);
        Date startTime = new Date(2000, 7, 22);

        deDateUtil.timeDistance(endTime, startTime);
    }

    @Test
    public void toDateTest() {
        LocalDateTime temporalAccessor = mock(LocalDateTime.class);

        ZonedDateTime zdt = mock(ZonedDateTime.class);
        when(temporalAccessor.atZone(Mockito.any())).thenReturn(zdt);
        Instant instant = mock(Instant.class);
        when(zdt.toInstant()).thenReturn(instant);
        when(instant.toEpochMilli()).thenReturn(456L);

        deDateUtil.toDate(temporalAccessor);
    }

    @Test
    public void toDateTest1() {
        LocalDate temporalAccessor = mock(LocalDate.class);
        LocalDateTime localDateTime = mock(LocalDateTime.class);
        MockedStatic<LocalDateTime> localDateTimeStatic = mockStatic(LocalDateTime.class);
        localDateTimeStatic.when(() -> LocalDateTime.of(Mockito.any(), Mockito.any())).thenReturn(localDateTime);

        ZonedDateTime zdt = mock(ZonedDateTime.class);
        when(localDateTime.atZone(Mockito.any())).thenReturn(zdt);

        Instant instant = mock(Instant.class);
        when(zdt.toInstant()).thenReturn(instant);
        when(instant.toEpochMilli()).thenReturn(456L);


        deDateUtil.toDate(temporalAccessor);
        localDateTimeStatic.close();
    }

}
