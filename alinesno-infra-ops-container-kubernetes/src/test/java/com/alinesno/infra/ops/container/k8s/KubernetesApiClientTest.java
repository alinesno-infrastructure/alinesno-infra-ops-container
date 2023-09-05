//package com.alinesno.infra.ops.container.k8s;
//
//import io.kubernetes.client.openapi.ApiClient;
//import io.kubernetes.client.util.ClientBuilder;
//import io.kubernetes.client.util.KubeConfig;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.io.StringReader;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//public class KubernetesApiClientTest {
//
//    @Mock
//    private ApiClient apiClientMock;
//
//    private KubernetesApiClient kubernetesApiClient;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.openMocks(this);
//        kubernetesApiClient = new KubernetesApiClient();
//        kubernetesApiClient.setApiClient(apiClientMock);
//    }
//
//    @Test
//    @DisplayName("测试获取默认的 API 客户端")
//    public void testGetApiClient_Default() throws Exception {
//        // 执行测试
//        ApiClient apiClient = kubernetesApiClient.getApiClient();
//
//        // 验证返回的 API 客户端不为空
//        assertNotNull(apiClient);
//
//        // 验证是否调用了正确的方法
//        verify(apiClientMock, never()).setBasePath(anyString());
//        verify(apiClientMock, never()).kubeconfig(any(KubeConfig.class));
//    }
//
//    @Test
//    @DisplayName("测试获取指定 API 服务器 URL 的 API 客户端")
//    public void testGetApiClient_WithApiServerUrl() throws Exception {
//        // 执行测试
//        ApiClient apiClient = kubernetesApiClient.getApiClient("http://example.com:8080");
//
//        // 验证返回的 API 客户端不为空
//        assertNotNull(apiClient);
//
//        // 验证是否调用了正确的方法
//        verify(apiClientMock).setBasePath("http://example.com:8080");
//        verify(apiClientMock, never()).kubeconfig(any(KubeConfig.class));
//    }
//
//    @Test
//    @DisplayName("测试获取指定 KubeConfig 文件内容的 API 客户端")
//    public void testGetApiClientByConfig() throws Exception {
//        // 模拟 KubeConfig 文件内容
//        String kubeConfigFileContent = "apiVersion: v1\nkind: Config\nclusters:\n- cluster:\n    server: http://example.com:8080\n  name: my-cluster\ncontexts:\n- context:\n    cluster: my-cluster\n    user: my-user\n  name: my-context\ncurrent-context: my-context\nusers:\n- name: my-user\n  user:\n    password: my-password\n    username: my-username";
//
//        // 执行测试
//        ApiClient apiClient = kubernetesApiClient.getApiClientByConfig(kubeConfigFileContent);
//
//        // 验证返回的 API 客户端不为空
//        assertNotNull(apiClient);
//
//        // 验证是否调用了正确的方法
//        verify(apiClientMock, never()).setBasePath(anyString());
//        verify(apiClientMock).kubeconfig(any(KubeConfig.class));
//    }
//
//    @Test
//    @DisplayName("测试获取指定 API 服务器 URL 和 KubeConfig 文件内容的 API 客户端")
//    public void testGetApiClient_WithApiServerUrlAndConfig() throws Exception {
//        // 模拟 KubeConfig 文件内容
//        String kubeConfigFileContent = "apiVersion: v1\nkind: Config\nclusters:\n- cluster:\n    server: http://example.com:8080\n  name: my-cluster\ncontexts:\n- context:\n    cluster: my-cluster\n    user: my-user\n  name: my-context\ncurrent-context: my-context\nusers:\n- name: my-user\n  user:\n    password: my-password\n    username: my-username";
//
//        // 执行测试
//        ApiClient apiClient = kubernetesApiClient.getApiClient("http://example.com:8080", kubeConfigFileContent);
//
//        // 验证返回的 API 客户端不为空
//        assertNotNull(apiClient);
//
//        // 验证是否调用了正确的方法
//        verify(apiClientMock).setBasePath("http://example.com:8080");
//        verify(apiClientMock).kubeconfig(any(KubeConfig.class));
//    }
//
//    @Test
//    @DisplayName("测试根据用户 ID 和 KubeConfig 获取 API 客户端")
//    public void testGetApiClientByConfigByUserId() {
//        // 模拟用户 ID 和 KubeConfig
//        String kubeConfig = "apiVersion: v1\nkind: Config\nclusters:\n- cluster:\n    server: http://example.com:8080\n  name: my-cluster\ncontexts:\n- context:\n    cluster: my-cluster\n    user: my-user\n  name: my-context\ncurrent-context: my-context\nusers:\n- name: my-user\n  user:\n    password: my-password\n    username: my-username";
//        String userId = "user1";
//
//        // 模拟缓存中不存在对应的 API 客户端
//        when(apiClientMock.clone()).thenReturn(apiClientMock);
//        when(apiClientMock.setBasePath(anyString())).thenReturn(apiClientMock);
//        when(apiClientMock.kubeconfig(any(KubeConfig.class))).thenReturn(apiClientMock);
//
//        // 执行测试
//        ApiClient apiClient = kubernetesApiClient.getApiClientByConfigByUserId(kubeConfig, userId);
//
//        // 验证返回的 API 客户端不为空
//        assertNotNull(apiClient);
//
//        // 验证是否调用了正确的方法
//        verify(apiClientMock, times(1)).clone();
//        verify(apiClientMock, times(1)).setBasePath(anyString());
//        verify(apiClientMock, times(1)).kubeconfig(any(KubeConfig.class));
//
//        // 验证缓存中是否存储了对应的 API 客户端
//        Map<String, ApiClient> kubeMap = kubernetesApiClient.getKubeMap();
//        assertTrue(kubeMap.containsKey(userId));
//        assertEquals(apiClientMock, kubeMap.get(userId));
//
//        // 再次调用获取 API 客户端，验证是否从缓存中获取
//        // 执行测试
//        ApiClient cachedApiClient = kubernetesApiClient.getApiClientByConfigByUserId(kubeConfig, userId);
//
//        // 验证返回的 API 客户端与缓存中的客户端对象相同
//        assertEquals(apiClientMock, cachedApiClient);
//
//        // 验证不再调用 clone、setBasePath 和 kubeconfig 方法
//        verify(apiClientMock, times(1)).clone();
//        verify(apiClientMock, times(1)).setBasePath(anyString());
//        verify(apiClientMock, times(1)).kubeconfig(any(KubeConfig.class));
//    }
//}
