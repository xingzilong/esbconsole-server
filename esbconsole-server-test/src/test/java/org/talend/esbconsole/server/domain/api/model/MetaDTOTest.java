package org.talend.esbconsole.server.domain.api.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link MetaDTO} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class MetaDTOTest {

    public void init(MetaDTO metaDTO) {
        metaDTO.setTitle("tset");
        metaDTO.setRouteLevel(1);
        metaDTO.setIcon("test");
        metaDTO.setNoCache(true);
        metaDTO.setLink("test-rootid");
    }

    @Test
    public void test() {

        MetaDTO metaDTO = new MetaDTO();
        MetaDTO metaDTO1 = new MetaDTO();

        assertTrue(metaDTO.equals(metaDTO1));

        metaDTO.hashCode();
        metaDTO.toString();
        init(metaDTO);
        metaDTO.hashCode();

        MetaDTO metaDTO2 = metaDTO;
        assertTrue(metaDTO2.equals(metaDTO));

        assertFalse(metaDTO1.equals(metaDTO));
        assertFalse(metaDTO1.equals(null));

        init(metaDTO1);
        assertTrue(metaDTO1.equals(metaDTO));

        init(metaDTO1);
        metaDTO1.setTitle("tests");
        assertFalse(metaDTO1.equals(metaDTO));

        metaDTO1.setRouteLevel(2);
        assertFalse(metaDTO1.equals(metaDTO));

        init(metaDTO1);
        metaDTO1.setIcon("tests");
        assertFalse(metaDTO1.equals(metaDTO));

        init(metaDTO1);
        metaDTO1.setNoCache(false);
        assertFalse(metaDTO1.equals(metaDTO));

        init(metaDTO1);
        metaDTO1.setLink("test-rootidsasd");
        assertFalse(metaDTO1.equals(metaDTO));
    }
}
