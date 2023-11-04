package com.alinesno.infra.ops.container.entity.market;

import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 评分实体类
 * 表名：ratings
 */
@TableName("ratings")
@Data
public class RatingEntity extends InfraBaseEntity {

    @TableField("rating_id")
	@ColumnType(length=255)
	@ColumnComment("ratingId")
    private String ratingId;

    @TableField("user_id")
	@ColumnType(length=255)
	@ColumnComment("userId")
    private Long userId;

    @TableField("application_id")
	@ColumnType(length=255)
	@ColumnComment("applicationId")
    private Long applicationId;

    @TableField("rating_value")
	@ColumnType(length=255)
	@ColumnComment("ratingValue")
    private Integer ratingValue;

    @TableField("rating_time")
	@ColumnType(length=255)
	@ColumnComment("ratingTime")
    private Date ratingTime;
}
