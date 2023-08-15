package com.alinesno.infra.ops.container.entity;

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
public class ManagerRecordEntity extends InfraBaseEntity {

    /**
     * 操作说明
     */
    @TableField
    private String operation;

    /**
     * 执行时间
     */
    @Excel(name = "执行时间")
    @TableField("method_time")
    private long methodTime;

    /**
     * 类方法
     */
    @Excel(name = "类方法")
    @TableField("method")
    private String method;

    /**
     * 请求参数
     */
    @Excel(name = "请求参数")
    @TableField("params")
    @Basic(fetch = FetchType.LAZY)
    private String params;

    /**
     * 方法描述
     */
    @Excel(name = "方法描述")
    @TableField("method_desc")
    private String methodDesc;

    /**
     * 请求记录
     */
    @Excel(name = "请求记录")
    @TableField("record_type")
    private String recordType;

    /**
     * 服务器IP
     */
    @Excel(name = "服务器IP")
    @TableField("ip")
    private String ip;

    /**
     * 请求链接
     */
    @Excel(name = "请求链接")
    @TableField("url")
    private String url;

    /**
     * 浏览器信息
     */
    @Excel(name = "浏览器信息")
    @TableField("agent")
    private String agent;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 账户ID
     */
    @TableField("account_id")
    private String accountId;

    /**
     * 登录名
     */
    @Excel(name = "登陆名")
    @TableField("login_name")
    private String loginName;

    /**
     * 用户名
     */
    @Excel(name = "用户名")
    @TableField("account_name")
    private String accountName;

    /**
     * 角色权限
     */
    @TableField("role_power")
    private String rolePower;

    // getter and setter methods
    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public long getMethodTime() {
        return methodTime;
    }

    public void setMethodTime(long methodTime) {
        this.methodTime = methodTime;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getMethodDesc() {
        return methodDesc;
    }

    public void setMethodDesc(String methodDesc) {
        this.methodDesc = methodDesc;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getRolePower() {
        return rolePower;
    }

    public void setRolePower(String rolePower) {
        this.rolePower = rolePower;
    }
}
