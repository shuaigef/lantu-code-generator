package com.shuaigef.maker;

import com.shuaigef.maker.generator.main.GenerateTemplate;
import com.shuaigef.maker.generator.main.MainGenerator;
import com.shuaigef.maker.generator.main.ZipGenerator;
import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 * @date 2023/11/21 13:49
 */
public class Main {
    public static void main(String[] args) throws TemplateException, IOException, InterruptedException {
        // GenerateTemplate generateTemplate  = new MainGenerator();
        GenerateTemplate generateTemplate  = new ZipGenerator();
        generateTemplate.doGenerate();
    }
}