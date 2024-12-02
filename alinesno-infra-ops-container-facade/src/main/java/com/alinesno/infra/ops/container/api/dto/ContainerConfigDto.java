package com.alinesno.infra.ops.container.api.dto;

import lombok.Data;

@Data
public class ContainerConfigDto {
    private ContainerInfo info;
    private ContainerDetails container;
    private StorageConfig storage;
}
