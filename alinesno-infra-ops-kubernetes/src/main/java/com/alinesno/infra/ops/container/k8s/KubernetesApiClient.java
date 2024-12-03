package com.alinesno.infra.ops.container.k8s;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;
import io.kubernetes.client.util.credentials.AccessTokenAuthentication;
import org.apache.commons.lang3.StringUtils;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Kubernetes客户端工具类
 * 用于获取基于Kubernetes的API客户端
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public class KubernetesApiClient {

    private static final String DEFAULT_API_SERVER = "http://localhost:8001";

    private static ApiClient apiClient;

    /**
     * 缓存用户对应的ApiClient
     */
    private static final Map<String, ApiClient> kubeMap = new HashMap<>();

    private static final Map<String, ApiClient> kubeMapByAPI = new HashMap<>();

    /**
     * 同步获取API客户端
     * @return ApiClient对象
     * @throws Exception 异常
     */
    public static synchronized ApiClient getApiClient() throws Exception {
        return getApiClient(null);
    }

    /**
     * 同步获取API客户端
     * @param apiServerUrl API服务器URL
     * @return ApiClient对象
     * @throws Exception 异常
     */
    public static synchronized ApiClient getApiClient(String apiServerUrl) throws Exception {
        return getApiClient(apiServerUrl, null);
    }

    /**
     * 根据KubeConfig获取ApiClient
     * @param kubeConfigFileContent KubeConfig文件内容
     * @return ApiClient对象
     * @throws Exception 异常
     */
    public static synchronized ApiClient getApiClientByConfig(String kubeConfigFileContent) throws Exception {
        return getApiClient(null, kubeConfigFileContent);
    }

    /**
     * 同步获取API客户端
     * @param apiServerUrl API服务器URL
     * @param kubeConfigFileContent KubeConfig文件内容
     * @return ApiClient对象
     * @throws Exception 异常
     */
    public static synchronized ApiClient getApiClient(String apiServerUrl, String kubeConfigFileContent)  throws Exception {

        ApiClient apiClient = kubeMapByAPI.get(apiServerUrl) ;

        if (apiClient == null) {
            if (apiServerUrl == null || apiServerUrl.isEmpty()) {
                apiServerUrl = DEFAULT_API_SERVER;
            }

            if (StringUtils.isNoneBlank(kubeConfigFileContent)) {
                KubeConfig kubeConfig = KubeConfig.loadKubeConfig(new StringReader(kubeConfigFileContent));
                apiClient = ClientBuilder.kubeconfig(kubeConfig).build();
            } else {
                apiClient = new ClientBuilder().setBasePath(apiServerUrl).build();
            }

            kubeMapByAPI.put(apiServerUrl, apiClient);
        }

        return apiClient;
    }

    /**
     * 通过Token获取到地址
     * @param apiServerUrl
     * @param token
     * @return
     * @throws Exception
     */
    public static synchronized ApiClient getApiClientByToken(String apiServerUrl, String token) {

        ApiClient apiClient = kubeMapByAPI.get(apiServerUrl) ;

        if (apiClient == null) {
            if (apiServerUrl == null || apiServerUrl.isEmpty()) {
                apiServerUrl = DEFAULT_API_SERVER;
            }

            apiClient = new ClientBuilder().setBasePath(apiServerUrl).setVerifyingSsl(false).setAuthentication(new AccessTokenAuthentication(token)).build();
            Configuration.setDefaultApiClient(apiClient);

            kubeMapByAPI.put(apiServerUrl, apiClient);
        }

        return apiClient;
    }

    /**
     * 根据API服务器URL和KubeConfig文件内容获取ApiClient
     * @param apiServerUrl API服务器URL
     * @param kubeConfigFileContent KubeConfig文件内容
     * @return ApiClient对象
     * @throws Exception 异常
     */
    public static synchronized ApiClient getApiClientByInfo(String apiServerUrl, String kubeConfigFileContent) throws Exception {

        ApiClient apiClient = null;

        if (apiServerUrl == null || apiServerUrl.isEmpty()) {
            apiServerUrl = DEFAULT_API_SERVER;
        }

        if (StringUtils.isNoneBlank(kubeConfigFileContent)) {
            KubeConfig kubeConfig = KubeConfig.loadKubeConfig(new StringReader(kubeConfigFileContent));
            apiClient = ClientBuilder.kubeconfig(kubeConfig).build();
        } else {
            apiClient = new ClientBuilder().setBasePath(apiServerUrl).build();
        }

        return apiClient;
    }

    /**
     * 根据用户ID和KubeConfig获取ApiClient
     * @param kubeConfig KubeConfig文件内容
     * @param userId 用户ID
     * @return ApiClient对象
     */
    public static ApiClient getApiClientByConfigByUserId(String kubeConfig, String userId) {

        ApiClient client = kubeMap.get(userId);

        if (client == null) {

            try {
                client = getApiClientByConfig(kubeConfig);
            } catch (Exception e) {
                e.printStackTrace();
            }

            kubeMap.put(userId, client);
        }

        return client;
    }

}
