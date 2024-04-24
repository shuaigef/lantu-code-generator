package com.shuaigef.maker.generator.file;

import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * 核心生成器
 *
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 * @date 2023/11/23 10:31
 */
public class FileGenerator {

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
        String inputPath = parentFile.getAbsolutePath() + File.separator + "code-generator-demo-project" + File.separator + "acm-template";
        String outputPath = projectPath;
        // 生成静态文件
        StaticFileGenerator.copyFilesByHutool(inputPath, outputPath);
        // 生成动态文件
        String inoutDynamicFilePath = parentFile.getAbsolutePath() + File.separator + "code-generator-basic/src/main/resources/templates/MainTemplate.java.ftl";
        String outputDynamicPath = outputPath + File.separator + "generated/acm-template/src/com/shuaigef/acm/MainTemplate.java";
        DynamicFileGenerator.doGenerate(inoutDynamicFilePath, outputDynamicPath, model);
    }

}
