package com.alinesno.infra.ops.container.analyzer.utils;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.AdmissionregistrationV1Api;
import io.kubernetes.client.openapi.apis.AppsV1Api;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.apis.NetworkingV1Api;
import io.kubernetes.client.openapi.models.CoreV1Event;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.util.Config;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class K8SUtilsTest {

    @Test
    void sliceContainsString() {
    }

    @Test
    void fetchLatestEvent() {
    }

    @Test
    void getParent() {
    }

    @Test
    void removeDuplicates() {
    }

    @Test
    void sliceDiff() {
    }

    @Test
    void maskString() {
    }

    public static void main(String[] args) throws IOException, ApiException, NoSuchAlgorithmException {

        ApiClient client = Config.defaultClient();
        CoreV1Api coreV1Api = new CoreV1Api(client);
        AppsV1Api appsV1Api = new AppsV1Api(client);
        NetworkingV1Api networkingV1Api = new NetworkingV1Api(client);
        AdmissionregistrationV1Api admissionregistrationV1Api = new AdmissionregistrationV1Api(client);

        String namespace = "default";
        String podName = "example-pod";

        CoreV1Event latestEvent = K8SUtils.fetchLatestEvent(coreV1Api, namespace, podName);
        System.out.println("Latest Event for Pod " + podName + ": " + latestEvent);

        V1Pod pod = coreV1Api.readNamespacedPod(podName, namespace, null) ;
        String parentResource = K8SUtils.getParent(coreV1Api, appsV1Api, networkingV1Api, admissionregistrationV1Api, pod.getMetadata());
        System.out.println("Parent Resource for Pod " + podName + ": " + parentResource);

        String[] sourceSlice = {"apple", "banana", "orange", "apple", "grape"};
        String[] destSlice = {"banana", "orange", "grape", "kiwi"};

        Map<String, Set<String>> diffResult = K8SUtils.removeDuplicates(sourceSlice);
        System.out.println("Unique Elements in Source Slice: " + diffResult.get("uniqueSlice"));
        System.out.println("Duplicate Elements in Source Slice: " + diffResult.get("duplicates"));

        String[] sliceDifference = K8SUtils.sliceDiff(sourceSlice, destSlice);
        System.out.println("Elements in Source Slice not present in Destination Slice: " + Arrays.toString(sliceDifference));

        String inputString = "confidential_data";
        String maskedString = K8SUtils.maskString(inputString);
        System.out.println("Original String: " + inputString);
        System.out.println("Masked String: " + maskedString);

    }

}