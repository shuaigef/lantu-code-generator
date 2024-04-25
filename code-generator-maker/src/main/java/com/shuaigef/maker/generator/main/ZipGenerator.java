package com.shuaigef.maker.generator.main;

/**
 * 生成代码生成器压缩包
 *
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 * @date 2024/4/25 16:12
 */
public class ZipGenerator extends GenerateTemplate {

    @Override
    protected String buildDist(String outputPath, String jarPath, String shellOutputFilePath, String sourceCopDestPath) {
        String distOutputPath = super.buildDist(outputPath, jarPath, shellOutputFilePath, sourceCopDestPath);
        return super.buildZip(distOutputPath);
    }


}
