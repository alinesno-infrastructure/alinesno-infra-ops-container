package com.alinesno.infra.ops.container.entity.market;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("subscriptions")
public class SubscriptionEntity extends InfraBaseEntity {

    @TableField("subscription_id")
    private String subscriptionId;

    @TableField("user_id")
    private Long userId;

    @TableField("application_id")
    private Long applicationId;

    @TableField("subscription_time")
    private Date subscriptionTime;

    // Getters and setters
}