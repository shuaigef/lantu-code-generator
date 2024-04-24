package com.shuaigef.model;

import lombok.Data;

/**
 * 静态模板配置
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 * @date 2023/11/22 23:24
 */
@Data
public class MainTemplateConfig {

    /**
     * 作者信息
     */
    private String author = "shuaigef";

    /**
     * 输出信息
     */
    private String outputText = "输出结果: ";

    /**
     * 是否循环
     */
    private boolean loop = true;

}
