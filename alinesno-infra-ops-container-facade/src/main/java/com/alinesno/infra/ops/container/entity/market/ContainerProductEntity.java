package com.alinesno.infra.ops.container.entity.market;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import java.time.LocalDateTime;

@TableName("service")
public class ContainerProductEntity extends InfraBaseEntity {

    @TableField("name")
    private String name;

    @TableField("description")
    private String description;

    @TableField("icon")
    private String icon;

    @TableField("version")
    private String version;

    @TableField("namespace")
    private String namespace ;

    @TableField("init_run")
    private String initRun ;

    @TableField("has_deploy")
    private int hasDeploy ;

    // Getters and setters


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getInitRun() {
        return initRun;
    }

    public void setInitRun(String initRun) {
        this.initRun = initRun;
    }

    public int getHasDeploy() {
        return hasDeploy;
    }

    public void setHasDeploy(int hasDeploy) {
        this.hasDeploy = hasDeploy;
    }

    @Override
    public String toString() {
        return "ContainerProductEntity{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                ", version='" + version + '\'' +
                ", namespace='" + namespace + '\'' +
                ", initRun='" + initRun + '\'' +
                ", hasDeploy=" + hasDeploy +
                '}';
    }
}