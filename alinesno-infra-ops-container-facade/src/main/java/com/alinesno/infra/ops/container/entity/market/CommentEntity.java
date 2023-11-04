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
 * 评论实体类
 * 表名：comments
 */
@TableName("comments")
@Data
public class CommentEntity extends InfraBaseEntity {

    @TableField("comment_id")
	@ColumnType(length=255)
	@ColumnComment("commentId")
    private String commentId;

    @TableField("user_id")
	@ColumnType(length=255)
	@ColumnComment("userId")
    private Long userId;

    @TableField("application_id")
	@ColumnType(length=255)
	@ColumnComment("applicationId")
    private Long applicationId;

    @TableField("comment_content")
	@ColumnType(length=255)
	@ColumnComment("commentContent")
    private String commentContent;

    @TableField("comment_time")
	@ColumnType(length=255)
	@ColumnComment("commentTime")
    private Date commentTime;
}
