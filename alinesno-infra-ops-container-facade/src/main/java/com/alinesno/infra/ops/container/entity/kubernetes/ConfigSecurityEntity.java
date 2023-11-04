package com.alinesno.infra.ops.container.entity.kubernetes;

import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 功能名：密钥管理实体类
 * 数据表：config_security
 * 表备注：密钥管理
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("config_security")
@Data
public class ConfigSecurityEntity extends InfraBaseEntity {
    // 密钥名称
    @TableField("key_name")
	@ColumnType(length=255)
	@ColumnComment("密钥名称")
    private String keyName;

    // 命名空间
    @TableField("namespace")
	@ColumnType(length=255)
	@ColumnComment("命名空间")
    private String namespace;

    // 类型
    @TableField("type")
	@ColumnType(length=255)
	@ColumnComment("类型")
    private String type;

    // 密钥数量
    @TableField("key_count")
	@ColumnType(length=255)
	@ColumnComment("密钥数量")
    private Long keyCount;

    // 密钥键
    @TableField("key_name_values")
	@ColumnType(length=255)
	@ColumnComment("密钥键")
    private String keyNameValues;

    // 密钥值
    @TableField("key_value")
	@ColumnType(length=255)
	@ColumnComment("密钥值")
    private String keyValue;
}
