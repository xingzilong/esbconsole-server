package org.talend.esbconsole.server.start.config;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate相关配置
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Configuration
public class RestTemplateConfig {

    @Value("${httpclient.max-total}")
    private Integer maxTotal;

    @Value("${httpclient.default-max-per-route}")
    private Integer defaultMaxPerRoute;

    @Value("${httpclient.connect-timeout}")
    private Integer connectTimeout;

    @Value("${httpclient.connection-request-timeout}")
    private Integer connectionRequestTimeout;

    @Value("${httpclient.socket-timeout}")
    private Integer socketTimeout;

    @Value("${httpclient.stale-connection-check-enabled}")
    private boolean staleConnectionCheckEnabled;

    @Value("${httpclient.validate-after-inactivity}")
    private Integer validateAfterInactivity;


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(httpRequestFactory());
    }

    @Bean
    public ClientHttpRequestFactory httpRequestFactory() {
        return new HttpComponentsClientHttpRequestFactory(httpClient());
    }

    @Bean
    public HttpClient httpClient() {
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", SSLConnectionSocketFactory.getSocketFactory())
                .build();
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
        // 最大连接数
        connectionManager.setMaxTotal(maxTotal);
        //单个路由最大连接数
        connectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
        // 最大空间时间
        connectionManager.setValidateAfterInactivity(validateAfterInactivity);
        RequestConfig requestConfig = RequestConfig.custom()
                //服务器返回数据(vo)的时间，超过抛出read timeout
                .setSocketTimeout(socketTimeout)
                //连接上服务器(握手成功)的时间，超出抛出connect timeout
                .setConnectTimeout(connectTimeout)
                // 提交前检测是否可用
                .setStaleConnectionCheckEnabled(staleConnectionCheckEnabled)
                //从连接池中获取连接的超时时间，超时间未拿到可用连接，会抛出org.apache.http.conn.ConnectionPoolTimeoutException: Timeout waiting for connection from pool
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .build();
        return HttpClientBuilder.create()
                .setDefaultRequestConfig(requestConfig)
                .setConnectionManager(connectionManager)
                .build();
    }

}
