package com.alinesno.infra.ops.container.entity.kubernetes;

import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.FetchType;
import javax.persistence.Lob;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * 用户操作记录
 * 数据表：event_manager_record
 * 表备注：用户操作记录
 *
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@SuppressWarnings("serial")
@TableName("event_manager_record")
@Data
public class ManagerRecordEntity extends InfraBaseEntity {

    /**
     * 操作说明
     */
    @TableField
	@ColumnType(length=255)
	@ColumnComment("操作说明")
    private String operation;

    /**
     * 执行时间
     */
    @Excel(name = "执行时间")
    @TableField("method_time")
	@ColumnType(length=255)
	@ColumnComment("执行时间")
    private long methodTime;

    /**
     * 类方法
     */
    @Excel(name = "类方法")
    @TableField("method")
	@ColumnType(length=255)
	@ColumnComment("类方法")
    private String method;

    /**
     * 请求参数
     */
    @Excel(name = "请求参数")
    @TableField("params")
    @Basic(fetch = FetchType.LAZY)
	@ColumnType(length=255)
	@ColumnComment("请求参数")
    private String params;

    /**
     * 方法描述
     */
    @Excel(name = "方法描述")
    @TableField("method_desc")
	@ColumnType(length=255)
	@ColumnComment("方法描述")
    private String methodDesc;

    /**
     * 请求记录
     */
    @Excel(name = "请求记录")
    @TableField("record_type")
	@ColumnType(length=255)
	@ColumnComment("请求记录")
    private String recordType;

    /**
     * 服务器IP
     */
    @Excel(name = "服务器IP")
    @TableField("ip")
	@ColumnType(length=255)
	@ColumnComment("服务器IP")
    private String ip;

    /**
     * 请求链接
     */
    @Excel(name = "请求链接")
    @TableField("url")
	@ColumnType(length=255)
	@ColumnComment("请求链接")
    private String url;

    /**
     * 浏览器信息
     */
    @Excel(name = "浏览器信息")
    @TableField("agent")
	@ColumnType(length=255)
	@ColumnComment("浏览器信息")
    private String agent;

    /**
     * 创建时间
     */
    @TableField("create_time")
	@ColumnType(length=255)
	@ColumnComment("创建时间")
    private Date createTime;

    /**
     * 账户ID
     */
    @TableField("account_id")
	@ColumnType(length=255)
	@ColumnComment("账户ID")
    private String accountId;

    /**
     * 登录名
     */
    @Excel(name = "登陆名")
    @TableField("login_name")
	@ColumnType(length=255)
	@ColumnComment("登录名")
    private String loginName;

    /**
     * 用户名
     */
    @Excel(name = "用户名")
    @TableField("account_name")
	@ColumnType(length=255)
	@ColumnComment("用户名")
    private String accountName;

    /**
     * 角色权限
     */
    @TableField("role_power")
	@ColumnType(length=255)
	@ColumnComment("角色权限")
    private String rolePower;
}
