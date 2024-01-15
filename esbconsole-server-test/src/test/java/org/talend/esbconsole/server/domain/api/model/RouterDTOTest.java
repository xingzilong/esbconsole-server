package org.talend.esbconsole.server.domain.api.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link RouterDTO} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class RouterDTOTest {

    public void init(RouterDTO routerDTO) {
        routerDTO.setName("test");
        routerDTO.setMenuName("test");
        routerDTO.setOrderNumber(1);
        routerDTO.setPath("test-fatherid");
        routerDTO.setHidden(true);
        routerDTO.setRedirect("test-rootid");
        routerDTO.setComponent("test");
        routerDTO.setQuery("test");
        routerDTO.setAlwaysShow(true);
        routerDTO.setMeta(new MetaDTO());
        routerDTO.setChildren(new ArrayList<>());
    }

    @Test
    public void test() {

        RouterDTO routerDTO = new RouterDTO();
        RouterDTO routerDTO1 = new RouterDTO();

        assertTrue(routerDTO.equals(routerDTO1));

        routerDTO.hashCode();
        routerDTO.toString();
        init(routerDTO);
        routerDTO.hashCode();

        RouterDTO routerDTO2 = routerDTO;
        assertTrue(routerDTO2.equals(routerDTO));

        assertFalse(routerDTO1.equals(routerDTO));
        assertFalse(routerDTO1.equals(null));

        init(routerDTO1);
        assertTrue(routerDTO1.equals(routerDTO));

        init(routerDTO1);
        routerDTO1.setName("tests");
        assertFalse(routerDTO1.equals(routerDTO));

        routerDTO1.setMenuName("tests");
        assertFalse(routerDTO1.equals(routerDTO));

        init(routerDTO1);
        routerDTO1.setOrderNumber(2);
        assertFalse(routerDTO1.equals(routerDTO));

        init(routerDTO1);
        routerDTO1.setPath("test-fatheriasdad");
        assertFalse(routerDTO1.equals(routerDTO));

        init(routerDTO1);
        routerDTO1.setHidden(false);
        assertFalse(routerDTO1.equals(routerDTO));

        init(routerDTO1);
        routerDTO1.setRedirect("test-roasdasdaotid");
        assertFalse(routerDTO1.equals(routerDTO));

        init(routerDTO1);
        routerDTO1.setComponent("tests");
        assertFalse(routerDTO1.equals(routerDTO));

        init(routerDTO1);
        routerDTO1.setQuery("tests");
        assertFalse(routerDTO1.equals(routerDTO));

        init(routerDTO1);
        routerDTO1.setAlwaysShow(false);
        assertFalse(routerDTO1.equals(routerDTO));

        init(routerDTO1);
        MetaDTO metaDTO = new MetaDTO();
        metaDTO.setLink("test");
        routerDTO1.setMeta(metaDTO);
        assertFalse(routerDTO1.equals(routerDTO));

        init(routerDTO1);
        List<RouterDTO> list = new ArrayList<>();
        list.add(new RouterDTO());
        routerDTO1.setChildren(list);
        assertFalse(routerDTO1.equals(routerDTO));
    }
}
