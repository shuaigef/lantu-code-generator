package com.shuaigef.generator;

import com.shuaigef.model.MainTemplateConfig;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * 核心生成器
 *
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 * @date 2023/11/23 10:31
 */
public class MainGenerator {

    public static void main(String[] args) throws TemplateException, IOException {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("shuaigef");
        mainTemplateConfig.setOutputText("输出结果是：");
        mainTemplateConfig.setLoop(false);
        doGenerate(mainTemplateConfig);
    }

    /**
     * 生成
     *
     * @param model
     * @throws TemplateException
     * @throws IOException
     */
    public static void doGenerate(Object model) throws TemplateException, IOException {
        String projectPath = System.getProperty("user.dir");
        // 整个项目的根路径
        File parentFile = new File(projectPath).getParentFile();
        // 输入路径
        String inputPath = projectPath + File.separator + "code-generator-demo-project" + File.separator + "acm-template";
        String outputPath = projectPath;
        // 生成静态文件
        StaticGenerator.copyFilesByRecursive(inputPath, outputPath);
        // 生成动态文件
        String inoutDynamicFilePath = projectPath + File.separator + "code-generator-basic" + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String outputDynamicPath = outputPath + File.separator + "acm-template/src/com/shuaigef/acm/MainTemplate.java";
        DynamicGenerator.doGenerate(inoutDynamicFilePath, outputDynamicPath, model);
    }

}
