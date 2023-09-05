package com.alinesno.infra.ops.container.entity.market;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;
import java.util.Date;

@TableName("ratings")
public class RatingEntity extends InfraBaseEntity {

    @TableField("rating_id")
    private String ratingId;

    @TableField("user_id")
    private Long userId;

    @TableField("application_id")
    private Long applicationId;

    @TableField("rating_value")
    private Integer ratingValue;

    @TableField("rating_time")
    private Date ratingTime;

    // Getters and setters
}