package org.talend.esbconsole.server.tools.common.utils.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@link SpringUtils} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/17
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class SpringUtilsTest {

    private ConfigurableListableBeanFactory beanFactory = Mockito.mock(ConfigurableListableBeanFactory.class);
    private ApplicationContext context = Mockito.mock(ApplicationContext.class);

    @Test
    public void initTest() {
        SpringUtils utils = Mockito.spy(SpringUtils.class);
        utils.setApplicationContext(context);
        utils.setBeanFactory(beanFactory);
        utils.setContext(context);
        utils.postProcessBeanFactory(beanFactory);
    }

    @Test
    public void test() {
        SpringUtils.setBeanFactory(beanFactory);
        SpringUtils.setContext(context);
        String temp = "111";
        Mockito.doReturn(temp).when(beanFactory).getBean(Mockito.eq("111"));
        assertSame(temp, SpringUtils.getBean("111"));

        Mockito.doReturn(temp).when(beanFactory).getBean(Mockito.eq(String.class));
        assertSame(temp, SpringUtils.getBean(String.class));

        Mockito.doReturn(true).when(beanFactory).containsBean(Mockito.eq("test"));
        assertTrue(SpringUtils.containsBean("test"));

        Mockito.doReturn(true).when(beanFactory).isSingleton(Mockito.eq("test"));
        assertTrue(SpringUtils.isSingleton("test"));

        Mockito.doReturn(String.class).when(beanFactory).getType(Mockito.eq("test"));
        assertEquals(String.class, SpringUtils.getType("test"));

        Mockito.doReturn(null).when(beanFactory).getAliases(Mockito.eq("test"));
        assertNull(SpringUtils.getAliases("test"));
        MockedStatic<AopContext> aop = Mockito.mockStatic(AopContext.class);
        aop.when(() -> AopContext.currentProxy()).thenReturn("test");
        try {
            assertEquals("test", SpringUtils.getAopProxy("test"));
        } catch (Exception e) {

        } finally {
            aop.close();
        }
        Environment ment = Mockito.mock(Environment.class);
        Mockito.doReturn(ment).when(context).getEnvironment();
        Mockito.doReturn(null).when(ment).getActiveProfiles();
        assertNull(SpringUtils.getActiveProfiles());
        assertNull(SpringUtils.getActiveProfile());
        Mockito.doReturn(new String[]{"test"}).when(ment).getActiveProfiles();
        assertEquals("test", SpringUtils.getActiveProfile());
        Mockito.doReturn("test-first").when(ment).getRequiredProperty(Mockito.eq("test"));
        assertEquals("test-first", SpringUtils.getRequiredProperty("test"));
    }

}
