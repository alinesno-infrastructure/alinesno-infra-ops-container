package com.alinesno.infra.ops.container.api.provider;

import com.alinesno.infra.ops.container.api.deploy.DeploymentDTO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 容器部署控制器类
 * 处理容器的发布和文件上传请求
 */
@RestController
public class ContainerDeployController {

    /**
     * 发布容器
     *
     * @param containerName 容器名称
     * @param image         容器镜像
     * @return 发布结果信息
     */
    @GetMapping("/deploy")
    public String deployContainer(@RequestParam("containerName") String containerName,
                                  @RequestParam("image") String image) {

        // 在这里编写调用k8s API的代码来发布容器
        // 根据传入的参数containerName和image来发布相应的容器

        return "Container " + containerName + " has been released with image " + image;
    }

    /**
     * 接收文件上传
     *
     * @param files          上传的文件列表
     * @param deploymentDTO  部署信息
     * @return 上传结果信息
     */
    @PostMapping("/upload")
    public String uploadFiles(
            @RequestPart("files") List<MultipartFile> files,
            @RequestBody DeploymentDTO deploymentDTO
    ) {
        // 处理上传的文件和封装的字段
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                // 处理文件
                System.out.println("Received file: " + file.getOriginalFilename());
            }
        }

        // 处理封装的字段
        System.out.println("Received namespace: " + deploymentDTO.getNamespace());
        System.out.println("Received images: " + deploymentDTO.getImages());
        System.out.println("Received strategy: " + deploymentDTO.getStrategy());
        System.out.println("Received traffic-split-method: " + deploymentDTO.getTrafficSplitMethod());
        System.out.println("Received action: " + deploymentDTO.getAction());

        // 返回响应或进行其他操作
        return "upload-success";
    }
}
