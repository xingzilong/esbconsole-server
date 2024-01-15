package org.talend.esbconsole.server.domain.api.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link RouteAndAuthorityModel} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class RouteAndAuthorityModelTest {

    public void init(RouteAndAuthorityModel routeAndAuthorityModel) {
        routeAndAuthorityModel.setId(10L);
        routeAndAuthorityModel.setParentId(10L);
        routeAndAuthorityModel.setName("test-pid");
        routeAndAuthorityModel.setRouteName("test-fatherid");
        routeAndAuthorityModel.setRoutePath("test-rootid");
        routeAndAuthorityModel.setRouteComponent("test-rootid");
        routeAndAuthorityModel.setOrderNum(1);
        routeAndAuthorityModel.setAuthorityCode("test");
        routeAndAuthorityModel.setAuthorityKey("test");
        routeAndAuthorityModel.setType("test");
        routeAndAuthorityModel.setDescription("test");
        routeAndAuthorityModel.setCreateTime(LocalDateTime.MIN);
        routeAndAuthorityModel.setUpdateTime(LocalDateTime.MIN);
        routeAndAuthorityModel.setChildren(new ArrayList<>());
    }

    @Test
    public void test() {

        RouteAndAuthorityModel routeAndAuthorityModel = new RouteAndAuthorityModel();
        RouteAndAuthorityModel routeAndAuthorityModel1 = new RouteAndAuthorityModel();

        assertTrue(routeAndAuthorityModel.equals(routeAndAuthorityModel1));

        routeAndAuthorityModel.hashCode();
        routeAndAuthorityModel.toString();
        init(routeAndAuthorityModel);
        routeAndAuthorityModel.hashCode();

        RouteAndAuthorityModel routeAndAuthorityModel2 = routeAndAuthorityModel;
        assertTrue(routeAndAuthorityModel2.equals(routeAndAuthorityModel));

        assertFalse(routeAndAuthorityModel1.equals(routeAndAuthorityModel));
        assertFalse(routeAndAuthorityModel1.equals(null));

        init(routeAndAuthorityModel1);
        assertTrue(routeAndAuthorityModel1.equals(routeAndAuthorityModel));

        init(routeAndAuthorityModel1);
        routeAndAuthorityModel1.setId(100L);
        assertFalse(routeAndAuthorityModel1.equals(routeAndAuthorityModel));

        routeAndAuthorityModel1.setParentId(100L);
        assertFalse(routeAndAuthorityModel1.equals(routeAndAuthorityModel));

        init(routeAndAuthorityModel1);
        routeAndAuthorityModel1.setName("test-pids");
        assertFalse(routeAndAuthorityModel1.equals(routeAndAuthorityModel));

        init(routeAndAuthorityModel1);
        routeAndAuthorityModel1.setRouteName("test-fatherids");
        assertFalse(routeAndAuthorityModel1.equals(routeAndAuthorityModel));

        init(routeAndAuthorityModel1);
        routeAndAuthorityModel1.setRoutePath("test-rootids");
        assertFalse(routeAndAuthorityModel1.equals(routeAndAuthorityModel));

        init(routeAndAuthorityModel1);
        routeAndAuthorityModel1.setRouteComponent("test-rootids");
        assertFalse(routeAndAuthorityModel1.equals(routeAndAuthorityModel));

        init(routeAndAuthorityModel1);
        routeAndAuthorityModel1.setOrderNum(2);
        assertFalse(routeAndAuthorityModel1.equals(routeAndAuthorityModel));

        init(routeAndAuthorityModel1);
        routeAndAuthorityModel1.setAuthorityCode("tests");
        assertFalse(routeAndAuthorityModel1.equals(routeAndAuthorityModel));

        init(routeAndAuthorityModel1);
        routeAndAuthorityModel1.setAuthorityKey("tests");
        assertFalse(routeAndAuthorityModel1.equals(routeAndAuthorityModel));

        init(routeAndAuthorityModel1);
        routeAndAuthorityModel1.setType("tests");
        assertFalse(routeAndAuthorityModel1.equals(routeAndAuthorityModel));

        init(routeAndAuthorityModel1);
        routeAndAuthorityModel1.setDescription("tests");
        assertFalse(routeAndAuthorityModel1.equals(routeAndAuthorityModel));

        init(routeAndAuthorityModel1);
        routeAndAuthorityModel1.setCreateTime(LocalDateTime.now().plusDays(1));
        assertFalse(routeAndAuthorityModel1.equals(routeAndAuthorityModel));

        init(routeAndAuthorityModel1);
        routeAndAuthorityModel1.setUpdateTime(LocalDateTime.now().plusDays(1));
        assertFalse(routeAndAuthorityModel1.equals(routeAndAuthorityModel));

        init(routeAndAuthorityModel1);
        List<RouteAndAuthorityModel> list = new ArrayList<>();
        list.add(new RouteAndAuthorityModel());
        routeAndAuthorityModel1.setChildren(list);
        assertFalse(routeAndAuthorityModel1.equals(routeAndAuthorityModel));
    }
}
