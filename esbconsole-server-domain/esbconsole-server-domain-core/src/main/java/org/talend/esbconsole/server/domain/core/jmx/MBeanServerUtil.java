package org.talend.esbconsole.server.domain.core.jmx;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.rmi.RMIConnector;
import javax.management.remote.rmi.RMIServer;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * JMX连接服务管理的工具类
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Slf4j
@Component
public class MBeanServerUtil implements InitializingBean {
    /**
     * MBeanServerConnection对象实例，用于操作JMX
     */
    private static MBeanServerConnection MBEAN_SERVER_CONNECTION;

    private static JMXConnector JMX_CONNECTOR;
    /**
     * RMI远程IP地址
     */
    @Value("${karaf.jmx.rmi-registry-host}")
    private String rmiRegistryHost;
    /**
     * RMI远程端口
     */
    @Value("${karaf.jmx.rmi-registry-port}")
    private int rmiRegistryPort;

    /**
     * RMI服务IP地址
     */
    @Value("${karaf.jmx.rmi-server-host}")
    private String rmiServerHost;
    /**
     * RMI服务端口
     */
    @Value("${karaf.jmx.rmi-server-port}")
    private int rmiServerPort;
    /**
     * 名称
     */
    @Value("${karaf.name}")
    private String karafName;
    /**
     * 用户名
     */
    @Value("${karaf.jmx.password}")
    private String username;
    /**
     * 密码
     */
    @Value("${karaf.jmx.password}")
    private String password;
    /**
     * 定时调度器
     */
    private ScheduledExecutorService scheduler;
    /**
     * 初始等待时间
     */
    private int startDelay = 0;
    /**
     * 轮询时间间隔
     */
    private int scanIntervall = 15;

    /**
     * 私有化构造器
     */
    private MBeanServerUtil() {
    }

    public static MBeanServerConnection getMbeanServerConnection() {
        return MBEAN_SERVER_CONNECTION;
    }

    private static void setMbeanServerConnection(MBeanServerConnection mbeanServerConnection) {
        MBEAN_SERVER_CONNECTION = mbeanServerConnection;
    }

    private static void setJmxConnector(JMXConnector jmxConnector) {
        JMX_CONNECTOR = jmxConnector;
    }

    /**
     * 构造法中调用的初始化方法，用于启动JMX连接健康检查的定时调度器
     */
    public void init() {

        scheduler = Executors.newScheduledThreadPool(1, (Runnable r) -> {
            Thread thread = new Thread(r);
            thread.setName("JMX-Health-Detection-Thread");
            return thread;
        });
        scheduler.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                try {
                    MBEAN_SERVER_CONNECTION.getMBeanCount();
                } catch (Exception e) {
                    log.error("JMX健康检查异常：", e);
                    reconnect();
                }
            }
        }, startDelay, scanIntervall, TimeUnit.SECONDS);
    }

    /**
     * JMX连接重连方法
     */
    public void reconnect() {
        try {
            log.info("重新连接JMX相关连接");
            jmxConnector();
            log.info("JMX相关连接成功");
        } catch (Exception e) {
            log.error("JMX相关连接重连失败", e);
        }
    }

    /**
     * 创建JMX连接并设置MBEAN_SERVER_CONNECTION
     *
     * @throws Exception 创建连接过程中可能会抛出异常
     */
    private void jmxConnector() throws Exception {
        Registry registry = LocateRegistry.getRegistry(rmiRegistryHost, rmiRegistryPort);
        RMIServer rmiServer = (RMIServer) registry.lookup(karafName);
        // 设置参数
        Map env = new HashMap();
        // 用户名密码
        String[] credential = {username, password};
        env.put(JMXConnector.CREDENTIALS, credential);
        setJmxConnector(new RMIConnector(rmiServer, env));
        JMX_CONNECTOR.connect();
        // 获取MBeanServerConnection
        setMbeanServerConnection(JMX_CONNECTOR.getMBeanServerConnection());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 初始化创建JMX连接
        try {
            log.info("初始化创建JMX连接");
            jmxConnector();
            log.info("初始化创建JMX连接成功");
        } catch (Exception e) {
            log.error("初始化创建JMX连接失败", e);
        }
        init();
    }
}
