package com.alinesno.infra.ops.container.api.dto;

public class ContainerConfigDto {
    private ContainerInfo info;
    private ContainerDetails container;
    private StorageConfig storage;

    // getter and setter methods

    public ContainerInfo getInfo() {
        return info;
    }

    public void setInfo(ContainerInfo info) {
        this.info = info;
    }

    public ContainerDetails getContainer() {
        return container;
    }

    public void setContainer(ContainerDetails container) {
        this.container = container;
    }

    public StorageConfig getStorage() {
        return storage;
    }

    public void setStorage(StorageConfig storage) {
        this.storage = storage;
    }

    @Override
    public String toString() {
        return "ContainerConfigDto{" +
                "info=" + info +
                ", container=" + container +
                ", storage=" + storage +
                '}';
    }
}
