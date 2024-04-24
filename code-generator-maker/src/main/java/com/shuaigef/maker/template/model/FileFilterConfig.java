package com.shuaigef.maker.template.model;

import lombok.Builder;
import lombok.Data;

/**
 * 文件过滤配置
 *
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 * @date 2024/4/15 23:11
 */
@Data
@Builder
public class FileFilterConfig {

    /**
     * 过滤范围
     */
    private String range;

    /**
     * 过滤规则
     */
    private String rule;

    /**
     * 过滤值
     */
    private String value;

}
