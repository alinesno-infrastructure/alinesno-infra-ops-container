package com.alinesno.infra.ops.container.analyzer.utils;

import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.models.*;
import io.kubernetes.client.util.Config;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.apis.AppsV1Api;
import io.kubernetes.client.openapi.apis.NetworkingV1Api;
import io.kubernetes.client.openapi.apis.AdmissionregistrationV1Api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class K8SUtils {

    private static final String anonymizePattern = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()-_=+[]{}|;':\",./<>?";

    public static boolean sliceContainsString(String[] slice, String s) {
        for (String item : slice) {
            if (item.equals(s)) {
                return true;
            }
        }
        return false;
    }

    public static CoreV1Event fetchLatestEvent(CoreV1Api coreV1Api, String namespace, String name) throws ApiException {
        CoreV1EventList eventList = coreV1Api.listNamespacedEvent(namespace, null, null, null, null, "involvedObject.name=" + name, null, null, null, null , null);

        CoreV1Event latestEvent = null;
        for (CoreV1Event event : eventList.getItems()) {
            if (latestEvent == null || Objects.requireNonNull(event.getLastTimestamp()).isAfter(latestEvent.getLastTimestamp())) {
                latestEvent = event;
            }
        }

        return latestEvent;
    }

    public static String getParent(CoreV1Api coreV1Api, AppsV1Api appsV1Api, NetworkingV1Api networkingV1Api, AdmissionregistrationV1Api admissionregistrationV1Api, V1ObjectMeta meta) throws ApiException {
        if (meta.getOwnerReferences() != null) {
            for (V1OwnerReference owner : meta.getOwnerReferences()) {
                switch (owner.getKind()) {
                    case "ReplicaSet":
                        V1ReplicaSet rs = appsV1Api.readNamespacedReplicaSet(owner.getName(), meta.getNamespace(), null);
                        if (Objects.requireNonNull(rs.getMetadata()).getOwnerReferences() != null) {
                            return getParent(coreV1Api, appsV1Api, networkingV1Api, admissionregistrationV1Api, rs.getMetadata());
                        }
                        return "ReplicaSet/" + rs.getMetadata().getName();

                    case "Deployment":
                        V1Deployment dep = appsV1Api.readNamespacedDeployment(owner.getName(), meta.getNamespace(), null) ;
                        if (Objects.requireNonNull(dep.getMetadata()).getOwnerReferences() != null) {
                            return getParent(coreV1Api, appsV1Api, networkingV1Api, admissionregistrationV1Api, dep.getMetadata());
                        }
                        return "Deployment/" + dep.getMetadata().getName();

                    case "StatefulSet":
                        V1StatefulSet sts = appsV1Api.readNamespacedStatefulSet(owner.getName(), meta.getNamespace(), null) ;
                        if (Objects.requireNonNull(sts.getMetadata()).getOwnerReferences() != null) {
                            return getParent(coreV1Api, appsV1Api, networkingV1Api, admissionregistrationV1Api, sts.getMetadata());
                        }
                        return "StatefulSet/" + sts.getMetadata().getName();

                    case "DaemonSet":
                        V1DaemonSet ds = appsV1Api.readNamespacedDaemonSet(owner.getName(), meta.getNamespace(), null);
                        if (Objects.requireNonNull(ds.getMetadata()).getOwnerReferences() != null) {
                            return getParent(coreV1Api, appsV1Api, networkingV1Api, admissionregistrationV1Api, ds.getMetadata());
                        }
                        return "DaemonSet/" + ds.getMetadata().getName();

                    case "Ingress":
                        V1Ingress ingress = networkingV1Api.readNamespacedIngress(owner.getName(), meta.getNamespace(), null);
                        if (Objects.requireNonNull(ingress.getMetadata()).getOwnerReferences() != null) {
                            return getParent(coreV1Api, appsV1Api, networkingV1Api, admissionregistrationV1Api, ingress.getMetadata());
                        }
                        return "Ingress/" + ingress.getMetadata().getName();

                    case "MutatingWebhookConfiguration":
                        V1MutatingWebhookConfiguration mw = admissionregistrationV1Api.readMutatingWebhookConfiguration(owner.getName(), meta.getNamespace());
                        if (Objects.requireNonNull(mw.getMetadata()).getOwnerReferences() != null) {
                            return getParent(coreV1Api, appsV1Api, networkingV1Api, admissionregistrationV1Api, mw.getMetadata());
                        }
                        return "MutatingWebhook/" + mw.getMetadata().getName();

                    case "ValidatingWebhookConfiguration":
                        V1ValidatingWebhookConfiguration vw = admissionregistrationV1Api.readValidatingWebhookConfiguration(owner.getName(), meta.getNamespace());
                        if (Objects.requireNonNull(vw.getMetadata()).getOwnerReferences() != null) {
                            return getParent(coreV1Api, appsV1Api, networkingV1Api, admissionregistrationV1Api, vw.getMetadata());
                        }
                        return "ValidatingWebhook/" + vw.getMetadata().getName();
                }
            }
        }
        return meta.getName();
    }

    public static Map<String, Set<String>> removeDuplicates(String[] slice) {
        Set<String> set = new HashSet<>();
        Set<String> duplicates = new HashSet<>();
        for (String val : slice) {
            if (!set.add(val)) {
                duplicates.add(val);
            }
        }

        Map<String, Set<String>> result = new HashMap<>();
        result.put("uniqueSlice", set);
        result.put("duplicates", duplicates);

        return result;
    }

    public static String[] sliceDiff(String[] source, String[] dest) {
        Set<String> set = new HashSet<>(Arrays.asList(dest));

        Set<String> diff = new HashSet<>();
        for (String x : source) {
            if (!set.contains(x)) {
                diff.add(x);
            }
        }

        return diff.toArray(new String[0]);
    }

    public static String maskString(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] key = new byte[input        .length()];
        new java.security.SecureRandom().nextBytes(key);

        java.security.MessageDigest sha256 = java.security.MessageDigest.getInstance("SHA-256");
        key = sha256.digest(key);

        char[] result = new char[input.length()];
        for (int i = 0; i < input.length(); i++) {
            result[i] = anonymizePattern.charAt((input.charAt(i) + key[i]) % anonymizePattern.length());
        }

        return new String(result);
    }

}