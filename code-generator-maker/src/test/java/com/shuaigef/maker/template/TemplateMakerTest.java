package com.shuaigef.maker.template;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.json.JSONUtil;
import com.shuaigef.maker.meta.Meta;
import com.shuaigef.maker.template.model.TemplateMakerConfig;
import com.shuaigef.maker.template.model.TemplateMakerFileConfig;
import com.shuaigef.maker.template.model.TemplateMakerModelConfig;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 * @date 2024/4/18 11:21
 */
public class TemplateMakerTest {

    @Test
    public void testMakeTemplateBug1() {
        // 1. 输入项目基本信息
        Meta meta = new Meta();
        meta.setName("acm-template-pro-generator");
        meta.setDescription("ACM 示例模板生成器");

        // 指定原始项目路径
        String projectPath = System.getProperty("user.dir");
        String originProjectPath = new File(projectPath).getParent() + File.separator + "code-generator-demo-project/springboot-init";
        String fileInputPath1 = "./";

        // 模型分组配置
        TemplateMakerModelConfig templateMakerModelConfig = new TemplateMakerModelConfig();
        TemplateMakerModelConfig.ModelInfoConfig modelInfoConfig1 = new TemplateMakerModelConfig.ModelInfoConfig();
        modelInfoConfig1.setFieldName("className");
        modelInfoConfig1.setType("String");
        modelInfoConfig1.setDefaultValue("BaseResponse");
        modelInfoConfig1.setReplaceText("BaseResponse");

        List<TemplateMakerModelConfig.ModelInfoConfig> modelInfoConfigList = Arrays.asList(modelInfoConfig1);
        templateMakerModelConfig.setModels(modelInfoConfigList);

        // 文件过滤配置
        TemplateMakerFileConfig templateMakerFileConfig = new TemplateMakerFileConfig();
        // 配置1
        TemplateMakerFileConfig.FileInfoConfig fileInfoConfig1 = new TemplateMakerFileConfig.FileInfoConfig();
        fileInfoConfig1.setPath(fileInputPath1);

        List<TemplateMakerFileConfig.FileInfoConfig> fileInfoConfigList = Arrays.asList(fileInfoConfig1);
        templateMakerFileConfig.setFiles(fileInfoConfigList);

        TemplateMaker.makeTemplate(meta, originProjectPath, templateMakerFileConfig, templateMakerModelConfig, null, 2L);
    }

    @Test
    public void testMakeTemplateWithJson() {
        String readUtf8Str = ResourceUtil.readUtf8Str("templateMaker.json");
        TemplateMakerConfig templateMakerConfig = JSONUtil.toBean(readUtf8Str, TemplateMakerConfig.class);
        long id = TemplateMaker.makeTemplate(templateMakerConfig);
        System.out.println(id);
    }

    /**
     * 制作 Spring Boot 模板
     */
    @Test
    public void makeSpringbootTemplate() {
        String readUtf8Str = ResourceUtil.readUtf8Str("examples/springboot-init/templateMaker.json");
        TemplateMakerConfig templateMakerConfig = JSONUtil.toBean(readUtf8Str, TemplateMakerConfig.class);
        long id = TemplateMaker.makeTemplate(templateMakerConfig);

        readUtf8Str = ResourceUtil.readUtf8Str("examples/springboot-init/templateMaker1.json");
        templateMakerConfig = JSONUtil.toBean(readUtf8Str, TemplateMakerConfig.class);
        TemplateMaker.makeTemplate(templateMakerConfig);

        readUtf8Str = ResourceUtil.readUtf8Str("examples/springboot-init/templateMaker2.json");
        templateMakerConfig = JSONUtil.toBean(readUtf8Str, TemplateMakerConfig.class);
        TemplateMaker.makeTemplate(templateMakerConfig);

        readUtf8Str = ResourceUtil.readUtf8Str("examples/springboot-init/templateMaker3.json");
        templateMakerConfig = JSONUtil.toBean(readUtf8Str, TemplateMakerConfig.class);
        TemplateMaker.makeTemplate(templateMakerConfig);

        readUtf8Str = ResourceUtil.readUtf8Str("examples/springboot-init/templateMaker4.json");
        templateMakerConfig = JSONUtil.toBean(readUtf8Str, TemplateMakerConfig.class);
        TemplateMaker.makeTemplate(templateMakerConfig);

        readUtf8Str = ResourceUtil.readUtf8Str("examples/springboot-init/templateMaker5.json");
        templateMakerConfig = JSONUtil.toBean(readUtf8Str, TemplateMakerConfig.class);
        TemplateMaker.makeTemplate(templateMakerConfig);

        readUtf8Str = ResourceUtil.readUtf8Str("examples/springboot-init/templateMaker6.json");
        templateMakerConfig = JSONUtil.toBean(readUtf8Str, TemplateMakerConfig.class);
        TemplateMaker.makeTemplate(templateMakerConfig);

        readUtf8Str = ResourceUtil.readUtf8Str("examples/springboot-init/templateMaker7.json");
        templateMakerConfig = JSONUtil.toBean(readUtf8Str, TemplateMakerConfig.class);
        TemplateMaker.makeTemplate(templateMakerConfig);

        readUtf8Str = ResourceUtil.readUtf8Str("examples/springboot-init/templateMaker8.json");
        templateMakerConfig = JSONUtil.toBean(readUtf8Str, TemplateMakerConfig.class);
        TemplateMaker.makeTemplate(templateMakerConfig);

        System.out.println(id);
    }


}