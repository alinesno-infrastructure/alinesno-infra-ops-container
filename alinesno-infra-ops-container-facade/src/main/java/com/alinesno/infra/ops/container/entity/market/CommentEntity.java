package com.alinesno.infra.ops.container.entity.market;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * 评论实体类
 * 表名：comments
 */
@TableName("comments")
public class CommentEntity extends InfraBaseEntity {

    @TableField("comment_id")
    private String commentId;

    @TableField("user_id")
    private Long userId;

    @TableField("application_id")
    private Long applicationId;

    @TableField("comment_content")
    private String commentContent;

    @TableField("comment_time")
    private Date commentTime;

    // Getters and setters

    // 在这里编写属性的Getter和Setter方法

}
