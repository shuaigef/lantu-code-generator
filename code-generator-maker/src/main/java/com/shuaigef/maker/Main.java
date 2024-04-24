package com.shuaigef.maker;

import com.shuaigef.maker.generator.main.MainGenerator;
import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 * @date 2023/11/21 13:49
 */
public class Main {
    public static void main(String[] args) throws TemplateException, IOException, InterruptedException {
        // args = new String[] {"generate", "-l", "-o", "-a"};
        // args = new String[] {"config"};
        // args = new String[] {"list"};
        // CommandExecutor commandExecutor = new CommandExecutor();
        // commandExecutor.doExecute(args);
        MainGenerator mainGenerator  = new MainGenerator();
        mainGenerator.doGenerate();
    }
}