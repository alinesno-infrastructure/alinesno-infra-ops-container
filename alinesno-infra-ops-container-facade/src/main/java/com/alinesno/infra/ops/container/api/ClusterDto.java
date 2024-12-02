package com.alinesno.infra.ops.container.api;

import com.alinesno.infra.common.facade.base.BaseDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ClusterDto extends BaseDto {

    /**
     * 配置类型
     */
    @NotBlank(message = "配置类型不能为空")
    private String configType;

    /**
     * 图标
     */
    @NotBlank(message = "图标不能为空")
    private String icon;

    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空")
    private String clusterName;

    /**
     * ApiServer地址
     */
    @NotBlank(message = "ApiServer地址不能为空")
    private String apiServerUrl;

    /**
     * 集群配置
     */
    @NotBlank(message = "集群配置不能为空")
    private String kubeConfig;

}
