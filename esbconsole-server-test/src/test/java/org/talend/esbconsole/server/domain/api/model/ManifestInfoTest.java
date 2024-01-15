package org.talend.esbconsole.server.domain.api.model;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link ManifestInfo} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/25
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class ManifestInfoTest {

    private void init(ManifestInfo manifestInfo) {
        manifestInfo.setBundleVersion("BundleVersion");
        manifestInfo.setBundleSymbolicName("BundleSymbolicName");
    }

    @Test
    public void test() {
        ManifestInfo manifestInfo1 = new ManifestInfo();
        ManifestInfo manifestInfo2 = manifestInfo1;
        ManifestInfo manifestInfo3 = new ManifestInfo("test", "test");

        assertTrue(manifestInfo1.equals(manifestInfo2));

        manifestInfo2 = new ManifestInfo();
        assertTrue(manifestInfo2.equals(manifestInfo1));
        assertFalse(manifestInfo2.equals(null));

        init(manifestInfo1);
        manifestInfo1.toString();
        manifestInfo1.hashCode();

        init(manifestInfo2);
        assertTrue(manifestInfo2.equals(manifestInfo1));

        manifestInfo2.setBundleVersion("BundleVersionasdasda");
        assertFalse(manifestInfo2.equalsValue(manifestInfo1));
        assertFalse(manifestInfo2.equals(manifestInfo1));

        init(manifestInfo2);
        manifestInfo2.setBundleSymbolicName("BundleSymbolicNameasdasd");
        assertFalse(manifestInfo2.equalsValue(manifestInfo1));
        assertFalse(manifestInfo2.equals(manifestInfo1));

    }
}
