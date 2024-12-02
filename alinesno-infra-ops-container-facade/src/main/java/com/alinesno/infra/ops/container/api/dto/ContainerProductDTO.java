package com.alinesno.infra.ops.container.api.dto;

import lombok.Data;

@Data
public class ContainerProductDTO {

    private Long id;
    private String name;
    private String description;
    private String icon;
    private String version;
    private String namespace ;
    private String initRun ;
    private int hasDeploy ;

}
