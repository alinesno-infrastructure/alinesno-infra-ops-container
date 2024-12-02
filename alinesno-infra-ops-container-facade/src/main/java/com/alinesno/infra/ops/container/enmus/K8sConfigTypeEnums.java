package com.alinesno.infra.ops.container.enmus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum K8sConfigTypeEnums  {

    TOKEN("TOKEN", "令牌"),
    CONFIG("CONFIG", "配置");

    private final String code;
    private final String label;
}