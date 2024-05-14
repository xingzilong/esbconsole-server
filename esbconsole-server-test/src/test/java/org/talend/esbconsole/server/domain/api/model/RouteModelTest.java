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
 * {@link RouteModel} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class RouteModelTest {

    public void init(RouteModel routeModel) {
        routeModel.setId(10L);
        routeModel.setParentId(10L);
        routeModel.setName("test-pid");
        routeModel.setRouteName("test-fatherid");
        routeModel.setRoutePath("test-rootid");
        routeModel.setRouteComponent("test-rootid");
        routeModel.setOrderNum(1);
        routeModel.setRouteLevel(1);
        routeModel.setIcon("test");
        routeModel.setStatus("test");
        routeModel.setChildren(new ArrayList<>());
    }

    @Test
    public void test() {

        RouteModel routeModel = new RouteModel();
        RouteModel routeModel1 = new RouteModel();
        RouteModel routeModel3 = new RouteModel(10L, 10L, "test-pid", "test-fatherid", "test-fatherid", "test-fatherid", 1, 1, "test-fatherid", "test-fatherid");

        assertTrue(routeModel.equals(routeModel1));

        routeModel.hashCode();
        routeModel.toString();
        init(routeModel);
        routeModel.hashCode();

        RouteModel routeModel2 = routeModel;
        assertTrue(routeModel2.equals(routeModel));

        assertFalse(routeModel1.equals(routeModel));
        assertFalse(routeModel1.equals(null));

        init(routeModel1);
        assertTrue(routeModel1.equals(routeModel));

        init(routeModel1);
        routeModel1.setId(100L);
        assertFalse(routeModel1.equals(routeModel));

        routeModel1.setParentId(100L);
        assertFalse(routeModel1.equals(routeModel));

        init(routeModel1);
        routeModel1.setName("test-pids");
        assertFalse(routeModel1.equals(routeModel));

        init(routeModel1);
        routeModel1.setRouteName("test-fatherids");
        assertFalse(routeModel1.equals(routeModel));

        init(routeModel1);
        routeModel1.setRoutePath("test-rootids");
        assertFalse(routeModel1.equals(routeModel));

        init(routeModel1);
        routeModel1.setRouteComponent("test-rootids");
        assertFalse(routeModel1.equals(routeModel));

        init(routeModel1);
        routeModel1.setOrderNum(2);
        assertFalse(routeModel1.equals(routeModel));

        init(routeModel1);
        routeModel1.setRouteLevel(2);
        assertFalse(routeModel1.equals(routeModel));

        init(routeModel1);
        routeModel1.setRouteLevel(2);
        assertFalse(routeModel1.equals(routeModel));

        init(routeModel1);
        routeModel1.setIcon("tests");
        assertFalse(routeModel1.equals(routeModel));

        init(routeModel1);
        routeModel1.setStatus("tests");
        assertFalse(routeModel1.equals(routeModel));

        init(routeModel1);
        List<RouteModel> list = new ArrayList<>();
        list.add(new RouteModel());
        routeModel1.setChildren(list);
        assertFalse(routeModel1.equals(routeModel));
    }
}
