package com.alinesno.infra.ops.container.api.dto;

public class ContainerDetails {
        private String namespace;
        private String initRun;

        // getter and setter methods


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

        @Override
        public String toString() {
            return "ContainerDetails{" +
                    "namespace='" + namespace + '\'' +
                    ", initRun='" + initRun + '\'' +
                    '}';
        }
    }