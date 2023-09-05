package com.alinesno.infra.ops.container.api.dto;

public class ContainerProductDTO {

    private Long id;

    private String name;

    private String description;

    private String icon;

    private String version;

    private String namespace ;

    private String initRun ;

    private int hasDeploy ;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
