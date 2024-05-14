package org.talend.esbconsole.server.domain.core.jmx;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import javax.management.MBeanServerConnection;
import javax.management.remote.rmi.RMIConnector;
import javax.management.remote.rmi.RMIServer;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * {@link MBeanServerUtil} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/16
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class MBeanServerUtilTest {

    @InjectMocks
    private MBeanServerUtil mBeanServerUtil;

//    @Test
//    public void jmxConnectorTest() throws NotBoundException, RemoteException {
//        Registry registry = Mockito.mock(Registry.class);
//        RMIServer rmiServer = Mockito.mock(RMIServer.class);
//        MBeanServerConnection mBeanServerConnection = Mockito.mock(MBeanServerConnection.class);
//        MockedStatic<LocateRegistry> locateRegistry = Mockito.mockStatic(LocateRegistry.class);
//        locateRegistry.when(() -> LocateRegistry.getRegistry(Mockito.any(), Mockito.anyInt())).thenReturn(registry);
//        Mockito.doReturn(rmiServer).when(registry).lookup(Mockito.any());
//        MockedConstruction<RMIConnector> rmiConnector = Mockito.mockConstruction(RMIConnector.class, (mock, context) -> {
//            Mockito.doReturn(mBeanServerConnection).when(mock).getMBeanServerConnection();
//        });
//
//        try {
//            mBeanServerUtil.jmxConnector();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            locateRegistry.close();
//            rmiConnector.close();
//        }
//
//    }

    @Test
    public void reconnectTest() throws NotBoundException, RemoteException {
        Registry registry = Mockito.mock(Registry.class);
        RMIServer rmiServer = Mockito.mock(RMIServer.class);
        MBeanServerConnection mBeanServerConnection = Mockito.mock(MBeanServerConnection.class);
        MockedStatic<LocateRegistry> locateRegistry = Mockito.mockStatic(LocateRegistry.class);
        locateRegistry.when(() -> LocateRegistry.getRegistry(Mockito.any(), Mockito.anyInt())).thenReturn(registry);
        Mockito.doReturn(rmiServer).when(registry).lookup(Mockito.any());
        MockedConstruction<RMIConnector> rmiConnector = Mockito.mockConstruction(RMIConnector.class, (mock, context) -> {
            Mockito.doReturn(mBeanServerConnection).when(mock).getMBeanServerConnection();
        });

        try {
            mBeanServerUtil.reconnect();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            locateRegistry.close();
            rmiConnector.close();
        }
    }

    @Test
    public void reconnectExcepotionTest() throws NotBoundException, RemoteException {
        Registry registry = Mockito.mock(Registry.class);
        RMIServer rmiServer = Mockito.mock(RMIServer.class);
        MockedStatic<LocateRegistry> locateRegistry = Mockito.mockStatic(LocateRegistry.class);
        locateRegistry.when(() -> LocateRegistry.getRegistry(Mockito.any(), Mockito.anyInt())).thenReturn(registry);
        Mockito.doReturn(rmiServer).when(registry).lookup(Mockito.any());
        MockedConstruction<RMIConnector> rmiConnector = Mockito.mockConstruction(RMIConnector.class, (mock, context) -> {
            Mockito.doThrow(new RuntimeException("test")).when(mock).getMBeanServerConnection();
        });

        try {
            mBeanServerUtil.reconnect();
        } catch (Exception e) {
            assertTrue(e instanceof RuntimeException);
        } finally {
            locateRegistry.close();
            rmiConnector.close();
        }
    }

    @Test
    public void afterPropertiesSetTest() throws NotBoundException, IOException {
        Registry registry = Mockito.mock(Registry.class);
        RMIServer rmiServer = Mockito.mock(RMIServer.class);
        MBeanServerConnection mBeanServerConnection = Mockito.mock(MBeanServerConnection.class);
        MockedStatic<LocateRegistry> locateRegistry = Mockito.mockStatic(LocateRegistry.class);
        locateRegistry.when(() -> LocateRegistry.getRegistry(Mockito.any(), Mockito.anyInt())).thenReturn(registry);
        Mockito.doReturn(rmiServer).when(registry).lookup(Mockito.any());
        MockedConstruction<RMIConnector> rmiConnector = Mockito.mockConstruction(RMIConnector.class, (mock, context) -> {
            Mockito.doReturn(mBeanServerConnection).when(mock).getMBeanServerConnection();
        });

        try {
            mBeanServerUtil.afterPropertiesSet();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            locateRegistry.close();
            rmiConnector.close();
        }
    }

    @Test
    public void afterPropertiesSetExceptionTest() throws NotBoundException, IOException {
        Registry registry = Mockito.mock(Registry.class);
        RMIServer rmiServer = Mockito.mock(RMIServer.class);
        MockedStatic<LocateRegistry> locateRegistry = Mockito.mockStatic(LocateRegistry.class);
        locateRegistry.when(() -> LocateRegistry.getRegistry(Mockito.any(), Mockito.anyInt())).thenReturn(registry);
        Mockito.doReturn(rmiServer).when(registry).lookup(Mockito.any());
        MockedConstruction<RMIConnector> rmiConnector = Mockito.mockConstruction(RMIConnector.class, (mock, context) -> {
            Mockito.doThrow(new RuntimeException("test")).when(mock).getMBeanServerConnection();
        });

        try {
            mBeanServerUtil.afterPropertiesSet();
        } catch (Exception e) {
            assertTrue(e instanceof RuntimeException);
        } finally {
            locateRegistry.close();
            rmiConnector.close();
        }
    }
}
