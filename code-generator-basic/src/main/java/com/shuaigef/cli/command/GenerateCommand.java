package com.shuaigef.cli.command;

import cn.hutool.core.bean.BeanUtil;
import com.shuaigef.generator.MainGenerator;
import com.shuaigef.model.MainTemplateConfig;
import lombok.Data;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

/**
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 * @date 2023/11/30 12:44
 */
@Command(name = "generate", mixinStandardHelpOptions = true)
@Data
public class GenerateCommand implements Callable<Integer> {

    /**
     * 作者信息
     */
    @Option(names = {"-a", "--author"}, arity = "0..1", description = "作者", interactive = true, echo = true)
    private String author = "shuaigef";

    /**
     * 输出信息
     */
    @Option(names = {"-o", "--outputText"}, arity = "0..1", description = "输出结果", interactive = true, echo = true)
    private String outputText = "输出结果: ";

    /**
     * 是否循环
     */
    @Option(names = {"-l", "--loop"}, arity = "0..1", description = "是否循环", interactive = true, echo = true)
    private boolean loop = true;

    @Override
    public Integer call() throws Exception {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        BeanUtil.copyProperties(this, mainTemplateConfig);
        MainGenerator.doGenerate(mainTemplateConfig);
        return 0;
    }
}
