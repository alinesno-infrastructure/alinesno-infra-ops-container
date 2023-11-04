package com.alinesno.infra.ops.container.entity.kubernetes;

import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
import java.util.Date;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 路由
 * 数据表：run_ingress
 * 表备注：路由
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("run_ingress")
@Data
public class RunIngressEntity extends InfraBaseEntity {

    /**
     * 名称
     */
    @TableField("name")
	@ColumnType(length=255)
	@ColumnComment("名称")
    private String name;

    /**
     * 命名空间
     */
    @TableField("namespace")
	@ColumnType(length=255)
	@ColumnComment("命名空间")
    private String namespace;

    /**
     * 访问地址
     */
    @TableField("address")
	@ColumnType(length=255)
	@ColumnComment("访问地址")
    private String address;

    /**
     * 创建时间
     */
    @TableField("creation_time")
	@ColumnType(length=255)
	@ColumnComment("创建时间")
    private Date creationTime;

    /**
     * 标签值
     */
    @TableField("label_values")
	@ColumnType(length=255)
	@ColumnComment("标签值")
    private String labelValues;

    /**
     * 访问协议
     */
    @TableField("protocol")
	@ColumnType(length=255)
	@ColumnComment("访问协议")
    private String protocol;
}
