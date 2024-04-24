package com.shuaigef.maker.template.model;

import com.shuaigef.maker.meta.Meta;
import lombok.Data;

/**
 * 模板制作配置
 *
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 * @date 2024/4/18 15:02
 */
@Data
public class TemplateMakerConfig {

    private Long id;

    private Meta meta = new Meta();

    private String originProjectPath;

    private TemplateMakerFileConfig fileConfig = new TemplateMakerFileConfig();

    private TemplateMakerModelConfig modelConfig = new TemplateMakerModelConfig();

    private TemplateMakerOutputConfig outputConfig = new TemplateMakerOutputConfig();

}
