package com.shuaigef.cli.command;

import cn.hutool.core.io.FileUtil;
import picocli.CommandLine.Command;

import java.io.File;
import java.util.List;

/**
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 * @date 2023/11/30 12:44
 */
@Command(name = "list", mixinStandardHelpOptions = true)
public class ListCommand implements Runnable{

    @Override
    public void run() {
        String projectPath = System.getProperty("user.dir") + File.separator + "code-generator-basic";
        File parentFile = new File(projectPath).getParentFile();
        String inputPath = new File(parentFile, "code-generator-demo-project" + File.separator + "acm-template").getAbsolutePath();
        List<File> files = FileUtil.loopFiles(inputPath);
        for (File file : files) {
            System.out.println(file);
        }
    }

}
