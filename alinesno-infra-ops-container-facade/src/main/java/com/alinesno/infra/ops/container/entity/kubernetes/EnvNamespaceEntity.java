package com.alinesno.infra.ops.container.entity.kubernetes;

import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.EqualsAndHashCode;

/**
 * 功能名：命名空间
 * 数据表：env_namespace
 * 表备注：命名空间
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@TableName("env_namespace")
@Data
public class EnvNamespaceEntity extends InfraBaseEntity {
    // fields
    /**
    * 空间名称
    */
    @TableField("namespace_name")
	@ColumnType(length=255)
	@ColumnComment("空间名称")
    private String namespaceName;
    /**
    * 类型
    */
    @TableField("type")
	@ColumnType(length=255)
	@ColumnComment("类型")
    private String type;
    /**
    * 环境名称
    */
    @TableField("env_name")
	@ColumnType(length=255)
	@ColumnComment("环境名称")
    private String envName;
    /**
    * 创建人
    */
    @TableField("creator")
	@ColumnType(length=255)
	@ColumnComment("创建人")
    private String creator;
    /**
    * CPU
    */
    @TableField("cpu")
	@ColumnType(length=255)
	@ColumnComment("CPU")
    private String cpu;
    /**
    * 内存
    */
    @TableField("memory")
	@ColumnType(length=255)
	@ColumnComment("内存")
    private String memory;
    /**
    * 存储
    */
    @TableField("storage")
	@ColumnType(length=255)
	@ColumnComment("存储")
    private String storage;
    /**
    * 已使用CPU
    */
    @TableField("used_cpu")
	@ColumnType(length=255)
	@ColumnComment("已使用CPU")
    private String usedCpu;
    /**
    * 已使用内存
    */
    @TableField("used_memory")
	@ColumnType(length=255)
	@ColumnComment("已使用内存")
    private String usedMemory;
    /**
    * 已使用存储
    */
    @TableField("used_storage")
	@ColumnType(length=255)
	@ColumnComment("已使用存储")
    private String usedStorage;
    /**
    * 备注
    */
    @TableField("remark")
	@ColumnType(length=255)
	@ColumnComment("备注")
    private String remark;
}
