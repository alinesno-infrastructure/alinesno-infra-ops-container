package com.alinesno.infra.ops.container.api.dto;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import static org.junit.jupiter.api.Assertions.*;

class ContainerConfigDtoTest {

    @Test
    void testContainerConfigDto(){
        String yamlContent =
                """
                    info:
                        name: alinesno-infra-ops-container
                        description: 提供基于k8s的管理服务，提供容器化应用的管理和部署功能，同时提供监控和运营管理
                        icon: https://avatars.githubusercontent.com/u/140813960?s=48&v=4
                    container:
                        namespace: alinesno-infra-ops
                        initRun:
                    storage:
                        isDeploy: no
                """;

        Yaml yaml = new Yaml();
        ContainerConfigDto containerConfig = yaml.loadAs(yamlContent, ContainerConfigDto.class);

        System.out.println(new Gson().toJson(containerConfig));
    }

}