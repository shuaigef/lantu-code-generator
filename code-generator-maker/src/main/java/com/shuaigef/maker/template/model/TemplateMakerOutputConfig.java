package com.shuaigef.maker.template.model;

import lombok.Data;

/**
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 * @date 2024/4/18 16:45
 */
@Data
public class TemplateMakerOutputConfig {

    // 从未分组的文件中移除组内同名文件
    private boolean removeGroupFilesFromRoot = true;
}
