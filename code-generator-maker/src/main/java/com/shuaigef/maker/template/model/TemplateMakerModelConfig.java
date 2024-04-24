package com.shuaigef.maker.template.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 * @date 2024/4/15 23:13
 */
@Data
public class TemplateMakerModelConfig {

    private List<ModelInfoConfig> models;

    private ModelGroupConfig modelGroupConfig;

    @NoArgsConstructor
    @Data
    public static class ModelInfoConfig {

        private String fieldName;

        private String type;

        private String description;

        private Object defaultValue;

        private String abbr;

        // 用于替换哪些文本
        private String replaceText;

    }

    @Data
    public static class ModelGroupConfig {

        private String condition;

        private String groupKey;

        private String groupName;

        private String type;

        private String description;
    }

}
