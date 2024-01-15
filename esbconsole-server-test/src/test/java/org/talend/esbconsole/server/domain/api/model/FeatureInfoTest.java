package org.talend.esbconsole.server.domain.api.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link FeatureInfo}单元测试
 *
 * @author xingzilong
 * @date 2023/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class FeatureInfoTest {

    public void init(FeatureInfo info) {

        info.setBlacklisted(true);
        info.setBundleInfoList(null);
        info.setBundles(null);
        info.setConfigurationFiles("test");
        info.setConfigurations("kst");
        info.setDependencies("sky");
        info.setInstalled(true);
        info.setName("esb");
        info.setRequired(true);
        info.setVersion("v1");
    }

    @Test
    public void test() {
        FeatureInfo dto = new FeatureInfo();
        dto.hashCode();
        assertFalse(dto.equals(null));
        assertTrue(dto.equals(dto));
        FeatureInfo dtos = new FeatureInfo();
        assertTrue(dto.equals(dtos));
        init(dto);
        assertFalse(dto.equals(dtos));
        dto.hashCode();
        dto.toString();
        init(dtos);
        FeatureInfo tas = new FeatureInfo(true, null, true, "test", true, "v1");
        //equals方法未完全覆盖
        assertTrue(dto.equals(dtos));
        dtos.setVersion("v2");
        assertFalse(dto.equals(dtos));

        dtos.setVersion("v1");
        dtos.setBlacklisted(false);
        assertFalse(dto.equals(dtos));

        dtos.setBlacklisted(true);
        dtos.setInstalled(false);
        assertFalse(dto.equals(dtos));

        dtos.setInstalled(true);
        dtos.setBundles(Arrays.asList("test"));
        assertFalse(dto.equals(dtos));

        dtos.setBundles(null);
        dtos.setConfigurationFiles("ppp");
        assertFalse(dto.equals(dtos));

        dtos.setConfigurationFiles("test");
        dtos.setName("kfc");
        assertFalse(dto.equals(dtos));

        dtos.setName("esb");
        dtos.setRequired(false);
        assertFalse(dto.equals(dtos));
    }
}
