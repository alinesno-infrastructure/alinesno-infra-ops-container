package com.alinesno.infra.ops.container.analyzer.bean;

import lombok.Data;

/**
 * 表示敏感信息，包括未屏蔽和已屏蔽的两个版本。
 */
@Data
public class SensitiveBean {

    /**
     * 未屏蔽的敏感信息。
     */
    private String unmasked;

    /**
     * 已屏蔽的敏感信息。
     */
    private String masked;

}