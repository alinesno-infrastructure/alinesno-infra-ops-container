package com.alinesno.infra.ops.container.entity.market;

import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * 订阅实体类
 * 表名：subscriptions
 */
@TableName("subscriptions")
@Data
public class SubscriptionEntity extends InfraBaseEntity {

    @TableField("subscription_id")
	@ColumnType(length=255)
	@ColumnComment("subscriptionId")
    private String subscriptionId;

    @TableField("user_id")
	@ColumnType(length=255)
	@ColumnComment("userId")
    private Long userId;

    @TableField("application_id")
	@ColumnType(length=255)
	@ColumnComment("applicationId")
    private Long applicationId;

    @TableField("subscription_time")
	@ColumnType(length=255)
	@ColumnComment("subscriptionTime")
    private Date subscriptionTime;


}
