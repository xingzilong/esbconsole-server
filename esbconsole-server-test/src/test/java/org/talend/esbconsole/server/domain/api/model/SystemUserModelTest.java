package org.talend.esbconsole.server.domain.api.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link SystemUserModel} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class SystemUserModelTest {

    public void init(SystemUserModel systemUserModel) {
        systemUserModel.setId("test-id");
        systemUserModel.setName("test-name");
        systemUserModel.setUserName("test-username");
    }

    @Test
    public void test() {

        SystemUserModel systemUserModel = new SystemUserModel();
        SystemUserModel systemUserModel1 = new SystemUserModel();
        SystemUserModel systemUserModel3 = new SystemUserModel("test", "test", "test");

        assertTrue(systemUserModel.equals(systemUserModel1));

        systemUserModel.hashCode();
        systemUserModel.toString();
        init(systemUserModel);
        systemUserModel.hashCode();

        SystemUserModel systemUserModel2 = systemUserModel;
        assertTrue(systemUserModel2.equals(systemUserModel));

        assertFalse(systemUserModel1.equals(systemUserModel));
        assertFalse(systemUserModel1.equals(null));

        init(systemUserModel1);
        assertTrue(systemUserModel1.equals(systemUserModel));

        systemUserModel1.setId("test-ids");
        assertFalse(systemUserModel1.equals(systemUserModel));

        init(systemUserModel1);
        systemUserModel1.setUserName("test-systemnames");
        assertFalse(systemUserModel1.equals(systemUserModel));

        init(systemUserModel1);
        systemUserModel1.setName("test-systemnames");
        assertFalse(systemUserModel1.equals(systemUserModel));
    }
}
