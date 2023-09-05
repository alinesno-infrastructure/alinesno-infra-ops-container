package com.alinesno.infra.ops.container.api.deploy;

public class DeploymentDTO {
    private String namespace;
    private String images;
    private String strategy;
    private String trafficSplitMethod;
    private String action;

    // 添加构造方法和 getter/setter 方法

    public DeploymentDTO() {
    }

    public DeploymentDTO(String namespace, String images, String strategy, String trafficSplitMethod, String action) {
        this.namespace = namespace;
        this.images = images;
        this.strategy = strategy;
        this.trafficSplitMethod = trafficSplitMethod;
        this.action = action;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public String getTrafficSplitMethod() {
        return trafficSplitMethod;
    }

    public void setTrafficSplitMethod(String trafficSplitMethod) {
        this.trafficSplitMethod = trafficSplitMethod;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
