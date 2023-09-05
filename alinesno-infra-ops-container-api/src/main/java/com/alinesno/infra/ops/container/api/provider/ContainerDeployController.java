package com.alinesno.infra.ops.container.api.provider;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContainerDeployController {

    @GetMapping("/deploy")
    public String deployContainer(@RequestParam("containerName") String containerName,
                                   @RequestParam("image") String image) {

        // 在这里编写调用k8s API的代码来发布容器
        // 根据传入的参数containerName和image来发布相应的容器

        return "Container " + containerName + " has been released with image " + image;
    }

}
