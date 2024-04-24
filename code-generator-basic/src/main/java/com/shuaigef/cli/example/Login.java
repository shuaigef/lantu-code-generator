package com.shuaigef.cli.example;

import picocli.CommandLine;

import java.util.concurrent.Callable;
import picocli.CommandLine.Option;

/**
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 * @date 2023/11/30 10:57
 */
public class Login implements Callable<Integer> {

    @Option(names = {"-u", "--user"}, description = "User name")
    String user;

    @Option(names = {"-p", "--password"}, arity = "0..1", description = "Passphrase", interactive = true, prompt = "请输入密码：")
    String password;

    @Option(names = {"-cp", "--check-password"}, arity = "0..1", description = "Check Password", interactive = true, prompt = "请再次输入密码：")
    String checkPassword;

    public Integer call() throws Exception {
        System.out.println("password = " + password);
        System.out.println("checkPassword = " + checkPassword);
        return 0;
    }

    public static void main(String[] args) {
        new CommandLine(new Login()).execute("-u", "user123", "-p", "xxx", "-cp");
    }

}
