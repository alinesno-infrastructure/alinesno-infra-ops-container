package com.alinesno.infra.ops.container.api.provider;

import com.alinesno.infra.ops.container.api.dto.ContainerProductDTO;
import com.alinesno.infra.ops.container.api.utils.YamlUtils;
import com.alinesno.infra.ops.container.service.market.IContainerProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class ContainerProductControllerTest {

    @Mock
    private IContainerProductService mockContainerProductService;

    private ContainerProductController containerProductController;

    @BeforeEach
    public void setUp() {
        try (AutoCloseable ignored = MockitoAnnotations.openMocks(this)) {
            containerProductController = new ContainerProductController(mockContainerProductService);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSaveProductInfo() {
        ContainerProductDTO containerProductDTO = new ContainerProductDTO();
        containerProductController.saveProductInfo(containerProductDTO);
        verify(mockContainerProductService, times(1)).createProduct(containerProductDTO);
    }

    @Test
    public void testAddProduct() throws IOException {
        // 生成临时文件
        File tempFile = File.createTempFile("test", ".yaml");
        tempFile.deleteOnExit();

        // 创建一个包含容器产品信息的YAML文件
        // 这里只是示例，实际的文件内容可以根据需要进行定制
        String yamlContent = "container:\n" +
                "  info:\n" +
                "    name: alinesno-infra-ops-container\n" +
                "    description: 提供基于k8s的管理服务，提供容器化应用的管理和部署功能，同时提供监控和运营管理\n" +
                "    icon: https://avatars.githubusercontent.com/u/140813960?s=48&v=4\n" +
                "  container:\n" +
                "    namespace: alinesno-infra-ops\n" +
                "    init-run:\n" +
                "  strage:\n" +
                "    is-deploy: no\n" ;
        YamlUtils.writeToYaml(tempFile, yamlContent);

        // 创建 MockMultipartFile
        MultipartFile multipartFile = new MockMultipartFile("file", tempFile.getName(), "multipart/form-data", tempFile.getAbsolutePath().getBytes());

        containerProductController.addProduct(multipartFile);

        ContainerProductDTO expectedDTO = YamlUtils.loadFromYaml(tempFile, ContainerProductDTO.class);
        verify(mockContainerProductService, times(1)).createProduct(expectedDTO);
    }
}
