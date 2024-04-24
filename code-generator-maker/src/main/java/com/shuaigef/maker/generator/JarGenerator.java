package com.shuaigef.maker.generator;

import java.io.*;

/**
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 * @date 2023/12/9 11:26
 */
public class JarGenerator {

    public static void doGenerate(String projectDir) throws InterruptedException, IOException {
        // 调用 Process 类执行 Maven 打包命令
        String winMavenCommand = "mvn.cmd clean package -DskipTests=true";
        String otherMavenCommand = "mvn.cmd clean package -DskipTests=true";
        String mavenCommand = winMavenCommand;

        ProcessBuilder processBuilder = new ProcessBuilder(mavenCommand.split(" "));
        processBuilder.directory(new File(projectDir));

        Process process = processBuilder.start();

        // 读取命令的输出
        InputStream inputStream = process.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }

        int exitCode = process.waitFor();
        System.out.println("命令执行结束，退出码：" + exitCode);
    }
}
