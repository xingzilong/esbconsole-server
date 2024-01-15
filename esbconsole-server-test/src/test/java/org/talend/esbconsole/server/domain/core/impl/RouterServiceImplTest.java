package org.talend.esbconsole.server.domain.core.impl;

import org.talend.esbconsole.server.domain.api.model.RouteModel;
import org.talend.esbconsole.server.domain.repository.mapper.SystemAuthorityDAO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * {@link RouterServiceImpl} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/11
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class RouterServiceImplTest {

    @Mock
    private SystemAuthorityDAO systemAuthorityDAO;

    @InjectMocks
    private RouterServiceImpl routerService;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void listRouteByUserIdTest() {
        RouteModel routeModel = Mockito.mock(RouteModel.class);

        Mockito.doReturn(Arrays.asList(routeModel)).when(systemAuthorityDAO).listRouteByUserId(Mockito.anyString());

        List<RouteModel> routeModelListRS = routerService.listRouteByUserId(Mockito.anyString());

        Mockito.verify(systemAuthorityDAO).listRouteByUserId(Mockito.anyString());

        assertNotNull(routeModelListRS);
    }

    @Test
    public void routeListToTreeTest() {
        RouteModel routeModelParam = new RouteModel();
        routeModelParam.setId(1L);
        routeModelParam.setParentId(0L);

        RouteModel routeModelParam1 = new RouteModel();
        routeModelParam1.setId(2L);
        routeModelParam1.setParentId(1L);

        RouteModel routeModelParam2 = new RouteModel();
        routeModelParam2.setId(3L);
        routeModelParam2.setParentId(2L);

        List<RouteModel> routeModelListRS = routerService.routeListToTree(Arrays.asList(routeModelParam, routeModelParam1, routeModelParam2));

        assertNotNull(routeModelListRS);
    }

    @Test
    public void getRouteListToTreeByUserIdTest() {
        RouteModel routeModelParam = new RouteModel();
        routeModelParam.setId(1L);
        routeModelParam.setParentId(0L);

        RouteModel routeModelParam1 = new RouteModel();
        routeModelParam1.setId(2L);
        routeModelParam1.setParentId(1L);

        RouteModel routeModelParam2 = new RouteModel();
        routeModelParam2.setId(3L);
        routeModelParam2.setParentId(2L);

        Mockito.doReturn(Arrays.asList(routeModelParam, routeModelParam1, routeModelParam2)).when(systemAuthorityDAO).listRouteByUserId(Mockito.anyString());

        List<RouteModel> routeModelListRS = routerService.getRouteListToTreeByUserId(Mockito.anyString());

        assertNotNull(routeModelListRS);
    }

}
