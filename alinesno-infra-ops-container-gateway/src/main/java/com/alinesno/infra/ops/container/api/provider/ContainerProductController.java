package com.alinesno.infra.ops.container.api.provider;

import com.alinesno.infra.ops.container.api.dto.ContainerConfigDto;
import com.alinesno.infra.ops.container.api.dto.ContainerProductDTO;
import com.alinesno.infra.ops.container.api.utils.YamlUtils;
import com.alinesno.infra.ops.container.entity.market.ContainerProductEntity;
import com.alinesno.infra.ops.container.service.market.IContainerProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 容器产品控制器
 * 处理容器产品的保存和添加请求
 */
@RestController
@RequestMapping("/products")
public class ContainerProductController {

    private static final Logger log = LoggerFactory.getLogger(ContainerProductController.class);

    private final IContainerProductService containerProductService;

    @Autowired
    public ContainerProductController(IContainerProductService containerProductService) {
        this.containerProductService = containerProductService;
    }

    /**
     * 保存容器产品信息
     *
     * @param containerProductDTO 容器产品DTO
     */
    @GetMapping
    public void saveProductInfo(ContainerProductDTO containerProductDTO) {
        log.debug("containerProduct DTO = {}", containerProductDTO);
        containerProductService.createProduct(containerProductDTO);
    }

    /**
     * 添加容器产品
     *
     * @param file 包含容器产品信息的YAML文件
     */
    @PostMapping
    public void addProduct(@RequestParam("file") MultipartFile file) {
        try {
            ContainerConfigDto dto = YamlUtils.loadFromYaml(file.getResource().getFile(), ContainerConfigDto.class);
            log.debug("containerProduct DTO = {}", dto);

            ContainerProductEntity entity = new ContainerProductEntity();

            entity.setName(dto.getInfo().getName());
            entity.setDescription(dto.getInfo().getDescription());
            entity.setIcon(dto.getInfo().getIcon());

            entity.setName(dto.getContainer().getNamespace());
            entity.setInitRun(dto.getContainer().getInitRun());
            entity.setHasDeploy(dto.getStorage().getIsDeploy() ? 1 : 0);

            containerProductService.save(entity);
        } catch (IOException e) {
            // 处理异常
        }
    }
}
