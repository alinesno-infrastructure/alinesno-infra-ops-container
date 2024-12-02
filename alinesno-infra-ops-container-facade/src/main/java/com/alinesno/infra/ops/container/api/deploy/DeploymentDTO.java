package com.alinesno.infra.ops.container.api.deploy;

import lombok.Data;

@Data
public class DeploymentDTO {
    private String namespace;
    private String images;
    private String strategy;
    private String trafficSplitMethod;
    private String action;
}
