package org.talend.esbconsole.server.domain.core.impl;

import org.talend.esbconsole.server.domain.core.jmx.karaf.Bundle;
import org.talend.esbconsole.server.domain.core.jmx.karaf.Kar;
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

/**
 * {@link KarServiceImpl} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/11
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class KarServiceImplTest {

    @Mock
    private Kar kar;

    @Mock
    private Bundle bundle;

    @InjectMocks
    private KarServiceImpl karService;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void installKarTest() {
        Mockito.doNothing().when(kar).install(Mockito.anyString());

        karService.installKar(Mockito.anyString());

        Mockito.verify(kar).install(Mockito.anyString());
    }

    @Test
    public void unInstallKarTest() {
        Mockito.doNothing().when(kar).uninstall(Mockito.anyString());

        karService.uninstallKar(Mockito.anyString());

        Mockito.verify(kar).uninstall(Mockito.anyString());
    }

    @Test
    public void startKarTest() {
        Mockito.doNothing().when(bundle).start(Mockito.anyString());

        karService.startKar(Arrays.asList("kar1"));

        Mockito.verify(bundle).start(Mockito.anyString());
    }

    @Test
    public void stoplKarTest() {
        Mockito.doNothing().when(bundle).stop(Mockito.anyString());

        karService.stoplKar(Arrays.asList("kar1"));

        Mockito.verify(bundle).stop(Mockito.anyString());
    }
}
