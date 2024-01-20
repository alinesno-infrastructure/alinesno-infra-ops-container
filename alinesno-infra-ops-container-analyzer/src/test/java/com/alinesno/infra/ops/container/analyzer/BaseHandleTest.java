package com.alinesno.infra.ops.container.analyzer;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.credentials.AccessTokenAuthentication;
import lombok.SneakyThrows;

public class BaseHandleTest {

    @SneakyThrows
    protected ApiClient getApiClient(){

        String apiServerUrl = System.getenv("API_SERVER_URL") ;
        String token = System.getenv(("API_SERVER_TOKEN")) ;

        System.out.println("apiServerUrl = " + apiServerUrl);
        System.out.println("token = " + token);

        ApiClient client = new ClientBuilder().setBasePath(apiServerUrl).setVerifyingSsl(false) .setAuthentication(new AccessTokenAuthentication(token)).build();
        Configuration.setDefaultApiClient(client);

        return client ;
    }

}
