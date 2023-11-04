package com.alinesno.infra.ops.container.entity.market;

import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import java.time.LocalDateTime;

/**
 * 容器产品实体类
 * 表名：service
 */
@TableName("container_product")
@Data
public class ContainerProductEntity extends InfraBaseEntity {

    @TableField("name")
	@ColumnType(length=255)
	@ColumnComment("name")
    private String name;

    @TableField("description")
	@ColumnType(length=255)
	@ColumnComment("description")
    private String description;

    @TableField("icon")
	@ColumnType(length=255)
	@ColumnComment("icon")
    private String icon;

    @TableField("version")
	@ColumnType(length=255)
	@ColumnComment("version")
    private String version;

    @TableField("namespace")
	@ColumnType(length=255)
	@ColumnComment("namespace")
    private String namespace ;

    @TableField("init_run")
	@ColumnType(length=255)
	@ColumnComment("initRu")
    private String initRun ;

    @TableField("has_deploy")
	@ColumnType(length=255)
	@ColumnComment("hasDeploy")
    private int hasDeploy ;
}
