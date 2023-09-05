package com.alinesno.infra.ops.container.entity.market;

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

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Date getSubscriptionTime() {
        return subscriptionTime;
    }

    public void setSubscriptionTime(Date subscriptionTime) {
        this.subscriptionTime = subscriptionTime;
    }
}
