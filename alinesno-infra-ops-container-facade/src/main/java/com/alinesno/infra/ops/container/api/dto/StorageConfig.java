package com.alinesno.infra.ops.container.api.dto;

public class StorageConfig {
        private String isDeploy;

        // getter and setter methods

        public boolean getIsDeploy() {
            return isDeploy.equals("yes");
        }

        public void setIsDeploy(String isDeploy) {
            this.isDeploy = isDeploy;
        }

        @Override
        public String toString() {
            return "StorageConfig{" +
                    "isDeploy='" + isDeploy + '\'' +
                    '}';
        }
    }
