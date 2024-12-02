package com.alinesno.infra.ops.container.api.dto;

import lombok.Data;

@Data
public class ContainerDetails {
    private String namespace;
    private String initRun;
}