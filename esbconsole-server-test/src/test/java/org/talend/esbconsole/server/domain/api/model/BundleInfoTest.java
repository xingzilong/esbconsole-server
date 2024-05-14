package org.talend.esbconsole.server.domain.api.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link BundleInfo}单元测试
 *
 * @author xingzilong
 * @date 2021/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class BundleInfoTest {

    public void init(BundleInfo info) {

        info.setBundleName("test");
        info.setIdentifier(1L);
        info.setLastModified(2L);
        info.setStartLevel(1);
        info.setState("success");
        info.setSymbolicName("esb");
        info.setUpdateLocation("2012");
        info.setVersion("v1");
    }

    @Test
    public void test() {
        BundleInfo dto = new BundleInfo();
        dto.hashCode();
        assertFalse(dto.equals(null));
        assertTrue(dto.equals(dto));
        BundleInfo dtos = new BundleInfo();
        assertTrue(dto.equals(dtos));
        init(dto);
        assertFalse(dto.equals(dtos));
        dto.hashCode();
        dto.toString();
        init(dtos);
        BundleInfo tas = new BundleInfo(1L, "esb", "test", "sss", 1, "test", "test", 2L);
        //equals方法未完全覆盖
        assertTrue(dto.equals(dtos));
        dtos.setVersion("v2");
        assertFalse(dto.equals(dtos));

        dtos.setVersion("v1");
        dtos.setUpdateLocation("sss");
        assertFalse(dto.equals(dtos));

        dtos.setUpdateLocation("2012");
        dtos.setSymbolicName("kst");
        assertFalse(dto.equals(dtos));

    }
}
