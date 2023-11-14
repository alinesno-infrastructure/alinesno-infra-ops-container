package com.alinesno.infra.ops.container.entity.kubernetes;

import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.EqualsAndHashCode;

/**
 * 功能名：配置字典
 * 数据表：config_dict
 * 表备注：配置字典
 * 
 * @TableName("config_dict")
 * 
 * @author luoxiaodong
 * @version 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@TableName("config_dict")
@Data
public class ConfigDictEntity extends InfraBaseEntity {

    /**
     * 配置名称
     */
    @TableField("config_name")
	@ColumnType(length=255)
	@ColumnComment("配置名称")
    private String configName;

    /**
     * 全名空间
     */
    @TableField("namespace")
	@ColumnType(length=255)
	@ColumnComment("全名空间")
    private String namespace;

    /**
     * 配置项目
     */
    @TableField("config_item")
	@ColumnType(length=255)
	@ColumnComment("配置项目")
    private String configItem;

    /**
     * 配置值
     */
    @TableField("config_value")
	@ColumnType(length=255)
	@ColumnComment("配置值")
    private String configValue;

    /**
     * 配置 YAML 内容
     */
    @TableField("yaml_content")
	@ColumnType(length=255)
	@ColumnComment("配置")
    private String yamlContent;
}
