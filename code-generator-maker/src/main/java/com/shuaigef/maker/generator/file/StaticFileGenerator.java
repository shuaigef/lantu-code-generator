package com.shuaigef.maker.generator.file;

import cn.hutool.core.io.FileUtil;

/**
 * 静态文件生成器
 *
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 * @date 2023/11/21 17:05
 */
public class StaticFileGenerator {

    /**
     * 拷贝文件（Hutool 实现）
     * @param inputPath 输入路径
     * @param outputPath 输出路径
     */
    public static void copyFilesByHutool(String inputPath, String outputPath) {
        FileUtil.copy(inputPath, outputPath, false);
    }

}
