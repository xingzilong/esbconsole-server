package org.talend.esbconsole.server.domain.core.converter;

import org.talend.esbconsole.server.domain.api.model.StatCatcherDTO;
import org.talend.esbconsole.server.domain.repository.entity.StatCatcherDO;
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
 * {@link StatCatcherConverterImpl} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/17
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class StatCatcherConverterImplTest {

    @InjectMocks
    private StatCatcherConverterImpl statCatcherConverterImpl;

    @Test
    public void do2dto4StatCatcherDOTest() {
        StatCatcherDO statCatcherDO = new StatCatcherDO();
        StatCatcherDTO statCatcherDTO = statCatcherConverterImpl.do2dto(statCatcherDO);
        assertNotNull(statCatcherDTO);
        statCatcherDO = null;
        StatCatcherDTO statCatcherDTO1 = statCatcherConverterImpl.do2dto(statCatcherDO);
        assertNull(statCatcherDTO1);
    }

    @Test
    public void do2dto4StatCatcherDOListTest() {
        ArrayList<StatCatcherDO> statCatcherDOS = new ArrayList<>();
        statCatcherDOS.add(new StatCatcherDO());
        List<StatCatcherDTO> statCatcherDTOS = statCatcherConverterImpl.do2dto(statCatcherDOS);
        assertNotNull(statCatcherDTOS);
        statCatcherDOS = null;
        List<StatCatcherDTO> statCatcherDTOS1 = statCatcherConverterImpl.do2dto(statCatcherDOS);
        assertNull(statCatcherDTOS1);
    }

}
