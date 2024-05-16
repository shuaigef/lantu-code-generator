package com.shuaigef.maker.generator.main;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ZipUtil;
import com.shuaigef.maker.generator.JarGenerator;
import com.shuaigef.maker.generator.ScriptGenerator;
import com.shuaigef.maker.generator.file.DynamicFileGenerator;
import com.shuaigef.maker.meta.Meta;
import com.shuaigef.maker.meta.MetaManager;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 * @date 2024/4/11 09:45
 */
public abstract class GenerateTemplate {

    /**
     * 生成
     *
     * @throws TemplateException
     * @throws IOException
     * @throws InterruptedException
     */
    public void doGenerate() throws TemplateException, IOException, InterruptedException {
        Meta meta = MetaManager.getMetaObject();

        // 输出根路径
        String projectPath = System.getProperty("user.dir");
        String outputPath = projectPath + File.separator + "generated" +File.separator + meta.getName();
        doGenerate(meta, outputPath);
    }

    /**
     * 生成
     *
     * @throws TemplateException
     * @throws IOException
     * @throws InterruptedException
     */
    public void doGenerate(Meta meta, String outputPath) throws TemplateException, IOException, InterruptedException {
        // 输出根路径
        if (!FileUtil.exist(outputPath)) {
            FileUtil.mkdir(outputPath);
        }

        // 复制原始文件
        String sourceCopDestPath = copySource(meta, outputPath);

        // 代码生成
        generateCode(meta, outputPath);

        // 构建 jar 包
        String jarPath = buildJar(outputPath, meta);

        // 封装脚本
        String shellOutputFilePath = buildScript(outputPath, jarPath);

        // 生成精简版的程序
        buildDist(outputPath, jarPath, shellOutputFilePath, sourceCopDestPath);
    }

    /**
     * 从原始模板文件路径复制到生成的代码包中
     *
     * @param meta
     * @param outputPath
     * @return
     */
    protected String copySource(Meta meta, String outputPath) {
        String sourceRootPath = meta.getFileConfig().getSourceRootPath();
        String sourceCopDestPath = outputPath + File.separator + ".source";
        FileUtil.copy(sourceRootPath, sourceCopDestPath, false);
        return sourceCopDestPath;
    }

    /**
     * 生成代码
     * @param meta
     * @param outputPath
     * @throws IOException
     * @throws TemplateException
     */
    protected void generateCode(Meta meta, String outputPath) throws IOException, TemplateException {
        // 读取 resources 目录
        // ClassPathResource classPathResource = new ClassPathResource("");
        // String inputResourcePath = classPathResource.getAbsolutePath();
        String inputResourcePath = "";

        // Java 包基础路径
        // com.shuaigef
        String outputBasePackage = meta.getBasePackage();
        // com/shuaigef
        String outputBasePackagePath = StrUtil.join("/", StrUtil.split(outputBasePackage, "."));
        // com/shuaigef/src/main/java
        String outputBaseJavaPackagePath = outputPath + File.separator + "src/main/java/" + outputBasePackagePath;

        String inputFilePath;
        String outputFilePath;

        // model.DataModel
        inputFilePath = inputResourcePath + File.separator + "templates/java/model/DataModel.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/model/DataModel.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // cli.command.GenerateCommand
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command/GenerateCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/command/GenerateCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // cli.command.JsonGenerateCommand
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command/JsonGenerateCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/command/JsonGenerateCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // cli.command.ConfigCommand
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command/ConfigCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/command/ConfigCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // cli.command.ListCommand
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command/ListCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/command/ListCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // cli.CommandExecutor
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/CommandExecutor.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/CommandExecutor.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // Main
        inputFilePath = inputResourcePath + File.separator + "templates/java/Main.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/Main.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // generator.FileGenerator.java.ftl
        inputFilePath = inputResourcePath + File.separator + "templates/java/generator/FileGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/generator/FileGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // generator.DynamicFileGenerator.java.ftl
        inputFilePath = inputResourcePath + File.separator + "templates/java/generator/DynamicFileGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/generator/DynamicFileGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // generator.StaticFileGenerator.java.ftl
        inputFilePath = inputResourcePath + File.separator + "templates/java/generator/StaticFileGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/generator/StaticFileGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // pom.xml
        inputFilePath = inputResourcePath + File.separator + "templates/pom.xml.ftl";
        outputFilePath = outputPath + "/pom.xml";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // README.md
        // inputFilePath = inputResourcePath + File.separator + "templates/README.md.ftl";
        // outputFilePath = outputPath + File.separator + "README.md";
        // DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);
    }

    /**
     * 构建 jar 包
     * @param outputPath
     * @param meta
     * @throws InterruptedException
     * @throws IOException
     */
    protected String buildJar(String outputPath, Meta meta) throws InterruptedException, IOException {
        JarGenerator.doGenerate(outputPath);
        String jarName = String.format("%s-%s-jar-with-dependencies.jar", meta.getName(), meta.getVersion());
        String jarPath = "target/" + jarName;
        return jarPath;
    }

    /**
     * 封装脚本
     * @param outputPath
     * @param jarPath
     * @return
     */
    protected String buildScript(String outputPath, String jarPath) {
        String shellOutputFilePath = outputPath + File.separator + "generator";
        ScriptGenerator.doGenerate(shellOutputFilePath, jarPath);
        return shellOutputFilePath;
    }

    /**
     * 生成精简版的程序
     * @param outputPath
     * @param jarPath
     * @param shellOutputFilePath
     * @param sourceCopDestPath
     */
    protected String buildDist(String outputPath, String jarPath, String shellOutputFilePath, String sourceCopDestPath) {
        String distOutputPath = outputPath + "-dist";
        // 拷贝 jar 包
        String targetAbsolutePath = distOutputPath + File.separator + "target";
        FileUtil.mkdir(targetAbsolutePath);
        String jarAbsolutePath = outputPath + File.separator + jarPath;
        FileUtil.copy(jarAbsolutePath, targetAbsolutePath, true);
        // 拷贝脚本文件
        FileUtil.copy(shellOutputFilePath, distOutputPath, true);
        FileUtil.copy(shellOutputFilePath + ".bat", distOutputPath, true);
        // 拷贝源模板文件
        FileUtil.copy(sourceCopDestPath, distOutputPath, true);
        return distOutputPath;
    }

    /**
     * 制作压缩包
     *
     * @param outputPath
     * @return 压缩包路径
     */
    protected String buildZip(String outputPath) {
        String zipPath = outputPath + ".zip";
        ZipUtil.zip(outputPath, zipPath);
        return zipPath;
    }


}
