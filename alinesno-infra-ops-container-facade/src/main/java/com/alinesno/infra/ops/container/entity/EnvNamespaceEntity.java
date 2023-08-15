package com.alinesno.infra.ops.container.entity;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 功能名：命名空间
 * 数据表：env_namespace
 * 表备注：命名空间
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("env_namespace")
public class EnvNamespaceEntity extends InfraBaseEntity {
    // fields
    /**
    * 空间名称
    */
    @TableField("namespace_name")
    private String namespaceName;
    /**
    * 类型
    */
    @TableField("type")
    private String type;
    /**
    * 环境名称
    */
    @TableField("env_name")
    private String envName;
    /**
    * 创建人
    */
    @TableField("creator")
    private String creator;
    /**
    * CPU
    */
    @TableField("cpu")
    private String cpu;
    /**
    * 内存
    */
    @TableField("memory")
    private String memory;
    /**
    * 存储
    */
    @TableField("storage")
    private String storage;
    /**
    * 已使用CPU
    */
    @TableField("used_cpu")
    private String usedCpu;
    /**
    * 已使用内存
    */
    @TableField("used_memory")
    private String usedMemory;
    /**
    * 已使用存储
    */
    @TableField("used_storage")
    private String usedStorage;
    /**
    * 备注
    */
    @TableField("remark")
    private String remark;
    
    // getter and setter methods
    public String getNamespaceName() {
        return this.namespaceName;
    }
    
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }
    
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getEnvName() {
        return this.envName;
    }
    
    public void setEnvName(String envName) {
        this.envName = envName;
    }
    
    public String getCreator() {
        return this.creator;
    }
    
    public void setCreator(String creator) {
        this.creator = creator;
    }
    
    public String getCpu() {
        return this.cpu;
    }
    
    public void setCpu(String cpu) {
        this.cpu = cpu;
    }
    
    public String getMemory() {
        return this.memory;
    }
    
    public void setMemory(String memory) {
        this.memory = memory;
    }
    
    public String getStorage() {
        return this.storage;
    }
    
    public void setStorage(String storage) {
        this.storage = storage;
    }
    
    public String getUsedCpu() {
        return this.usedCpu;
    }
    
    public void setUsedCpu(String usedCpu) {
        this.usedCpu = usedCpu;
    }
    
    public String getUsedMemory() {
        return this.usedMemory;
    }
    
    public void setUsedMemory(String usedMemory) {
        this.usedMemory = usedMemory;
    }
    
    public String getUsedStorage() {
        return this.usedStorage;
    }
    
    public void setUsedStorage(String usedStorage) {
        this.usedStorage = usedStorage;
    }
    
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
