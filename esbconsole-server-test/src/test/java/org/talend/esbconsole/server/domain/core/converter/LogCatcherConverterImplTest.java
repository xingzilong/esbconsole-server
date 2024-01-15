package org.talend.esbconsole.server.domain.core.converter;

import org.talend.esbconsole.server.domain.api.model.LogCatcherDTO;
import org.talend.esbconsole.server.domain.repository.entity.LogCatcherDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * {@link LogCatcherConverterImpl} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/17
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class LogCatcherConverterImplTest {

    @InjectMocks
    private LogCatcherConverterImpl logCatcherConverterImpl;

    @Test
    public void do2dto4LogCatcherDOTest() {
        LogCatcherDO logCatcherDO = new LogCatcherDO();
        LogCatcherDTO logCatcherDTO = logCatcherConverterImpl.do2dto(logCatcherDO);
        assertNotNull(logCatcherDTO);
        logCatcherDO = null;
        LogCatcherDTO logCatcherDTO1 = logCatcherConverterImpl.do2dto(logCatcherDO);
        assertNull(logCatcherDTO1);
    }

    @Test
    public void do2dto4LogCatcherDOListTest() {
        ArrayList<LogCatcherDO> logCatcherDOS = new ArrayList<>();
        logCatcherDOS.add(new LogCatcherDO());
        List<LogCatcherDTO> logCatcherDTOS = logCatcherConverterImpl.do2dto(logCatcherDOS);
        assertNotNull(logCatcherDTOS);
        logCatcherDOS = null;
        List<LogCatcherDTO> logCatcherDTOS1 = logCatcherConverterImpl.do2dto(logCatcherDOS);
        assertNull(logCatcherDTOS1);
    }

}
